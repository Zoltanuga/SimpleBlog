package by.it.controller;

import by.it.model.Comment;
import by.it.services.ICommentService;
import by.it.services.exeptions.IncorrectDataException;
import by.it.services.exeptions.ServiceException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static by.it.controller.UserController.getPrincipal;

@Controller
@RequestMapping(value = "/")
public class CommentController {
    private static Logger log = Logger.getLogger(CommentController.class);
    @Autowired
    ICommentService commentService;



    @RequestMapping(value = "/comments/add", method = RequestMethod.GET)
    public String addCommentPage(@RequestParam("newsId") int newsId,  ModelMap model) {
        model.put("newsId", newsId);
        return "addComment";
    }

    @RequestMapping(value = "/comments/add/add", method = RequestMethod.POST)
    public String addComment(@RequestParam("newsId") int newsId, HttpServletRequest request) {
        String content = request.getParameter("contentComment");
        try {
            commentService.addComment(content, getPrincipal(), newsId);
        } catch (IncorrectDataException e) {
            log.error(e.getMessage());
            return "redirect:/comments/add?isNotValidData=true&newsId=" + newsId;
        } catch (ServiceException e) {
            log.error(e.getMessage());
            return "redirect:/comments?errorComment=true&newsId=" + newsId;
        }
        return "redirect:/comments?successComment=true&newsId=" + newsId;
    }


    @RequestMapping(value = "/comments/edit", method = RequestMethod.GET)
    public String editCommentPage(@RequestParam("newsId") int newsId, @RequestParam("commentId") int commentId,
                                  ModelMap model) {
        Comment comment = null;
        try {
            comment = commentService.obtainComment(commentId);
        } catch (ServiceException e) {
            log.error(e.getMessage());
            return "redirect:/comments?errorComment=true&newsId=" + newsId;
        }
        model.put("newsId", newsId);
        model.put("comment", comment);

        return "editComment";
    }

    @RequestMapping(value = {"/comments/edit/edit"}, method = {RequestMethod.POST})
    public String editComment(@RequestParam("newsId") int newsId, @RequestParam("commentId") int commentId,
                              HttpServletRequest request) {
        try {
            commentService.updateComment(commentId, request.getParameter("contentComment"));
        } catch (IncorrectDataException e) {
            log.error(e.getMessage());
            return "redirect:/comments/edit?isNotValidData=true&newsId=" + newsId;
        } catch (ServiceException e) {
            log.error(e.getMessage());
            return "redirect:/comments?errorComment=true&newsId=" + newsId;
        }
        return "redirect:/comments?successComment=true&newsId=" + newsId;
    }

    @RequestMapping(value = {"/comments/delete"}, method = {RequestMethod.GET})
    public String deleteComment(@RequestParam("newsId") int newsId, @RequestParam("commentId") int commentId) {
        try {
            commentService.deleteComment(commentId);
        } catch (ServiceException e) {
            log.error(e.getMessage());
            return "redirect:/comments?errorDelete=true&newsId=" + newsId;
        }
        return "redirect:/comments?successDelete=true&newsId=" + newsId;
    }


    @RequestMapping(value = {"/comments/paging", "/comments"})
    public String listComments(@RequestParam("newsId") int newsId, HttpServletRequest request, ModelMap model) throws Exception {
        List<Comment> allComments = commentService.obtainComments("date", 0, 10, newsId);
        PagedListHolder<Comment> pagedListHolder = new PagedListHolder<>(allComments);
        int page = ServletRequestUtils.getIntParameter(request, "p", 0);
        pagedListHolder.setPage(page);
        int pageSize = 5;
        pagedListHolder.setPageSize(pageSize);
        model.put("newsId", newsId);
        model.put("pagedListHolder", pagedListHolder);
        return "comments";
    }


}

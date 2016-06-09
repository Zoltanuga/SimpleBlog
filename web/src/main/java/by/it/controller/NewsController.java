package by.it.controller;


import by.it.model.News;
import by.it.services.INewsService;
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
public class NewsController {
    public static final String PARAM_SORT = "sort";
    public static final int VALUE_SHIFT = 1;
    public static final String PARAM_HEADER = "header";
    public static final String PARAM_TEXT_NEWS = "textNews";
    private static Logger log = Logger.getLogger(NewsController.class);
    @Autowired
    INewsService newsService;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addNewsPage() {
        return "addNews";
    }

    @RequestMapping(value = "/add/add", method = RequestMethod.POST)
    public String addNews(HttpServletRequest request) {
        try {
            newsService.addNews(request.getParameter(PARAM_HEADER),
                    request.getParameter(PARAM_TEXT_NEWS), getPrincipal());
        } catch (IncorrectDataException e) {
            log.error(e.getMessage());
            return "redirect:/add?isNotValidData=true";
        } catch (ServiceException e) {
            log.error(e.getMessage());
            return "redirect:/?errorAddNews=true";
        }
        return "redirect:/?successAddNews=true";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editNewsPage(@RequestParam("newsId") int newsId, ModelMap model) {
        try {
            News news = newsService.obtainNews(newsId);
            model.put("currentNews", news);
        }catch (ServiceException e) {
            log.error(e.getMessage());
            return "redirect:/?errorAddNews=true";
        }
        return "editNews";
    }

    @RequestMapping(value = "/edit/edit", method = RequestMethod.POST)
    public String editNews(@RequestParam("newsId") int newsId, HttpServletRequest request) {
        try {
            newsService.updateNews(newsId, request.getParameter(PARAM_HEADER),
                    request.getParameter(PARAM_TEXT_NEWS));
        } catch (IncorrectDataException e) {
            log.error(e.getMessage());
            return "redirect:/edit?isNotValidData=true";
        } catch (ServiceException e) {
            log.error(e.getMessage());
            return "redirect:/?errorAddNews=true";
        }
        return "redirect:/?successAddNews=true";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteNews(@RequestParam("newsId") int newsId){
        try {
            newsService.deleteNews(newsId);
        } catch (ServiceException e) {
            log.error(e.getMessage());
            return "redirect:/?errorDelete=true";
        }
        return "redirect:/?successDelete=true";
    }

    @RequestMapping(value = {"/paging", "/"})
    public String listNews(HttpServletRequest request,ModelMap model) throws ServletRequestBindingException {
        List<News> allNews = null;
        try {
            allNews = newsService.obtainNewsList("date", 0, 11);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        PagedListHolder<News> pagedListHolder = new PagedListHolder<>(allNews);
        int page = ServletRequestUtils.getIntParameter(request, "p", 0);
        pagedListHolder.setPage(page);
        int pageSize = 5;
        pagedListHolder.setPageSize(pageSize);
        model.put("pagedListHolder", pagedListHolder);
        return "news";
    }

















    /*    @RequestMapping(value = {"/"}, method = {RequestMethod.GET})
        public String newsPage(ModelMap model) {

            System.out.println("!!!!!!!!!!!!!!!!!!!!");
            String paramSort = model.containsAttribute(PARAM_SORT) ?
                    model.get(PARAM_SORT).toString() : SortMode.SORT_BY_DATE.toString();
            model.put(PARAM_SORT, paramSort);
            int newsPerPage = 10;
            if (newsPerPage == 0) {
                newsPerPage = Paginator.VALUE_DEFAULT_ITEMS_PER_PAGE;
            }
            long newsQuantity = 0;
            try {
                newsQuantity = newsService.countNews();
            } catch (ServiceException e) {
                // log.error(e.getMessage());
                // return ERROR_PAGE;
            }
            System.out.println("news quantity   " + newsQuantity);
            int totalPages = Paginator.calculatePageQuantity(newsPerPage, newsQuantity);
            int currentPage = Paginator.getCurrentPageInCaseOfChange(model, totalPages);
            Paginator.setPageOrder(model, totalPages);
            Paginator.setCurrentPage(model, currentPage);
            Paginator.setTotalPages(model, totalPages);
            Paginator.setItemsPerPage(model, newsPerPage);
            System.out.println("333!!!!!!!");

            for (String s : model.keySet()) {
                System.out.println(s);
            }
            System.out.println("444!!!!!!!");
            List<News> newsList = null;
            try {
                newsList = getNewsList(paramSort, newsPerPage, currentPage);
            } catch (ServiceException e) {
             *//*   log.error(e.getMessage());
            return ERROR_PAGE;*//*
        }

        model.addAttribute("newsList", newsList);
        return "news/main";
    }*/


/*
    @RequestMapping(value = {"/pages"}, method = RequestMethod.GET)
    public String listNews(ModelMap model) {
        model.addAttribute("user", getPrincipal());

*/
/*
        String paramSort = model.get(PARAM_SORT).toString();
*//*

        String paramSort = model.containsAttribute(PARAM_SORT) ?
                model.get(PARAM_SORT).toString() : SortMode.SORT_BY_DATE.toString();
        model.put(PARAM_SORT, paramSort);

        System.out.println("*****************");

        int newsPerPage = Paginator.getItemsPerPage(model);
        int totalPages = Paginator.getTotalPages(model);
        int currentPage = Paginator.getCurrentPage(model);
        System.out.println("111*************");

        for (String s : model.keySet()) {
            System.out.println(s);
        }
        System.out.println("222*************");

        List<News> newsList = null;
        try {
            newsList = getNewsList(paramSort, newsPerPage, currentPage);
        } catch (ServiceException e) {
          */
/*  log.error(e.getMessage());
            return ERROR_PAGE;*//*

        }
        Paginator.setCurrentPage(model, currentPage);
        Paginator.setPageOrder(model, totalPages);
        Paginator.setItemsPerPage(model, newsPerPage);
        Paginator.setTotalPages(model, totalPages);
        model.addAttribute("newsList", newsList);
        return "news/main";
    }

   private List<News> getNewsList(String paramSort, int newsPerPage, int currentPage) throws ServiceException {
        List<News> newsList;
        SortMode sortMode = SortMode.valueOf(paramSort.toUpperCase());
        int startPosition = newsPerPage * (currentPage - VALUE_SHIFT);
        newsList = newsService.obtainNewsList(sortMode.obtain(), startPosition, newsPerPage);
        return newsList;
    }*/
}

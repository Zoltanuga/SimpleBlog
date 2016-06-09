package by.it.services.impl;



import by.it.dao.ICommentDao;
import by.it.dao.exceptions.DaoException;
import by.it.model.Comment;
import by.it.services.ICommentService;
import by.it.services.INewsService;
import by.it.services.IUserService;
import by.it.services.exeptions.IncorrectDataException;
import by.it.services.exeptions.ServiceException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CommentService implements ICommentService {
    private static CommentService instance;
    private static Logger log = Logger.getLogger(CommentService.class);
    @Autowired
    private ICommentDao commentDAO;
    @Autowired
    private IUserService userService;
    @Autowired
    INewsService newsService;

    public Comment obtainComment(int id) throws ServiceException {
        Comment comment = null;
        log.info("obtainComment(int id) before start transaction in service");
        try {
            comment = commentDAO.get(id);
            log.info("obtainComment(int id) transaction committed in service");
        } catch (DaoException e) {
            log.error("obtainComment(int id) error in service" + e);
            throw new ServiceException(e);
        }
        return comment;
    }

    public List<Comment> obtainComments(String sortParam, int offset, int limit, int newsId) throws ServiceException {
        List<Comment> comments = new ArrayList<Comment>();
        log.info("obtainComment(sortParam, offset, limit, newsId) before start transaction in service");
        try {
            comments = commentDAO.obtainComments(sortParam, offset, limit, newsId);
            log.info("obtainComment(sortParam, offset, limit, newsId)  transaction committed in service");
        } catch (DaoException e) {
            log.error("obtainComment(sortParam, offset, limit, newsId) error in service" + e);
            throw new ServiceException(e);
        }
        return comments;
    }


    public long countComments() throws ServiceException {
        long commentQuantity = 0;
        log.info("countComments() before start transaction in service");
        try {
            commentQuantity = commentDAO.countComments();
            log.info("countComments() transaction committed in service");
        } catch (DaoException e) {
            log.error("countComments() error in service" + e);
            throw new ServiceException(e);
        }
        return commentQuantity;
    }

    public long countComments(int newsId) throws ServiceException {
        long commentQuantity = 0;
        log.info("countComments(int newsId)  before start transaction in service");
        try {
            commentQuantity = commentDAO.countComments(newsId);
            log.info("countComments(int newsId)  transaction committed in service");
        } catch (DaoException e) {
            log.error("countComments(int newsId) error in service" + e);
            throw new ServiceException(e);
        }
        return commentQuantity;
    }

    public void addComment(String content, String userEmail, int newsId) throws ServiceException {
        Date date = new Date();
        if (!StringUtils.isEmpty(content)) {
            Comment comment = new Comment();
            comment.setUser( userService.obtainUser(userEmail));
            comment.setContent(content);
            comment.setDate(date);
            comment.setNews(newsService.obtainNews(newsId));
            log.info("addComment(content, user, news) before start transaction in service");

            try {
                commentDAO.saveOrUpdate(comment);
                log.info("addComment(content, user, news)  transaction committed in service");
            } catch (DaoException e) {
                log.error("addComment(content, user, news) error in service" + e);
                throw new ServiceException(e);
            }
        } else {
            throw new IncorrectDataException(new Exception("content is empty"));
        }
    }

    public void updateComment(int id, String content) throws ServiceException {
        log.info("updateComment(id, content) before start transaction in service");
        if (!StringUtils.isEmpty(content)) {
            Comment comment = null;
            try {
                comment = commentDAO.get(id);
                comment.setContent(content);
                commentDAO.saveOrUpdate(comment);
                log.info("updateComment(id, content)  transaction committed in service");

            } catch (DaoException e) {
                log.error("updateComment(id, content) error in service" + e);
                throw new ServiceException(e);
            }
        } else {
            throw new IncorrectDataException(new Exception("content is empty"));
        }
    }

    public void deleteComment(int id) throws ServiceException {
        log.info("deleteComment(int id) before start transaction in service");
        Comment comment = null;
        try {
            comment = commentDAO.get(id);
            commentDAO.delete(comment);
            log.info("deleteComment(int id)  transaction committed in service");
        } catch (DaoException e) {
            log.error("deleteComment(int id) error in service" + e);
            throw new ServiceException(e);
        }
    }
}

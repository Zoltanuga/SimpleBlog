package by.it.services;


import by.it.model.Comment;
import by.it.model.News;
import by.it.model.User;
import by.it.services.exeptions.ServiceException;

import java.util.List;

/**
 * service layer for news entities
 * singleton
 */
public interface ICommentService {
    /**
     * @param id of wanted comment
     * @return wanted comment (null if comment is absent)
     * @throws ServiceException
     */
    Comment obtainComment(int id) throws ServiceException;

    /**
     * @param sortParam of selecting from DB
     * @param offset    is start position of selecting comments
     * @param limit     is number of selected comments
     * @param newsId    is parameter of news (fk in DB) to which attached comments
     * @return list of comment (size must be equal limit)
     * @throws ServiceException
     */
    List<Comment> obtainComments(String sortParam, int offset, int limit, int newsId) throws ServiceException;

    /**
     * @return is long type total quantity comments in DB
     * @throws ServiceException
     */
    long countComments() throws ServiceException;

    /**
     * @param newsId is parameter of restrictions
     * @return quantity of comments attached to news with newsId
     * @throws ServiceException
     */
    long countComments(int newsId) throws ServiceException;

    /**
     * @param content of added comment
     * @param userEmail user  who add comment
     * @param newsId   news to which attach comment
     * @throws ServiceException
     */
    void addComment(String content, String userEmail, int newsId) throws ServiceException;

    /**
     * @param id      of comment that we want to update
     * @param content is new content for updating
     * @throws ServiceException
     */
    void updateComment(int id, String content) throws ServiceException;

    /**
     * @param id of comment that we want to delete
     */
    void deleteComment(int id) throws ServiceException;
}

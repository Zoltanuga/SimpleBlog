package by.it.dao;


import by.it.dao.exceptions.DaoException;
import by.it.model.Comment;

import java.util.List;

/**
 * encapsulates specific operations with comments table
 */
public interface ICommentDao  extends Dao<Comment> {
    /**
     *
     * @return is long type total quantity comments in DB
     * @throws DaoException
     */
    long countComments() throws DaoException;

    /**
     *
     * @param newsId is parameter of restrictions
     * @return quantity of comments attached to news with newsId
     * @throws DaoException
     */
    long countComments(int newsId) throws DaoException;

    /**
     *
     * @param sortParam of selecting from DB
     * @param offset is start position of selecting comments
     * @param limit is number of selected comments
     * @param newsId is parameter of news (fk in DB) to which attached comments
     * @return list of comment (size must be equal limit)
     * @throws DaoException
     */
    List<Comment> obtainComments(String sortParam, int offset, int limit, int newsId) throws DaoException;

}

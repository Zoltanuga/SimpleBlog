package by.it.dao;

import by.it.dao.exceptions.DaoException;
import by.it.model.User;

import java.util.List;

/**
 *encapsulates specific operations with user table
 */
public interface IUserDao extends Dao<User> {
    /**
     *
     * @return list of users from DB
     * @throws DaoException
     */
    List<by.it.model.User> obtainUserList() throws DaoException;
}


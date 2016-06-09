package by.it.services;




import by.it.model.User;
import by.it.model.UserRole;
import by.it.services.exeptions.ServiceException;

import java.util.List;

/**
 * service level for user entities
 * singleton
 * perform business logic of app
 */
public interface IUserService {
    /**
     * add user to DB
     *
     * @param email    string parameter
     * @param password string parameter
     * @param name     string parameter
     * @param surname  string parameter
     * @throws ServiceException
     */
    void addUser(String email, String password, String name, String surname, UserRole role) throws ServiceException;

    /**
     * get user entity by email
     *
     * @param email string
     * @return user entity
     * @throws ServiceException
     */
    User obtainUser(String email) throws ServiceException;

    /**
     * @return list of users from DB
     * @throws ServiceException
     */
    List<User> obtainUserList() throws ServiceException;

    /**
     * @param user entity for deleting from DB
     * @throws ServiceException
     */
    void deleteUser(User user) throws ServiceException;
}


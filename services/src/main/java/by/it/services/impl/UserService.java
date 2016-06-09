package by.it.services.impl;


import by.it.dao.IUserDao;
import by.it.dao.exceptions.DaoException;
import by.it.model.User;
import by.it.model.UserRole;
import by.it.services.IUserService;
import by.it.services.exeptions.IncorrectDataException;
import by.it.services.exeptions.ServiceException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("userService")
@Transactional
public class UserService implements IUserService {
    public static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public static final String PASSWORD_REGEX = "^.*(?=.{10,})(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$";
    public static final String NAME_REGEX = "[A-Z]{1}[a-z]+";
    private static Logger log = Logger.getLogger(UserService.class);
    @Autowired
    @Qualifier("userDao")
    private IUserDao userDAO;

    public UserService() {
    }



    public void addUser(String email, String password, String name, String surname, UserRole role) throws ServiceException {
        boolean isEmailCorrect = isCorrectEmail(email);
        boolean isPasswordCorrect = isCorrectPassword(password);
        boolean isNameCorrect = isCorrectName(name);
        boolean isSurnameCorrect = isCorrectName(surname);
        log.info("before check on valid input in service");
        if (isEmailCorrect && isPasswordCorrect && isNameCorrect && isSurnameCorrect) {
            User user = new User();
            user.setName(name);
            user.setPassword(password);
            user.setEmail(email);
            user.setSurname(surname);
            user.setRole(role);
            log.info("addUser() before start transaction in service");
            try {
                userDAO.saveOrUpdate(user);
                log.info("addUser() transaction committed in service");
            } catch (DaoException e) {
                log.error("addUser() error in service" + e);
                throw new ServiceException(e);
            }
        } else {
            log.error("addUser() Attempt to enter invalid data");
            throw new IncorrectDataException(new Exception("incorrect data input"));
        }
    }


    public User obtainUser(String email) throws ServiceException {
        User user = null;
        log.info("obtainUser(String email) before start transaction in service");
        try {
            user = userDAO.get(email);
            log.info("obtainUser(String email) transaction committed in service");
        } catch (DaoException e) {
            log.error("obtainUser(String email) error in service" + e);
            throw new ServiceException(e);
        }
        return user;
    }

    public List<User> obtainUserList() throws ServiceException {
        List<User> users = new ArrayList<>();
        log.info("obtainUserList() before start transaction in service");
        try {
            users = userDAO.obtainUserList();
            log.info("obtainUserList() transaction committed in service");
        } catch (DaoException e) {
            log.error("obtainUserList() error in service" + e);
            throw new ServiceException(e);
        }
        return users;
    }

    public void deleteUser(User user) throws ServiceException {
        log.info("deleteUser(User user) before start transaction in service");
        try {
            userDAO.saveOrUpdate(user);
            userDAO.delete(user);
            log.info("deleteUser(User user) transaction committed in service");

        } catch (DaoException e) {
            log.error("deleteUser(User user) error in service" + e);
            throw new ServiceException(e);
        }
    }


    private boolean isCorrectName(String name) {
        Pattern pattern = Pattern.compile(NAME_REGEX);
        Matcher matcher = pattern.matcher(name);
        return  matcher.matches();
    }


    private boolean isCorrectPassword(String password) {
        Pattern pattern = Pattern.compile(PASSWORD_REGEX);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }


    private boolean isCorrectEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}

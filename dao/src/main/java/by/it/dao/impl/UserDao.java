package by.it.dao.impl;


import by.it.dao.IUserDao;
import by.it.dao.exceptions.DaoException;
import by.it.model.User;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository("userDao")
@Transactional(propagation = Propagation.MANDATORY)

public class UserDao extends BaseDao<User> implements IUserDao {
    private static Logger log = Logger.getLogger(UserDao.class);

    @Autowired
   public UserDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<User> obtainUserList() throws DaoException {
        List<User> users = new ArrayList<>();
        try {
            Session session = getSession();
            log.info("before obtainUserList() in dao");
            Criteria criteria = session.createCriteria(User.class);
            users.addAll(criteria.list());
            log.info("obtainUserList() in dao is successfully");
        } catch (Exception e) {
            log.error("obtainUserList() in dao exception", e);
            throw new DaoException(e);
        }
        return users;
    }
}

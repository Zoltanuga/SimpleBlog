package by.it.dao.impl;

import by.it.dao.Dao;
import by.it.dao.exceptions.DaoException;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

@Repository
@Transactional(propagation = Propagation.MANDATORY)

public class BaseDao<T> implements Dao<T> {
    private static Logger log = Logger.getLogger(BaseDao.class);

    private SessionFactory sessionFactory;

    @Autowired
    public BaseDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getSession(){
        return sessionFactory.getCurrentSession();
    }


    public void saveOrUpdate(T t) throws DaoException {

        try {
            Session session = getSession();
            log.info("before session.saveOrUpdate(t) " + t);
            session.saveOrUpdate(t);
            log.info("save or update in dao is successfully" + t);
        } catch (Exception e) {
            log.error("save or update dao exception", e);
            throw new DaoException(e);
        }
    }

    public void delete(T t) throws DaoException {
        try {
            Session session = getSession();
            log.info("before session.delete(t) " + t);
            session.delete(t);
            log.info("delete in dao is successfully" + t);
        } catch (Exception e) {
            log.error("delete  dao exception", e);
            throw new DaoException(e);

        }
    }

    public T get(Serializable id) throws DaoException {
        T t = null;
        try {
            Session session = getSession();
            log.info("before session.get(id) ");
            t = (T) session.get(getPersistentClass(), id);
            log.info("get in dao is successfully");
        } catch (Exception e) {
            log.error("get in dao exception", e);
            throw new DaoException(e);

        }
        return t;
    }

    public T load(Serializable id) throws DaoException {
        T t = null;
        try {
            Session session = getSession();
            log.info("before session.load(id) ");
            t = (T) session.load(getPersistentClass(), id);
            log.info("load in dao is successfully");
        } catch (Exception e) {
            log.error("load in dao exception", e);
            throw new DaoException(e);
        }
        return t;
    }

    private Class getPersistentClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}

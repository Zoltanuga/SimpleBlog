package by.it.dao.impl;


import by.it.dao.INewsDao;
import by.it.dao.exceptions.DaoException;
import by.it.model.News;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional(propagation = Propagation.MANDATORY)

public class NewsDao extends BaseDao<News> implements INewsDao {
    private static Logger log = Logger.getLogger(NewsDao.class);

    @Autowired

   public NewsDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }



    public List<News> obtainListNews(String sortParam, int offset, int limit) throws DaoException {
        List<News> newsList = new ArrayList<>();
        try {
            Session session = getSession();
            log.info("before obtainListNews(sortParam, offset, limit)");
            Criteria criteria = session.createCriteria(News.class);
            Order order = Order.desc(sortParam);
            criteria.addOrder(order);
            criteria.setFirstResult(offset);
            criteria.setMaxResults(limit);
            newsList.addAll(criteria.list());
            log.info("obtainListNews(sortParam, offset, limit)in dao is successfully");
        } catch (Exception e) {
            log.error("obtainListNews(sortParam, offset, limit) in dao exception", e);
            throw new DaoException(e);
        }
        return newsList;
    }


    public long countNews() throws DaoException {
        long totalCount = 0;
        try {
            Session session = getSession();
            log.info("before countNews() in dao");
            Criteria criteria = session.createCriteria(News.class);
            criteria.setProjection(Projections.rowCount());
            totalCount = (Long) criteria.list().get(0);
            log.info("countNews() in dao is successfully");
        } catch (Exception e) {
            log.error("countNews() in dao exception", e);
            throw new DaoException(e);
        }
        return totalCount;
    }
}

package by.it.dao.impl;


import by.it.dao.ICommentDao;
import by.it.dao.exceptions.DaoException;
import by.it.model.Comment;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional(propagation = Propagation.MANDATORY)

public class CommentDao extends BaseDao<Comment> implements ICommentDao {
    private static Logger log = Logger.getLogger(CommentDao.class);

@Autowired
    public CommentDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }


    public long countComments() throws DaoException {
        long totalCount = 0;
        try {
            Session session = getSession();
            log.info("before  countComments() in dao");
            Criteria criteria = session.createCriteria(Comment.class);
            criteria.setProjection(Projections.rowCount());
            totalCount = (Long) criteria.list().get(0);
            log.info("countComments() in dao is successfully");
        } catch (Exception e) {
            log.error("obtainListNews in dao exception", e);
            throw new DaoException(e);
        }
        return totalCount;
    }

    public long countComments(int newsId) throws DaoException {
        long totalCount = 0;
        try {
            Session session = getSession();
            log.info("before  countComments(int newsId) in dao");
            Criteria criteria = session.createCriteria(Comment.class);
            criteria.setProjection(Projections.rowCount());
            criteria.createCriteria("news", "n");
            criteria.add(Restrictions.eq("n.id", newsId));
            totalCount = (Long) criteria.list().get(0);
            log.info("countComments(int newsId) in dao is successfully");
        } catch (Exception e) {
            log.error("countComments(int newsId) in dao exception", e);
            throw new DaoException(e);
        }
        return totalCount;
    }


    public List<Comment> obtainComments(String sortParam, int offset, int limit, int newsId) throws DaoException {
        List<Comment> comments = new ArrayList<Comment>();
        try {
            Session session = getSession();
            log.info("before obtainComments(sortParam, offset, limit, newsId) in dao ");
            Criteria criteria = session.createCriteria(Comment.class);
            Order order = Order.asc(sortParam);
            criteria.addOrder(order);
            criteria.createCriteria("news", "u");
            criteria.add(Restrictions.eq("u.id", newsId));
            criteria.setFirstResult(offset);
            criteria.setMaxResults(limit);
            comments.addAll(criteria.list());
            log.info("obtainComments(sortParam, offset, limit, newsId) in dao is successfully");
        } catch (Exception e) {
            log.error("obtainComments(sortParam, offset, limit, newsId) in dao exception", e);
            throw new DaoException(e);
        }
        return comments;
    }
}

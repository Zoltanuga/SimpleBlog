package by.it.services.impl;


import by.it.dao.INewsDao;
import by.it.dao.exceptions.DaoException;
import by.it.model.News;
import by.it.services.INewsService;
import by.it.services.IUserService;
import by.it.services.exeptions.IncorrectDataException;
import by.it.services.exeptions.ServiceException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class NewsService implements INewsService {
    private static Logger log = Logger.getLogger(NewsService.class);
    @Autowired
    public IUserService userService;
    private INewsDao newsDAO;

    public NewsService() {
    }

    @Autowired
    public NewsService(INewsDao newsDAO) {
        this.newsDAO = newsDAO;
    }

    public void addNews(String header, String text, String email) throws ServiceException {
        Date date = new Date();
        log.info("addNews() before checking valid data in service");
        if ((!StringUtils.isEmpty(header) && !StringUtils.isEmpty(text))) {
            News news = new News();
            news.setText(text);
            news.setHeader(header);
            news.setDate(date);
            news.setUser(userService.obtainUser(email));
            log.info("addNews(header, text, user) before start transaction in service");
            try {
                newsDAO.saveOrUpdate(news);
                log.info("addNews(header, text, user) transaction committed in service");
            } catch (DaoException e) {
                log.error("addNews(header, text, user) error in service" + e);
                throw new ServiceException(e);
            }
        } else {
            throw new IncorrectDataException(new Exception("header or text is empty"));
        }
    }

    public void updateNews(int id, String header, String text) throws ServiceException {
        if ((!StringUtils.isEmpty(header) && !StringUtils.isEmpty(text))) {
            log.info("updateNews(id, header, text) before start transaction in service");
            News news = null;
            try {
                news = newsDAO.get(id);
                news.setText(text);
                news.setHeader(header);
                newsDAO.saveOrUpdate(news);
                log.info("updateNews(id, header, text) transaction committed in service");
            } catch (DaoException e) {
                log.error("updateNews(id, header, text) error in service" + e);
                throw new ServiceException(e);
            }
        } else {
            throw new IncorrectDataException(new Exception("header or text is empty"));
        }
    }

    public void deleteNews(int id) throws ServiceException {
        log.info("updateNews(id, header, text) before start transaction in service");
        News news = null;
        try {
            news = newsDAO.get(id);
            newsDAO.delete(news);
            log.info("deleteNews(int id) transaction committed in service");
        } catch (DaoException e) {
            log.error("deleteNews(int id) error in service" + e);
            throw new ServiceException(e);
        }
    }

    public long countNews() throws ServiceException {
        long newsQuantity = 0;
        log.info("countNews() before start transaction in service");
        try {
            newsQuantity = newsDAO.countNews();
            log.info("countNews() transaction committed in service");
        } catch (DaoException e) {
            log.error("countNews() error in service" + e);
            throw new ServiceException(e);
        }
        return newsQuantity;
    }


    public News obtainNews(int id) throws ServiceException {
        News news = null;
        log.info("obtainNews(int id) before start transaction in service");
        try {
            news = newsDAO.get(id);
            log.info("obtainNews(int id) transaction committed in service");
        } catch (DaoException e) {
            log.error("obtainNews(int id) error in service" + e);
            throw new ServiceException(e);
        }
        return news;
    }


    public List<News> obtainNewsList(String paramSort, int offset, int limit) throws ServiceException {
        List<News> newsList = null;
        log.info("obtainNewsList(paramSort, offset, limit) before start transaction in service");
        try {
            newsList = newsDAO.obtainListNews(paramSort, offset, limit);
            log.info("obtainNewsList(paramSort, offset, limit) transaction committed in service");
        } catch (DaoException e) {
            log.error("obtainNewsList(paramSort, offset, limit) error in service" + e);
            throw new ServiceException(e);
        }
        return newsList;
    }
}

package by.it.services;



import by.it.model.News;
import by.it.model.User;
import by.it.services.exeptions.ServiceException;

import java.util.List;

/**
 * service layer for news entities
 * singleton
 */
public interface INewsService {
    /**
     * @param id int
     * @return news by ID
     * @throws ServiceException
     */
    News obtainNews(int id) throws ServiceException;

    /**
     * @param paramSort string parameter of sorting.It is according to column name in DB
     * @param offset is start position of selecting news
     * @param limit is number of selected news
     * @return list of news (size must be equal limit)
     * @throws ServiceException
     */
    List<News> obtainNewsList(String paramSort, int offset, int limit) throws ServiceException;

    /**
     * add news to DB
     * time and date parameters automatically generated at the moment of perform this method
     *
     * @param header is for news
     * @param text   is body of news
     * @param email   users email who add news
     * @throws ServiceException
     */
    void addNews(String header, String text, String email) throws ServiceException;

    /**
     * @param id of adding news
     * @param header of adding news
     * @param text of adding news
     * @throws ServiceException
     */
    void updateNews(int id, String header, String text) throws ServiceException;

    /**
     * @param id of deleting news
     * @throws ServiceException
     */
    void deleteNews(int id) throws ServiceException;

    /**
     * @return quantity of news that store
     * @throws ServiceException
     */
    long countNews() throws ServiceException;
}

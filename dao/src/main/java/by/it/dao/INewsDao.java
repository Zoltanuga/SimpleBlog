package by.it.dao;




import by.it.dao.exceptions.DaoException;
import by.it.model.News;

import java.util.List;

/**
 *encapsulates specific operations with news table
 */
public interface INewsDao  extends Dao<News> {
    /**
     * @param sortParam string parameter of sorting.It is according to column name in DB
     * @param offset is start position of selecting news
     * @param limit is number of selected news
     * @return list of news (size must be equal limit)
     * @throws DaoException
     */
    List<News> obtainListNews(String sortParam, int offset, int limit) throws DaoException;

    /**
     * @return quantity of news that store
     * @throws DaoException
     */
    long countNews() throws DaoException, by.it.dao.exceptions.DaoException;

}

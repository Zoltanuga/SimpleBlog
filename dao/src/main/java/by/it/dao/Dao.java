package by.it.dao;

import by.it.dao.exceptions.DaoException;

import java.io.Serializable;

/**
 * abstractions of CRUD operations
 * @param <T> for introducing entities
 */
public interface Dao<T> {

    void saveOrUpdate(T t) throws DaoException, DaoException;

    void delete(T t) throws DaoException;

    T get(Serializable id) throws DaoException;

    T load(Serializable id) throws DaoException;
}

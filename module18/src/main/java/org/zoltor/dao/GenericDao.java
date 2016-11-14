package org.zoltor.dao;

import java.util.List;

/**
 * Created by Pavel Ordenko on 14/11/2016, 22:37.
 */
public interface GenericDao<T> {

    List<T> getAll();
    T getById(long id);
    void create(T entity);
    T update(T entity);
    void delete(T entity);
}

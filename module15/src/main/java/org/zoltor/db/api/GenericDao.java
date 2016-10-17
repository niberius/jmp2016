package org.zoltor.db.api;

import org.bson.types.ObjectId;

import java.util.List;

/**
 * Created by zoltor on 18/10/16.
 */
public interface GenericDao<T> {

    List<T> getAll();
    T getById(ObjectId id);
    void save(T entity);
    void delete(T entity);

}

package org.zoltor.db.api;

import org.bson.types.ObjectId;

import java.util.List;

/**
 * Created by zoltor on 18/10/16.
 * Generic DAO interface
 */
public interface GenericDao<T> {

    /**
     * Get all entities from DB
     *
     * @return List with entities
     */
    List<T> getAll();

    /**
     * Get entity by id
     *
     * @param id Entity id from Mongo
     * @return The entitiy
     */
    T getById(ObjectId id);

    /**
     * Save given entity in MongoDB
     *
     * @param entity Entity to save
     */
    void save(T entity);

    /**
     * Delete given entity from MongoDB
     *
     * @param entity Entity to delete
     */
    void delete(T entity);

}

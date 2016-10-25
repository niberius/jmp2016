package org.zoltor.db.impl;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.zoltor.db.MorphiaUtils;
import org.zoltor.db.api.GenericDao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by zoltor on 18/10/16.
 * Basic DAO implementation for {@link GenericDao}
 * TODO Fail. It is not necessary due to {@link org.mongodb.morphia.dao.BasicDAO} existence. But I got to know it too late :(
 */
public abstract class AbstractGenericDaoImpl<T> implements GenericDao<T> {

    // Data store object to communicate with MongoDB data in DAO implementations
    protected Datastore dataStore = MorphiaUtils.getDataStore();

    // Class of DAO entity
    protected Class<T> typeClass;

    @SuppressWarnings("unchecked")
    public AbstractGenericDaoImpl() {
        final ParameterizedType genericSuperClass = (ParameterizedType) getClass().getGenericSuperclass();
        typeClass = (Class<T>) genericSuperClass.getActualTypeArguments()[0];
    }

    @Override
    public List<T> getAll() {
        return dataStore
                .createQuery(typeClass).asList();
    }

    @Override
    public T getById(ObjectId id) {
        return dataStore.get(typeClass, id);
    }

    @Override
    public void save(T entity) {
        dataStore.save(entity);
    }

    @Override
    public void delete(T entity) {
        dataStore.delete(entity);
    }
}

package org.zoltor.db.impl;

import com.mongodb.MongoClient;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.zoltor.db.api.GenericDao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import static org.zoltor.db.MongoConfig.*;

/**
 * Created by zoltor on 18/10/16.
 */
public abstract class AbstractGenericDaoImpl<T> implements GenericDao<T> {

    protected static final Datastore DATASTORE;
    protected Class<T> typeClass;

    static {
        Morphia morphia = new Morphia();
        morphia.mapPackage(ENTITIES_PACKAGE);
        DATASTORE = morphia.createDatastore(new MongoClient(HOST, PORT), STORAGE_NAME);
        DATASTORE.ensureIndexes();
    }

    @SuppressWarnings("unchecked")
    public AbstractGenericDaoImpl() {
        final ParameterizedType genericSuperClass = (ParameterizedType) getClass().getGenericSuperclass();
        typeClass = (Class<T>) genericSuperClass.getActualTypeArguments()[0];
    }

    @Override
    public List<T> getAll() {
        return DATASTORE
                .createQuery(typeClass).asList();
    }

    @Override
    public T getById(ObjectId id) {
        return DATASTORE.get(typeClass, id);
    }

    @Override
    public void save(T entity) {
        DATASTORE.save(entity);
    }

    @Override
    public void delete(T entity) {
        DATASTORE.delete(entity);
    }
}

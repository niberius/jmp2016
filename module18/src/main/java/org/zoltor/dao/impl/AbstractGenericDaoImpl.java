package org.zoltor.dao.impl;

import org.zoltor.dao.GenericDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by Pavel Ordenko on 14/11/2016, 22:40.
 */
@SuppressWarnings("unchecked")
public abstract class AbstractGenericDaoImpl<T> implements GenericDao<T> {

    @PersistenceContext
    private EntityManager em;

    // Class of DAO entity
    protected Class<T> typeClass;

    public AbstractGenericDaoImpl() {
        final ParameterizedType genericSuperClass = (ParameterizedType) getClass().getGenericSuperclass();
        typeClass = (Class<T>) genericSuperClass.getActualTypeArguments()[0];
    }

    @Override
    public List<T> getAll() {
        String findAllQuery = "SELECT e FROM " + typeClass.getName() + " e";
        return (List<T>) em.createQuery(findAllQuery).getResultList();
    }

    @Override
    public T getById(long id) {
        return em.find(typeClass, id);
    }

    @Override
    public void create(T entity) {
        em.persist(entity);
    }

    @Override
    public T update(T entity) {
        return em.merge(entity);
    }

    @Override
    public void delete(T entity) {
        em.remove(entity);
    }
}

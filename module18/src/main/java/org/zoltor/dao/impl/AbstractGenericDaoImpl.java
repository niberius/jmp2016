package org.zoltor.dao.impl;

import org.springframework.transaction.annotation.Transactional;
import org.zoltor.dao.GenericDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by Pavel Ordenko on 14/11/2016, 22:40.
 */
@SuppressWarnings("unchecked")
public abstract class AbstractGenericDaoImpl<T> implements GenericDao<T> {

    @PersistenceContext(unitName = "employee-unit", type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    // Class of DAO entity
    protected Class<T> typeClass;

    public AbstractGenericDaoImpl() {
        final ParameterizedType genericSuperClass = (ParameterizedType) getClass().getGenericSuperclass();
        typeClass = (Class<T>) genericSuperClass.getActualTypeArguments()[0];
    }

    @Override
    @Transactional
    public List<T> getAll() {
        String findAllQuery = "SELECT e FROM " + typeClass.getName() + " e";
        return (List<T>) em.createQuery(findAllQuery).getResultList();
    }

    @Override
    @Transactional
    public T getById(long id) {
        return em.find(typeClass, id);
    }

    @Override
    @Transactional
    public void create(T entity) {
        em.persist(entity);
    }

    @Override
    @Transactional
    public T update(T entity) {
        return em.merge(entity);
    }

    @Override
    @Transactional
    public void delete(T entity) {
        em.remove(entity);
    }
}

package com.pl.plugins.commons.dal.services.impl;

import com.pl.plugins.commons.dal.services.IEntityManager;
import com.pl.plugins.commons.dal.dbo.BaseDBO;
import com.pl.plugins.core.CorePlugin;

import java.util.List;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.hibernate.LockMode;


/**
 * Created by IntelliJ IDEA.
 * User: mostovoy.vladislav
 * Date: 18.09.2008
 * Time: 18:12:18
 * To change this template use File | Settings | File Templates.
 */
public class EntityManagementService<T extends BaseDBO> implements IEntityManager<T> {

    private Class<T> persistentClass;

    private HibernateTemplate hibernateTemplate;

    public EntityManagementService(HibernateTemplate hibernateTemplate){

        persistentClass = (Class<T>) ((ParameterizedType) getClass()
                               .getGenericSuperclass()).getActualTypeArguments()[0];

        if(hibernateTemplate == null)
            throw new NullPointerException("hibernateTemplate argument is null");

        this.hibernateTemplate = hibernateTemplate;
    }

    @SuppressWarnings("unchecked")
    public Class<T> getPersistentClass() {
            return persistentClass;
    }

    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    @SuppressWarnings("unchecked")
    public List<T> getAll() throws DataAccessException {

        return hibernateTemplate.loadAll(persistentClass);
    }

    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    public T getById(int id, boolean lock) throws DataAccessException {

        if(lock)
            return (T) hibernateTemplate.load(persistentClass, id, LockMode.UPGRADE);
        else
            return (T) hibernateTemplate.load(persistentClass, id);
    }
    
    @Transactional(propagation = Propagation.REQUIRED)
    public void addOrUpdate(T value) {

        hibernateTemplate.saveOrUpdate(value);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void remove(T value) {

        hibernateTemplate.delete(value);
    }
}

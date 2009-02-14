package com.pl.plugins.commons.ui.models.impl;

import com.pl.plugins.commons.ui.models.ISubscriber;
import com.pl.plugins.commons.ui.models.ISimpleDictionaryModel;
import com.pl.plugins.commons.dal.services.IEntityManager;
import com.pl.plugins.commons.dal.dbo.BaseDBO;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.io.Serializable;

import org.springframework.dao.DataAccessException;
import org.apache.log4j.Logger;
import org.jdesktop.beans.AbstractBean;

public class SimpleDictionaryModel <T extends BaseDBO> extends CommonModel implements ISimpleDictionaryModel<T> {
	
	protected IEntityManager<T> ems = null;

    private final Logger log = Logger.getLogger(SimpleDictionaryModel.class);

    public SimpleDictionaryModel(){
        super();
    }

    public IEntityManager<T> getEntityManager(){
        return ems;
    }

    public void setEtityManager(IEntityManager<T> ems){
        this.ems = ems;
    }

    public List<T> getAll() throws DataAccessException {

        List<T> results = null;

        if(null != getEntityManager())
        {
            results = getEntityManager().getAll();
            log.debug("success getting all instances.");
        }
        return results;

    }

    public T getById(int id, boolean lock) throws DataAccessException {

        T instance = null;
        if(null != getEntityManager())
        {
            instance = getEntityManager().getById(id, lock);
            log.debug("success getting instance.");
        }
         return instance;
    }

    public void addOrUpdate(T value) {

        if(value == null)
           throw new IllegalArgumentException("Parameter value must be not null");

        if(null != getEntityManager()){
            getEntityManager().addOrUpdate(value);
            notifySubscribers();
        }
        else{
            log.debug("getEntityManager() return null.");
            throw new NullPointerException("getEntityManager() return null");
        }
    }

    public void remove(T value) {

        if(value == null)
           throw new IllegalArgumentException("Parameter value must be not null");

        if(null != getEntityManager()){
            getEntityManager().remove(value);
            notifySubscribers();
        }
        else{
            log.debug("getEntityManager() return null.");
            throw new NullPointerException("getEntityManager() return null");
        }
    }
}

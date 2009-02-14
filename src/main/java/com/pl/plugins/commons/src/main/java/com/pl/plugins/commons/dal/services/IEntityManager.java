package com.pl.plugins.commons.dal.services;

import org.springframework.dao.DataAccessException;
import org.jdesktop.beans.AbstractBean;

import java.util.Collection;
import java.util.List;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: mostovoy.vladislav
 * Date: 18.09.2008
 * Time: 17:48:26
 * To change this template use File | Settings | File Templates.
 */
public interface IEntityManager<T> {

    List<T> getAll() throws DataAccessException;

    T getById(int id, boolean lock) throws DataAccessException;

    void addOrUpdate(T value);
    
    void remove(T value);
}

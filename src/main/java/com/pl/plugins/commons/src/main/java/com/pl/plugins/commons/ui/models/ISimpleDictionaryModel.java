package com.pl.plugins.commons.ui.models;

import com.pl.plugins.commons.dal.services.IEntityManager;

import java.io.Serializable;

import org.jdesktop.beans.AbstractBean;

/**
 * Created by IntelliJ IDEA.
 * User: Mostovoy.Vladislav
 * Date: 22.09.2008
 * Time: 12:50:33
 * To change this template use File | Settings | File Templates.
 */
public interface ISimpleDictionaryModel<T> extends IModel, IEntityManager<T>{

    IEntityManager<T> getEntityManager();
    void setEtityManager(IEntityManager<T> ems);
}

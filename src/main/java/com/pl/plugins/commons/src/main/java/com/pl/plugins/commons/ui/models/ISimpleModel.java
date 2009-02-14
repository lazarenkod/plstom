package com.pl.plugins.commons.ui.models;

import org.jdesktop.beans.AbstractBean;
import com.pl.plugins.commons.dal.dbo.BaseDBO;

/**
 * Created by IntelliJ IDEA.
 * User: Vlad
 * Date: 01.10.2008
 * Time: 0:55:01
 */
public interface ISimpleModel<T extends BaseDBO> extends IModel{

    T getProperty();
    T getMemento();

    void setProperty(T property);

    void reset();
}

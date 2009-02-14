package com.pl.plugins.commons.ui.controllers.impl;

import com.pl.plugins.commons.ui.controllers.IController;
import com.pl.plugins.commons.ui.models.impl.Model;
import com.pl.plugins.commons.ui.models.IModel;
import com.pl.plugins.commons.dal.enums.ModelOperations;
import com.pl.plugins.commons.dal.dbo.BaseDBO;
import org.jdesktop.beans.AbstractBean;

/**
 * Created by IntelliJ IDEA.
 * User: Mostovoy.Vladislav
 * Date: 22.09.2008
 * Time: 9:52:03
 * To change this template use File | Settings | File Templates.
 */
public class Controller<M extends IModel, T extends BaseDBO> implements IController<M, T> {

    private M model = null;

    public M getModel() {
        return model;
    }

    public void setModel(M value) {
        this.model = value; 
    }
}

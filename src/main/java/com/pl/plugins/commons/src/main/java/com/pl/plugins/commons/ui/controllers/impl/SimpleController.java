package com.pl.plugins.commons.ui.controllers.impl;

import com.pl.plugins.commons.dal.enums.ModelOperations;
import com.pl.plugins.commons.dal.dbo.BaseDBO;
import com.pl.plugins.commons.ui.models.ISimpleModel;
import com.pl.plugins.commons.helpers.impl.CloneHelper;
import com.pl.plugins.core.CorePlugin;

/**
 * Created by IntelliJ IDEA.
 * User: Mostovoy.Vladislav
 * Date: 01.10.2008
 * Time: 11:48:55
 * To change this template use File | Settings | File Templates.
 */
public class SimpleController<M extends ISimpleModel, T extends BaseDBO> extends Controller<M, T> {

    public void update(T value){
        getModel().setProperty(value);
    }

     public void reset(){
        getModel().reset();
    }

    public boolean isDataChanged(){
        boolean result = true;

        T previousValue = (T)getModel().getMemento();
        T currentValue = (T)getModel().getProperty();

        if(currentValue == previousValue && currentValue != null &&
           currentValue.equals(previousValue)){

            result = false;
        }

        return result;
    }
}

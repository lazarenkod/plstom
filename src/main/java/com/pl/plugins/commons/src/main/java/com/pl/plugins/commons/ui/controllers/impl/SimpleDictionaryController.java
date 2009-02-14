package com.pl.plugins.commons.ui.controllers.impl;

import com.pl.plugins.commons.dal.enums.ModelOperations;
import com.pl.plugins.commons.dal.dbo.BaseDBO;
import com.pl.plugins.commons.ui.controllers.ISimpleDictionaryController;
import com.pl.plugins.commons.ui.models.impl.SimpleDictionaryModel;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Mostovoy.Vladislav
 * Date: 22.09.2008
 * Time: 9:52:03
 * To change this template use File | Settings | File Templates.
 */

/**
 * Use  SimpleDictionaryController (SimpleDictionaryModel,BaseDBO)
 * 
 */
public class SimpleDictionaryController<M extends SimpleDictionaryModel, T extends BaseDBO>
                                          extends Controller<M, T>
                                          implements ISimpleDictionaryController<M, T> {
    public void addOrUpdate(T value){
        getModel().addOrUpdate(value);
    }

    public void remove(T value){
        getModel().remove(value);
    }

    public List<T> getAll(){
        return getModel().getAll();
    }

    public T getById(int id){
        return (T)getModel().getById(id, true);
    }
}

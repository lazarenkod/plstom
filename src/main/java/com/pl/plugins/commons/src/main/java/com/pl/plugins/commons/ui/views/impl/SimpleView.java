package com.pl.plugins.commons.ui.views.impl;

import com.pl.plugins.commons.ui.models.ISimpleModel;
import com.pl.plugins.commons.ui.models.ISimpleDictionaryModel;
import com.pl.plugins.commons.ui.controllers.IController;
import com.pl.plugins.commons.ui.controllers.ISimpleDictionaryController;
import com.pl.plugins.commons.dal.dbo.BaseDBO;

/**
 * Created by IntelliJ IDEA.
 * User: Vlad
 * Date: 04.10.2008
 * Time: 20:10:45
 */
public class SimpleView<M extends ISimpleModel, C extends IController>
                          extends View<M, C>{

    private ISimpleDictionaryController sdc = null;

    public SimpleView(M model, C controller) {
        super(model, controller);
    }

    public SimpleView() {
    }

    public ISimpleDictionaryController getSdc() {
        return sdc;
    }

    public void setSdc(ISimpleDictionaryController sdc) {
        this.sdc = sdc;
    }

    /**
     * ѕерезагружает свойства модели из репозитори€.
     */
    public void reload(){

        if(sdc != null){
            
            getModel().setProperty((BaseDBO) ((ISimpleDictionaryModel) sdc.getModel()).
                       getById(getModel().getProperty().getId(), false));
        }
    }
}

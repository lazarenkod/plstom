package com.pl.plugins.commons.ui.controllers;

import com.pl.plugins.commons.ui.models.impl.SimpleDictionaryModel;
import com.pl.plugins.commons.ui.models.impl.Model;
import com.pl.plugins.commons.ui.models.IModel;
import com.pl.plugins.commons.dal.enums.ModelOperations;
import com.pl.plugins.commons.dal.dbo.BaseDBO;

/**
 * Created by IntelliJ IDEA.
 * User: Mostovoy.Vladislav
 * Date: 22.09.2008
 * Time: 9:51:20
 * Абстрактный контроллер
 * @param <M> модель
 */
public interface IController<M extends IModel, T extends BaseDBO> {

    M getModel();
    void setModel(M value);
}

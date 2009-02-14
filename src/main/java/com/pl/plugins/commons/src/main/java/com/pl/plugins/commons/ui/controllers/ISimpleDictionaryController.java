package com.pl.plugins.commons.ui.controllers;

import com.pl.plugins.commons.dal.enums.ModelOperations;
import com.pl.plugins.commons.dal.dbo.BaseDBO;
import com.pl.plugins.commons.ui.models.impl.SimpleDictionaryModel;

/**
 * Created by IntelliJ IDEA.
 * User: Mostovoy.Vladislav
 * Date: 22.09.2008
 * Time: 9:51:20
 * јбстрактный контроллер
 * @param <M> модель
 * @param <T> тип данных модели
 */
public interface ISimpleDictionaryController<M extends SimpleDictionaryModel, T extends BaseDBO> extends IController<M, T>{
	
}

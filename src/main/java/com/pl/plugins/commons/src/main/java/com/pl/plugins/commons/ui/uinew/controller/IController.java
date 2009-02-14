package com.pl.plugins.commons.ui.uinew.controller;

import com.pl.plugins.commons.ui.uinew.model.impl.Model;

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 31.01.2009
 * Time: 14:47:05
 */
public interface IController <O, M extends Model<P>, P> {
	/**
	 * Выполнить
	 * @param operation операция
	 * @param model модель
	 * @param attribute атрибут операции
	 */
	void execute(O operation, M model, P attribute);

    /**
     * Выполнить загрузку данных
     */
    void loadData();
    
}

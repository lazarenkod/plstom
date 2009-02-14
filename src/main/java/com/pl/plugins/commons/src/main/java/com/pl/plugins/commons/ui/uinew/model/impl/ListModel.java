package com.pl.plugins.commons.ui.uinew.model.impl;

import com.pl.plugins.commons.ui.uinew.model.IModelSubscriber;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 31.01.2009
 * Time: 13:06:27
 */
public class ListModel <P> extends Model<Collection<Model<P>>> implements
        IModelSubscriber<P> {
	/**
	 * Конструктор
	 */
	public ListModel() {
		super(new HashSet<Model<P>>());
	}

	/**
	 * Добавить модель
	 * @param model модель
	 */
	public void add(Model<P> model) {
		if (model == null)
			throw new NullPointerException("Пустой параметр");
		getProperty().add(model);
		model.subscribe(this);
	}

	@Override
	public void dataChanged(Model<P> model) {
		if (model == null)
			throw new NullPointerException("Пустой параметр");
		notifySubscribers();
	}

	/**
	 * Удалить модель
	 * @param model модель
	 */
	public void remove(Model<P> model) {
		if (model == null)
			throw new NullPointerException("Пустой параметр");
		model.unsubscribe(this);
		getProperty().remove(model);
		notifySubscribers();
	}
}
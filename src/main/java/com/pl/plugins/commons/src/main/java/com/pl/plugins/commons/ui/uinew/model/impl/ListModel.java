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
	 * �����������
	 */
	public ListModel() {
		super(new HashSet<Model<P>>());
	}

	/**
	 * �������� ������
	 * @param model ������
	 */
	public void add(Model<P> model) {
		if (model == null)
			throw new NullPointerException("������ ��������");
		getProperty().add(model);
		model.subscribe(this);
	}

	@Override
	public void dataChanged(Model<P> model) {
		if (model == null)
			throw new NullPointerException("������ ��������");
		notifySubscribers();
	}

	/**
	 * ������� ������
	 * @param model ������
	 */
	public void remove(Model<P> model) {
		if (model == null)
			throw new NullPointerException("������ ��������");
		model.unsubscribe(this);
		getProperty().remove(model);
		notifySubscribers();
	}
}
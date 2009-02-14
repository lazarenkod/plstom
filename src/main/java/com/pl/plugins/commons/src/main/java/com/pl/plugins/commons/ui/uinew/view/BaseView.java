package com.pl.plugins.commons.ui.uinew.view;

import com.pl.plugins.commons.ui.uinew.model.IModelSubscriber;
import com.pl.plugins.commons.ui.uinew.model.impl.Model;

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 31.01.2009
 * Time: 19:22:06
 */
/**
 * ������� �������������
 * @param <M> ������
 * @param
 * <P>
 * �������� ������
 */
public abstract class BaseView<M extends Model<P>, P> implements
        IModelSubscriber<P> {
	private M model;

	/**
	 * �������� ������
	 * @return ������
	 */
	protected M getModel() {
		return model;
	}

	/**
	 * ���������� ������
	 * @param model ������
	 */
	public void setModel(M model) {
		unsubscribe();
		this.model = model;
		subscribe();
	}

	/**
	 * ����������� �� ������� ������
	 */
	private void subscribe() {
		if (model != null)
			model.subscribe(this);
	}

	/**
	 * ����� �������� � ������
	 */
	private void unsubscribe() {
		if (model != null)
			model.unsubscribe(this);
	}

	/**
	 * ����������� ��������
	 */
	public void dispose() {
		unsubscribe();
	}
}
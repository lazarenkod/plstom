package com.pl.plugins.commons.ui.uinew.view;

import com.pl.plugins.commons.ui.uinew.controller.impl.Controller;
import com.pl.plugins.commons.ui.uinew.controller.Operation;
import com.pl.plugins.commons.ui.uinew.model.impl.Model;

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 31.01.2009
 * Time: 19:22:50
 */
/**
 * ���������������� �������������
 * @param
 * <P>
 * �������� ������
 */
public abstract class View<P> extends BaseView<Model<P>, P> {
	private final Controller<P> controller = new Controller<P>();

	/**
	 * ������������� ������
	 * @param property �������� ������
	 */
	protected void edit(P property) {
		controller.execute(Operation.EDIT, getModel(), property);
	}

	/**
	 * �������� ��������� ��������
	 */
	protected void undo() {
		controller.execute(Operation.UNDO, getModel(), null);
	}
}
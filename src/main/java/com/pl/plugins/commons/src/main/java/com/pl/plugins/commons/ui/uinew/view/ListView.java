package com.pl.plugins.commons.ui.uinew.view;

import com.pl.plugins.commons.ui.uinew.model.impl.Model;
import com.pl.plugins.commons.ui.uinew.model.impl.ListModel;
import com.pl.plugins.commons.ui.uinew.controller.impl.Controller;
import com.pl.plugins.commons.ui.uinew.controller.impl.ListController;
import com.pl.plugins.commons.ui.uinew.controller.Operation;

import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 31.01.2009
 * Time: 19:23:56
 */
/**
 * Интеллектуальное представление списка
 * @param
 * <P>
 * свойство модели
 */
public abstract class ListView<P> extends
		BaseView<ListModel<P>, Collection<Model<P>>> {
	private final Controller<P> controller = new Controller<P>();

	private final ListController<P> listController = new ListController<P>();

	/**
	 * Редактировать модель
	 * @param model модель
	 * @param property свойство модели
	 */
	protected void edit(Model<P> model, P property) {
		controller.execute(Operation.EDIT, model, property);
	}

	/**
	 * Добавить модель
	 * @param model модель
	 */
	protected void add(Model<P> model) {
		listController.execute(Operation.ADD, getModel(), model);
	}

	/**
	 * Удалить модель
	 * @param model модель
	 */
	protected void delete(Model<P> model) {
		listController.execute(Operation.REMOVE, getModel(), model);
	}

	/**
	 * Отменить последнее действие
	 */
	protected void undo() {
		final Model<P> model = null;
		listController.execute(Operation.UNDO, getModel(), model);
	}
}
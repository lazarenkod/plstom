package com.pl.plugins.commons.ui.uinew.controller.impl;

import com.pl.plugins.commons.ui.uinew.model.impl.Model;
import com.pl.plugins.commons.ui.uinew.model.impl.ListModel;
import com.pl.plugins.commons.ui.uinew.controller.IController;
import com.pl.plugins.commons.ui.uinew.controller.Operation;

import java.util.Collection;
import java.util.Stack;
import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 31.01.2009
 * Time: 17:03:59
 */
/**
 * Интеллектуальный контроллер списка
 * @param
 * <P>
 * свойство модели
 */
public class ListController<P> implements IController<Operation, ListModel<P>, Collection<Model<P>>> {

    /**
	 * Элемент истории
	 */
	private class HistoryItem {
		private final Operation operation;

		private final Model<P> model;

		/**
		 * Конструктор
		 * @param operation операция над моделью списка
		 * @param model модель
		 */
		public HistoryItem(Operation operation, Model<P> model) {
			if (operation == null)
				throw new NullPointerException("Пустой параметр operation");
			if (model == null)
				throw new NullPointerException("Пустой параметр model");
			this.operation = operation;
			this.model = model;
		}
	}

	private final Stack<HistoryItem> history = new Stack<HistoryItem>();

	public void execute(Operation operation, ListModel<P> model,
			Collection<Model<P>> attribute) {
		if (operation == null)
			throw new NullPointerException("Пустой параметр operation");
		if (model == null)
			throw new NullPointerException("Пустой параметр model");
		switch (operation) {
		case ADD:
			if (attribute == null)
				throw new NullPointerException("Пустой параметр attribute");
			for (final Model<P> _model : attribute) {
				history.push(new HistoryItem(operation, _model));
				model.add(_model);
			}
			break;
		case REMOVE:
			if (attribute == null)
				throw new NullPointerException("Пустой параметр attribute");
			for (final Model<P> _model : attribute) {
				history.push(new HistoryItem(operation, _model));
				model.remove(_model);
			}
			break;
		case UNDO:
			if (!history.empty()) {
				final HistoryItem item = history.pop();
				switch (item.operation) {
				case ADD:
					model.remove(item.model);
					break;
				case REMOVE:
					model.add(item.model);
					break;
				default:
					throw new IllegalArgumentException(
							"Неизвестная операция: " + item.operation);
				}
			}
			break;
		default:
			throw new IllegalArgumentException("Неизвестная операция: " +
					operation);
		}
	}

    public void loadData() {

    }

    @SuppressWarnings("unchecked")
	public void execute(Operation operation, ListModel<P> model, Model<P> attribute) {
		execute(operation, model, Arrays.asList(attribute));
	}
}

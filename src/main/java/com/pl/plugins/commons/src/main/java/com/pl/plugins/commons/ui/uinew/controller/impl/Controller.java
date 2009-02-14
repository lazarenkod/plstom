package com.pl.plugins.commons.ui.uinew.controller.impl;

import com.pl.plugins.commons.ui.uinew.model.impl.Model;
import com.pl.plugins.commons.ui.uinew.controller.IController;
import com.pl.plugins.commons.ui.uinew.controller.Operation;

import java.util.Stack;

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 31.01.2009
 * Time: 14:48:30
 */

/**
 * Контроллер
 *
 * @param <P>
 * свойство модели
 */
public  class Controller<P> implements IController<Operation, Model<P>, P> {

    private final Stack<P> history = new Stack<P>();

    
    public void execute(Operation operation, Model<P> model, P attribute) {
        if (operation == null)
            throw new NullPointerException("Пустой параметр operation");
        if (model == null)
            throw new NullPointerException("Пустой параметр model");
        switch (operation) {
            case EDIT:
                if (attribute == null)
                    throw new NullPointerException("Пустой параметр attribute");
                if (!model.getProperty().equals(attribute)) {
                    history.push(model.getProperty());
                    model.setProperty(attribute);
                }
                break;
            case UNDO:
                if (!history.empty()) {
                    final P property = history.pop();
                    if (!model.getProperty().equals(property))
                        model.setProperty(property);
                }
                break;
            case SAVE:

                break;
            default:
                throw new IllegalArgumentException("Неизвестная операция: " +
                        operation);
        }
    }

    public void loadData() {

    }
}
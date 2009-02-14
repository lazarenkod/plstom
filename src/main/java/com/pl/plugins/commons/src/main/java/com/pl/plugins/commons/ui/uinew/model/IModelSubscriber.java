package com.pl.plugins.commons.ui.uinew.model;

import com.pl.plugins.commons.ui.uinew.model.impl.Model;

/**
 * Created by IntelliJ IDEA.
 * User: Администратор
 * Date: 10.09.2008
 * Time: 8:23:43
 */
public interface IModelSubscriber<P> {
    void dataChanged(Model<P> model);
}
package com.pl.plugins.commons.ui.views;

import com.pl.plugins.commons.ui.controllers.IController;
import com.pl.plugins.commons.ui.models.IModel;
import com.pl.plugins.commons.ui.models.ISubscriber;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: Mostovoy.Vladislav
 * Date: 22.09.2008
 * Time: 12:09:21
 */
public interface IView<M extends IModel, C extends IController> extends ISubscriber{

    M getModel();
    void setModel(M value);

    C getController();
    void setControlle(C value);

    JToolBar getToolBar();
    void setToolBar(JToolBar value);

    //void bind();

    void onShow();
    void onHide();

    void reload();
}

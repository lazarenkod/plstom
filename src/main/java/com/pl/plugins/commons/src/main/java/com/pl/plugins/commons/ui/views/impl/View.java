package com.pl.plugins.commons.ui.views.impl;

import com.pl.plugins.commons.ui.controllers.IController;
import com.pl.plugins.commons.ui.models.IModel;
import com.pl.plugins.commons.ui.views.IView;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

import org.jdesktop.beansbinding.BindingGroup;

/**
 * Created by IntelliJ IDEA.
 * User: Mostovoy.Vladislav
 * Date: 22.09.2008
 * Time: 16:16:05
  */
public class View<M extends IModel, C extends IController> extends JPanel implements IView<M, C> {

    private M model = null;
    private C controller = null;

    private JToolBar toolBar = null;

    private boolean isBindOnce = false;

    protected BindingGroup bindingGroup;

    public View(){
       this(null, null);
    }

    public View(M model, C controller){

        this.controller = controller;

        setModel(model);

        bindingGroup = new BindingGroup();
    }

    public M getModel() {
        return model;
    }

    public void setModel(M value) {
        model = value;

        if(controller != null){
            controller.setModel(model);
        }

        bind();
    }

    public C getController() {
        return controller;
    }

    public void setControlle(C value) {
        controller = value;
    }

    public JToolBar getToolBar() {
        return toolBar;
    }

    public void setToolBar(JToolBar value) {
        this.toolBar = value;
    }

    protected void bind() {

        if(!isBindOnce){
            isBindOnce = bindOnce();
        }
    }

    protected boolean bindOnce(){
        return false;
    }

    public void onShow() {
        getModel().subscribe(this);
    }

    public void onHide() {
        getModel().unsubscribe(this);
    }

    public void dataChanged(Object source) {
//        reload();
        bind();
    }

    public void reload(){
        
    }
}

package com.pl.plugins.commons.ui.uinew.core;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.jgoodies.binding.PresentationModel;
import com.pl.plugins.commons.ui.uinew.domain.view.validation.SimplePresentationModel;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 13.02.2009
 * Time: 10:47:44
 */
public abstract class AbstractPanel<C>
        extends JPanel
        implements VisualForm {

    private C controler;

    private AbstractTopComponent ownerTopComponent;

    private PresentationModel presentationModel;

    protected Log log = LogFactory.getLog(getClass().getName());

/*    public AbstractPanel(AbstractBindingModelDTO dto) {
    presentationModel = new SimplePresentationModel(dto, this);
}*/

    public AbstractPanel(AbstractTopComponent ownerTopComponent) {
        super();
        this.ownerTopComponent = ownerTopComponent;
        this.presentationModel = ownerTopComponent.getPresentationModel();
    }

    public AbstractPanel() {
        super();
    }

    /**
     * Must be override
     *
     * @return DTO who binded to this panel components.
     */
    public AbstractBindingModelDTO getModelDTO() {
        return null;
    }


    /**
     * this method for init all editable and binded components.
     */
    protected void bindComponents() {
    }


    public void updatePanel(Object object) {
        if (object == null) {
            return;
        }
        if (getPresentationModel() == null) {
            create(object);
        } else {
            refresh(object);
        }
    }

    /**
     * Override it for updating panel
     *
     * @param object
     */
    protected void refresh(Object object) {

    }

    /**
     * Override it for init panel
     *
     * @param object
     */
    protected void create(Object object) {

    }


    public void doOpen() {
    }


    public void doEdit() {
    }


    public void doView() {
    }


    public void doClose() {
    }


    public void open(Object data) {
    }


    public void edit(Object data) {
    }


    public void view(Object data) {
    }


    public void close(Object data) {
    }


    public C getController() {
        return controler == null ? (C) (ownerTopComponent != null ? ownerTopComponent.getController() : null) : controler;
    }


    public void setController(Object controller) {
        this.controler = (C) controller;
    }


    public void setPresentationModel(
            SimplePresentationModel presentationModel) {
        this.presentationModel = presentationModel;
    }

    public SimplePresentationModel getPresentationModel() {
        return (SimplePresentationModel) presentationModel;
    }

    protected void setEnableControls(boolean hasError) {
    }

    public void setNewDTO(AbstractBindingModelDTO dto) {
        getPresentationModel().setBean(dto);
    }

    public AbstractTopComponent getOwnerTopComponent() {
        return ownerTopComponent;
    }

    public void setOwnerTopComponent(AbstractTopComponent ownerTopComponent) {
        this.ownerTopComponent = ownerTopComponent;
        this.presentationModel = ownerTopComponent.getPresentationModel();
    }
}

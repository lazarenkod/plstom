package com.pl.plugins.commons.ui.uinew.core;

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 12.02.2009
 * Time: 16:45:03
 */

import com.jgoodies.binding.PresentationModel;
import com.pl.plugins.commons.ui.uinew.domain.view.i18n.NbBundle;
import com.pl.plugins.commons.ui.uinew.domain.view.validation.SimplePresentationModel;
import com.pl.plugins.commons.ui.uinew.domain.view.validation.ValidationUtils;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.MissingResourceException;


public class AbstractTopComponent<C extends AbstractController>
        extends JPanel
        implements VisualForm {

    protected C controller = (C) new DefaultController();

    private boolean isCloseAlreadyChecked = false;

    private JToolBar toolBar = null;

    private boolean isBindOnce = false;

    public AbstractTopComponent() {
    }

    public AbstractTopComponent(C controller) {
        this.controller = controller;
    }

    protected void createValidationResultWindow(SimplePresentationModel model) {
        ValidationUtils.createValidationResultWindow(model, this);
    }


    protected void activateValidationResultWindow() {
        ValidationUtils.activateValidationResultWindow(this);
    }


    protected void removeValidationResultWindow() {
        ValidationUtils.removeValidationResultWindow(this);
    }


    protected void componentActivated() {
        ValidationUtils.activateValidationResultWindow(this);
    }

    /**
     * Override this method for disable components depends on validation
     *
     * @param validationSuccess - true if validation success.
     */
    protected void setEnabledComponents(boolean validationSuccess) {

    }

    public JPanel getViewComponent() {
        return this;
    }

    public JToolBar getToolBar() {
        return toolBar;
    }

    public void setToolBar(JToolBar value) {
        this.toolBar = value;
    }

    public void bind() {

    }

    public void open() {
//        super.open(); fixme
        isCloseAlreadyChecked = false;
    }


    public boolean canClose() {
        isCloseAlreadyChecked = checkDataChanged();
        return isCloseAlreadyChecked;
    }


    /**
     * ѕроверка, изменились ли данные на форме или нет. ¬ случае, если изменились,
     * выдаетс€ окно, закрывать форму или нет.
     *
     * @return true если окно можно закрывать. false  в противном случае
     */
    private boolean checkDataChanged() {
        if (isCloseAlreadyChecked) {
            return true;
        }
        boolean result = true;
//        if (isDataChanged()) { fixme
//            Object dialogResult = showCloseConfirmWindow();
//            result = dialogResult.equals(NotifyDescriptor.YES_OPTION);
//            if (!result) {
//                this.requestActive();
//            }
//        }
        return result;
    }

    protected boolean isDataChanged() {
        return getController().isDataChanged();
    }


    /**
     * ѕоказ окна с подтверждением о закрытии
     *
     * @return результат, полученный от окна
     */
    protected Object showCloseConfirmWindow() {
//        return DialogUtil.showCloseConfirmationDialog();fixme
        return null;
    }


    protected void componentClosed() {
        removeValidationResultWindow();
//        super.componentClosed(); fixme
    }

    private java.util.List<AbstractPanel> child(Container parent) {
        java.util.List<AbstractPanel> abstractPanels = new LinkedList<AbstractPanel>();
        Component[] childComponents = parent.getComponents();
        for (Component c : childComponents) {
            if (c instanceof Container) {
                if (c instanceof AbstractPanel)
                    abstractPanels.add((AbstractPanel) c);
                abstractPanels.addAll(child((Container) c));
            }
        }
        return abstractPanels;
    }

    /**
     * {@inheritDoc}
     *
     * @param data
     */
    public void open(Object data) {
        doOpen();
        for (AbstractPanel abstractPanel : child(this)) {
            abstractPanel.doOpen();
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param data
     */
    public void edit(Object data) {
        doEdit();
        for (AbstractPanel abstractPanel : child(this)) {
            abstractPanel.doEdit();
        }
    }


    /**
     * {@inheritDoc}
     *
     * @param data
     */
    public void view(Object data) {
        doView();
        for (AbstractPanel abstractPanel : child(this)) {
            abstractPanel.doView();
        }
    }


    /**
     * {@inheritDoc}
     *
     * @param data
     */
    public void close(Object data) {
        doClose();
        for (AbstractPanel abstractPanel : child(this)) {
            abstractPanel.doClose();
        }
    }


    /**
     * {@inheritDoc}
     */
    public void doOpen() {
    }

    /**
     * {@inheritDoc}
     */
    public void doEdit() {
    }


    /**
     * {@inheritDoc}
     */
    public void doView() {
    }


    /**
     * {@inheritDoc}
     */
    public void doClose() {
    }


    public PresentationModel getPresentationModel() {
        return null;
    }


    /**
     * ѕолучение контроллера формы
     *
     * @return
     */
    public C getController() {
        return controller;
    }


    public void setController(Object controller) {
        this.controller = (C) controller;
    }

    public AbstractController.OpenMode getOpenMode() {
        return this.controller.getOpenMode();
    }

    @Override
    public String getName() {
        String name = super.getName();
        if (controller == null || controller instanceof DefaultController || controller.getOpenMode() == null)
            return super.getName();
        try {
            name = NbBundle.getMessage(this.getClass(), "CTL_" + this.getClass().getSimpleName() + "." +
                    getOpenMode().toString());
        } catch (MissingResourceException ignored) {
        }
        return name;
    }

    @Override
    public String getToolTipText() {
        String toolTipText = super.getToolTipText();
        if (controller == null || controller instanceof DefaultController || controller.getOpenMode() == null)
            return super.getName();
        try {
            toolTipText = NbBundle.getMessage(this.getClass(), "HINT_" + this.getClass().getSimpleName() + "." +
                    getOpenMode().toString());
        } catch (MissingResourceException ignored) {
        }
        return toolTipText;
    }
}

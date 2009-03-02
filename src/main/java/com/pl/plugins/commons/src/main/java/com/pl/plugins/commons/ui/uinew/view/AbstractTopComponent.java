package com.pl.plugins.commons.ui.uinew.view;

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 12.02.2009
 * Time: 16:45:03
 */

import com.jgoodies.binding.PresentationModel;
import com.pl.plugins.commons.ui.uinew.domain.view.DialogResult;
import com.pl.plugins.commons.ui.uinew.domain.view.DialogUtils;
import com.pl.plugins.commons.ui.uinew.domain.view.i18n.NbBundle;
import com.pl.plugins.commons.ui.uinew.domain.view.validation.SimplePresentationModel;
import com.pl.plugins.commons.ui.uinew.domain.view.validation.ValidationUtils;
import com.pl.plugins.commons.ui.uinew.controller.AbstractController;
import com.pl.plugins.commons.ui.uinew.controller.DefaultController;
import com.pl.plugins.core.ui.TopComponent;

import javax.swing.JPanel;
import javax.swing.JToolBar;
import java.util.MissingResourceException;


public class AbstractTopComponent<C extends AbstractController>
        extends TopComponent
        implements VisualForm {

    protected C controller = (C) new DefaultController();

    private boolean isCloseAlreadyChecked = false;

    private JToolBar toolBar = null;

    public AbstractTopComponent() {
    }

    public AbstractTopComponent(C controller) {
        this.controller = controller;
    }

//    protected void createValidationResultWindow(SimplePresentationModel model) {
//        ValidationUtils.createValidationResultWindow(model, this);
//    }
//
//
//    protected void activateValidationResultWindow() {
//        ValidationUtils.activateValidationResultWindow(this);
//    }
//
//
//    protected void removeValidationResultWindow() {
//        ValidationUtils.removeValidationResultWindow(this);
//    }


    protected void componentActivated() {
//        ValidationUtils.activateValidationResultWindow(this);
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

    /**
     * Ќужно об€зательно переопредел€ть этот метод
     */
    public void bind() {

    }

    public void open() {
        super.open(); 
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
        if (isDataChanged()) {
            DialogResult dialogResult = showCloseConfirmWindow();
            result = dialogResult.equals(DialogResult.YES);
            if (!result) {
                this.requestActive();
            }
        }
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
    protected DialogResult showCloseConfirmWindow() {
        return DialogUtils.showCloseConfirmationDialog();

    }

    public void initListeneres(){
        
    }
    
    protected void componentClosed() {
//        removeValidationResultWindow();
        //super.close(); 
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

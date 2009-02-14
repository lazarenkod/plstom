package com.pl.plugins.commons.ui.uinew.domain.view.validation;

import com.pl.plugins.commons.ui.uinew.domain.view.validation.ui.ValidationResultFormTopComponent;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.Component;

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 13.02.2009
 * Time: 12:04:56
 */
public class ValidationUtils {

    private static class ValidationResultViewWrapper extends JPanel {
        private JComponent refComponent;

        private ValidationResultViewWrapper(JComponent validationResultView, JComponent refComponent) {
            super();
            this.refComponent = refComponent;
            setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));
            add(validationResultView);
            validationResultView.setVisible(true);
        }

        public JComponent getRefComponent() {
            return refComponent;
        }
    }

    /**
     * Поиск вкладки
     *
     * @param jComponent компонент, для которого открыто окно валидации
     * @return индекс вкладки
     */
    private static int indexOfTab(JComponent jComponent) {
        ValidationResultFormTopComponent win = ValidationResultFormTopComponent.findInstance();
        Component[] components = win.getContentPane().getComponents();
        for (Component component : components) {
            if (component instanceof ValidationResultViewWrapper) {
                if (((ValidationResultViewWrapper) component).getRefComponent() == jComponent) {
                    return win.getContentPane().indexOfComponent(component);
                }
            }
        }
        return -1;
    }

    /**
     * Помещает в окно валидации компонент содержащий результаты валидации для
     * заданной SimplePresentationModel. Если окно валидации не было создано, или
     * было скрыто, оно создается и активируется автоматически.
     * <p/>
     * Обычно должен вызываться из TopComponent.componentOpened()
     *
     * @param model      модель
     * @param jComponent компонент, для которого открыто окно валидации
     */
    public static void createValidationResultWindow(SimplePresentationModel model, JComponent jComponent) {
        ValidationResultFormTopComponent win = ValidationResultFormTopComponent.findInstance();
        int index = indexOfTab(jComponent);
        if (index == -1) {
            ValidationResultViewWrapper resultViewWrapper = new ValidationResultViewWrapper(model.getValidationResultView(), jComponent);
            win.getContentPane().add(resultViewWrapper, jComponent.getName());
            win.getContentPane().setSelectedIndex(win.getContentPane().getTabCount() - 1);
        }
//        win.open();          fixme
//        win.requestActive();
    }

    /**
     * Активирует (выводит на передний план) существующую закладку с ошибками валидации в окне валидации.
     * Если окно в ошибками валидации было закрыто оно открывается.
     * <p/>
     * Обычно должен вызываться из TopComponent.componentActivated()
     *
     * @param jComponent компонент, для которого открыто окно валидации
     */
    public static void activateValidationResultWindow(JComponent jComponent) {
        ValidationResultFormTopComponent win = ValidationResultFormTopComponent.findInstance();
        win.setVisible(true);
        int index = indexOfTab(jComponent);
        if (index != -1) {
            win.getContentPane().setSelectedIndex(index);
        }
    }

    /**
     * Удаляет закладку с ошибками валидации из окна валидации.
     * <p/>
     * Обычно должен вызываться из TopComponent.componentClosed()
     *
     * @param jComponent компонент, для которого открыто окно валидации
     */
    public static void removeValidationResultWindow(JComponent jComponent) {
        ValidationResultFormTopComponent win = ValidationResultFormTopComponent.findInstance();
        int index = indexOfTab(jComponent);
        if (index != -1) {
            win.getContentPane().removeTabAt(index);
        }
    }

}

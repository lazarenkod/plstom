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
     * ����� �������
     *
     * @param jComponent ���������, ��� �������� ������� ���� ���������
     * @return ������ �������
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
     * �������� � ���� ��������� ��������� ���������� ���������� ��������� ���
     * �������� SimplePresentationModel. ���� ���� ��������� �� ���� �������, ���
     * ���� ������, ��� ��������� � ������������ �������������.
     * <p/>
     * ������ ������ ���������� �� TopComponent.componentOpened()
     *
     * @param model      ������
     * @param jComponent ���������, ��� �������� ������� ���� ���������
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
     * ���������� (������� �� �������� ����) ������������ �������� � �������� ��������� � ���� ���������.
     * ���� ���� � �������� ��������� ���� ������� ��� �����������.
     * <p/>
     * ������ ������ ���������� �� TopComponent.componentActivated()
     *
     * @param jComponent ���������, ��� �������� ������� ���� ���������
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
     * ������� �������� � �������� ��������� �� ���� ���������.
     * <p/>
     * ������ ������ ���������� �� TopComponent.componentClosed()
     *
     * @param jComponent ���������, ��� �������� ������� ���� ���������
     */
    public static void removeValidationResultWindow(JComponent jComponent) {
        ValidationResultFormTopComponent win = ValidationResultFormTopComponent.findInstance();
        int index = indexOfTab(jComponent);
        if (index != -1) {
            win.getContentPane().removeTabAt(index);
        }
    }

}

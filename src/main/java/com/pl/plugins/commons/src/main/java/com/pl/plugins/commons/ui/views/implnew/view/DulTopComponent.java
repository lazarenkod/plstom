/*
 * Created by JFormDesigner on Sun Mar 01 02:28:47 MSK 2009
 */

package com.pl.plugins.commons.ui.views.implnew.view;

import com.pl.plugins.commons.dal.dbo.DulDBO;
import com.pl.plugins.commons.ui.uinew.view.AbstractTopComponent;
import com.pl.plugins.commons.ui.views.implnew.controller.DulController;
import com.toedter.calendar.JDateChooser;
import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.layout.GroupLayout;
import org.jdesktop.layout.LayoutStyle;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

/**
 * @author Brainrain
 */
public class DulTopComponent extends AbstractTopComponent<DulController> {
    private DulDBO decorator;

    public DulTopComponent() {
        initComponents();
        bind();
        initListeneres();
        setName("namecvvfgcdfdrвав");
        setToolTipText("text");
    }

    @Override
    public void bind() {
        decorator= (DulDBO) controller.getDataObject();
        Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE,
                 decorator, BeanProperty.create(DulDBO.TYPE),
                 jtfType, BeanProperty.create("text")).bind();

        Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE,
                 decorator, BeanProperty.create(DulDBO.SERIES),
                 jtfSeries, BeanProperty.create("text")).bind();

        Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE,
                 decorator, BeanProperty.create(DulDBO.NUM),
                 jtfNum, BeanProperty.create("text")).bind();

        Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE,
                 decorator, BeanProperty.create(DulDBO.ISSUE_DATE),
                 jdcIssuerDate, BeanProperty.create("date")).bind();

        Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE,
                 decorator, BeanProperty.create(DulDBO.ISSUE_DIVISION),
                 jtfIssuerCode, BeanProperty.create("text")).bind();

        Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE,
                 decorator, BeanProperty.create(DulDBO.ISSUER),
                 jtfIssuerName, BeanProperty.create("date")).bind();
    }

    @Override
    public void doOpen() {
    }

    @Override
    public void doView() {
    }

    @Override
    public void doEdit() {
    }

    @Override
    public void initListeneres() {
    }

    private void buttonSaveActionPerformed(ActionEvent e) {
       // sds
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        ResourceBundle bundle = ResourceBundle.getBundle("com.pl.plugins.commons.ui.resources");
        dulPanel = new JPanel();
        label1 = new JLabel();
        jtfSeries = new JTextField();
        label2 = new JLabel();
        jtfNum = new JTextField();
        jdcIssuerDate = new JDateChooser();
        label3 = new JLabel();
        label4 = new JLabel();
        jtfIssuerCode = new JTextField();
        label5 = new JLabel();
        jtfIssuerName = new JTextField();
        label6 = new JLabel();
        jtfType = new JTextField();
        buttonSave = new JButton();
        button3 = new JButton();

        //======== this ========

        //======== dulPanel ========
        {
            dulPanel.setBorder(new TitledBorder(null, bundle.getString("DulTopComponent.dulPanel.border"), TitledBorder.LEADING, TitledBorder.TOP));
            dulPanel.setMinimumSize(new Dimension(300, 210));

            //---- label1 ----
            label1.setText(bundle.getString("DulTopComponent.label1.text"));

            //---- label2 ----
            label2.setText(bundle.getString("DulTopComponent.label2.text"));

            //---- label3 ----
            label3.setText(bundle.getString("DulTopComponent.label3.text"));

            //---- label4 ----
            label4.setText(bundle.getString("DulTopComponent.label4.text"));

            //---- label5 ----
            label5.setText(bundle.getString("DulTopComponent.label5.text"));

            //---- label6 ----
            label6.setText(bundle.getString("DulTopComponent.label6.text"));

            GroupLayout dulPanelLayout = new GroupLayout(dulPanel);
            dulPanel.setLayout(dulPanelLayout);
            dulPanelLayout.setHorizontalGroup(
                dulPanelLayout.createParallelGroup()
                    .add(dulPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .add(dulPanelLayout.createParallelGroup()
                            .add(dulPanelLayout.createSequentialGroup()
                                .add(label6)
                                .add(148, 148, 148))
                            .add(dulPanelLayout.createSequentialGroup()
                                .add(dulPanelLayout.createParallelGroup()
                                    .add(GroupLayout.TRAILING, jtfIssuerName, GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
                                    .add(GroupLayout.TRAILING, dulPanelLayout.createSequentialGroup()
                                        .add(dulPanelLayout.createParallelGroup()
                                            .add(dulPanelLayout.createParallelGroup(GroupLayout.TRAILING)
                                                .add(GroupLayout.LEADING, jdcIssuerDate, GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                                                .add(GroupLayout.LEADING, label1)
                                                .add(GroupLayout.LEADING, jtfSeries, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE))
                                            .add(label3))
                                        .add(50, 50, 50)
                                        .add(dulPanelLayout.createParallelGroup()
                                            .add(label2)
                                            .add(jtfNum, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                                            .add(label4, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                                            .add(jtfIssuerCode, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)))
                                    .add(label5, GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE))
                                .addContainerGap())
                            .add(jtfType, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)))
            );
            dulPanelLayout.setVerticalGroup(
                dulPanelLayout.createParallelGroup()
                    .add(GroupLayout.TRAILING, dulPanelLayout.createSequentialGroup()
                        .add(label6)
                        .addPreferredGap(LayoutStyle.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(jtfType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.RELATED)
                        .add(dulPanelLayout.createParallelGroup(GroupLayout.BASELINE)
                            .add(label2)
                            .add(label1))
                        .addPreferredGap(LayoutStyle.RELATED)
                        .add(dulPanelLayout.createParallelGroup(GroupLayout.BASELINE)
                            .add(jtfNum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .add(jtfSeries, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.RELATED)
                        .add(dulPanelLayout.createParallelGroup(GroupLayout.BASELINE)
                            .add(label3)
                            .add(label4))
                        .add(3, 3, 3)
                        .add(dulPanelLayout.createParallelGroup()
                            .add(jdcIssuerDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .add(jtfIssuerCode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.RELATED)
                        .add(label5)
                        .addPreferredGap(LayoutStyle.RELATED)
                        .add(jtfIssuerName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .add(9, 9, 9))
            );
        }

        //---- buttonSave ----
        buttonSave.setText(bundle.getString("DulTopComponent.buttonSave.text"));
        buttonSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buttonSaveActionPerformed(e);
                buttonSaveActionPerformed(e);
                buttonSaveActionPerformed(e);
            }
        });

        //---- button3 ----
        button3.setText(bundle.getString("DulTopComponent.button3.text"));

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .add(layout.createSequentialGroup()
                    .addContainerGap()
                    .add(layout.createParallelGroup(GroupLayout.TRAILING)
                        .add(layout.createSequentialGroup()
                            .add(buttonSave, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.UNRELATED)
                            .add(button3))
                        .add(GroupLayout.LEADING, dulPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .add(layout.createSequentialGroup()
                    .addContainerGap()
                    .add(dulPanel, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)
                    .add(18, 18, 18)
                    .add(layout.createParallelGroup(GroupLayout.BASELINE, false)
                        .add(buttonSave)
                        .add(button3))
                    .addContainerGap())
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel dulPanel;
    private JLabel label1;
    private JTextField jtfSeries;
    private JLabel label2;
    private JTextField jtfNum;
    private JDateChooser jdcIssuerDate;
    private JLabel label3;
    private JLabel label4;
    private JTextField jtfIssuerCode;
    private JLabel label5;
    private JTextField jtfIssuerName;
    private JLabel label6;
    private JTextField jtfType;
    private JButton buttonSave;
    private JButton button3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

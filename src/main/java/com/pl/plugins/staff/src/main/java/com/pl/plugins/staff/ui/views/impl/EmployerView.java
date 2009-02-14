/*
 * Created by JFormDesigner on Thu Sep 25 16:48:57 MSD 2008
 */

package com.pl.plugins.staff.ui.views.impl;

import com.pl.plugins.commons.ui.views.impl.HumanView;
import com.pl.plugins.commons.ui.views.impl.SimpleView;
import com.pl.plugins.staff.dal.dbo.EmployerDBO;
import com.pl.plugins.staff.ui.controllers.impl.EmployerController;
import com.pl.plugins.staff.ui.models.impl.EmployerModel;
import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.layout.GroupLayout;
import org.jdesktop.layout.LayoutStyle;
import org.jdesktop.swingx.VerticalLayout;

import javax.swing.*;
import javax.swing.border.TitledBorder;

/**
 * @author Vlad Kimo
 */
public class EmployerView extends SimpleView<EmployerModel, EmployerController>  {

    public EmployerView() {

        super(new EmployerModel(), new EmployerController());

        initComponents();

        humanView.setModel(getModel().getHumanModel());
    }

    @Override
    public void bind() {

        if(humanView != null){
            humanView.bind();
        }

        Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE,
                     getModel().getProperty(), BeanProperty.create(EmployerDBO.POST),
                     jtfPostName, BeanProperty.create("text")).bind();
    }

    @Override
    public void onShow() {
        super.onShow();

        if(humanView != null){
            humanView.onShow();
        }
        
        bind();
    }

    @Override
    public void onHide() {
        super.onHide();

        if(humanView != null){
            humanView.onHide();
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Vlad Kimo
        humanView = new HumanView();
        jpPostInfo = new JPanel();
        label1 = new JLabel();
        jtfPostName = new JTextField();

        //======== this ========

        // JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(
            new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

        setLayout(new VerticalLayout());
        add(humanView);

        //======== jpPostInfo ========
        {
            jpPostInfo.setBorder(new TitledBorder("\u0412\u044b\u0431\u043e\u0440 \u0434\u043e\u043b\u0436\u043d\u043e\u0441\u0442\u0438"));

            //---- label1 ----
            label1.setText("\u0414\u043e\u043b\u0436\u043d\u043e\u0441\u0442\u044c");

            GroupLayout jpPostInfoLayout = new GroupLayout(jpPostInfo);
            jpPostInfo.setLayout(jpPostInfoLayout);
            jpPostInfoLayout.setHorizontalGroup(
                jpPostInfoLayout.createParallelGroup()
                    .add(jpPostInfoLayout.createSequentialGroup()
                        .addContainerGap()
                        .add(jpPostInfoLayout.createParallelGroup()
                            .add(jtfPostName, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
                            .add(label1))
                        .addContainerGap(203, Short.MAX_VALUE))
            );
            jpPostInfoLayout.setVerticalGroup(
                jpPostInfoLayout.createParallelGroup()
                    .add(jpPostInfoLayout.createSequentialGroup()
                        .add(label1)
                        .addPreferredGap(LayoutStyle.RELATED)
                        .add(jtfPostName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(54, Short.MAX_VALUE))
            );
        }
        add(jpPostInfo);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Vlad Kimo
    private HumanView humanView;
    private JPanel jpPostInfo;
    private JLabel label1;
    private JTextField jtfPostName;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

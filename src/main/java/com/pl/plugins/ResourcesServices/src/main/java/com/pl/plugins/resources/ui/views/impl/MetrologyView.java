/*
 * Created by JFormDesigner on Wed Oct 01 11:50:32 MSD 2008
 */

package com.pl.plugins.resources.ui.views.impl;

import com.pl.plugins.commons.ui.views.impl.View;
import com.pl.plugins.resources.dal.dbo.MetrologyDBO;
import com.pl.plugins.resources.ui.controllers.impl.MetrologyController;
import com.pl.plugins.resources.ui.models.impl.MetrologyModel;
import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.layout.GroupLayout;
import org.jdesktop.layout.LayoutStyle;

import javax.swing.*;
import java.util.ResourceBundle;

/**
 * @author dmitriy lazarenko
 */
public class MetrologyView extends View<MetrologyModel, MetrologyController> {
    public MetrologyView() {
        super(new MetrologyModel(), new MetrologyController());
        initComponents();
        bind();
    }

    @Override
    public void bind() {
        Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE,
                getModel().getProperty(), BeanProperty.create(MetrologyDBO.SHORT_NAME),
                jtfShortName, BeanProperty.create("text")).bind();
        Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE,
                getModel().getProperty(), BeanProperty.create(MetrologyDBO.LONG_NAME),
                jtfFullName, BeanProperty.create("text")).bind();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - dmitriy lazarenko
        ResourceBundle bundle = ResourceBundle.getBundle("com.pl.plugins.resources.ui.Cards");
        lbShortName = new JLabel();
        lbFullName = new JLabel();
        jtfShortName = new JTextField();
        jtfFullName = new JTextField();

        //======== this ========

        // JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(
                new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                        "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                        javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                        java.awt.Color.red), getBorder()));
        addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent e) {
                if ("border".equals(e.getPropertyName())) throw new RuntimeException();
            }
        });

        //---- lbShortName ----
        lbShortName.setText(bundle.getString("MetrologyView.lbShortName.text"));

        //---- lbFullName ----
        lbFullName.setText(bundle.getString("MetrologyView.lbFullName.text"));

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                        .add(layout.createSequentialGroup()
                        .addContainerGap()
                        .add(layout.createParallelGroup()
                                .add(layout.createSequentialGroup()
                                        .add(lbFullName)
                                        .addPreferredGap(LayoutStyle.UNRELATED)
                                        .add(jtfFullName, GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE))
                                .add(layout.createSequentialGroup()
                                .add(lbShortName)
                                .addPreferredGap(LayoutStyle.RELATED)
                                .add(jtfShortName, GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)))
                        .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup()
                        .add(layout.createSequentialGroup()
                        .addContainerGap()
                        .add(layout.createParallelGroup(GroupLayout.BASELINE)
                                .add(lbShortName)
                                .add(jtfShortName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(GroupLayout.BASELINE)
                                .add(lbFullName)
                                .add(jtfFullName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(23, Short.MAX_VALUE))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }


    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - dmitriy lazarenko
    private JLabel lbShortName;
    private JLabel lbFullName;
    private JTextField jtfShortName;
    private JTextField jtfFullName;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

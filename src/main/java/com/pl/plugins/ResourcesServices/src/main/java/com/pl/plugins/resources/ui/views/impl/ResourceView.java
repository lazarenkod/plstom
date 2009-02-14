/*
 * Created by JFormDesigner on Wed Oct 01 12:54:04 MSD 2008
 */

package com.pl.plugins.resources.ui.views.impl;

import java.util.*;
import javax.swing.*;
import org.jdesktop.layout.*;
import org.jdesktop.layout.GroupLayout;
import org.jdesktop.layout.LayoutStyle;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.swingbinding.SwingBindings;
import com.pl.plugins.commons.ui.views.impl.SimpleView;
import com.pl.plugins.resources.ui.models.impl.ResourceModel;
import com.pl.plugins.resources.ui.controllers.impl.ResourceController;
import com.pl.plugins.resources.dal.dbo.ResourceDBO;
import com.pl.plugins.resources.dal.services.impl.MetrologyService;

/**
 * @author dmitriy lazarenko
 */
public class ResourceView extends SimpleView<ResourceModel, ResourceController> {
    public ResourceView() {
        super(new ResourceModel(),new ResourceController());
        initComponents();
        bind();
    }

    @Override
    public void bind() {
        Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE,
                getModel().getProperty(), BeanProperty.create(ResourceDBO.NAME),
                jtfName, BeanProperty.create("text")).bind();
//        SwingBindings.createJComboBoxBinding(
//                AutoBinding.UpdateStrategy.READ, MetrologyService.getInstance().getAll(), jcbMetrology).bind();

        Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE,
                getModel().getProperty(), BeanProperty.create(ResourceDBO.METROLOGY),
                jcbMetrology, BeanProperty.create("selectedItem")).bind();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - dmitriy lazarenko
        ResourceBundle bundle = ResourceBundle.getBundle("com.pl.plugins.resources.ui.Cards");
        jlName = new JLabel();
        jlMetrology = new JLabel();
        jtfName = new JTextField();
        jcbMetrology = new JComboBox();

        //======== this ========

        // JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(
            new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});


        //---- jlName ----
        jlName.setText(bundle.getString("ResourceView.jlName.text"));

        //---- jlMetrology ----
        jlMetrology.setText(bundle.getString("ResourceView.jlMetrology.text"));

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .add(layout.createSequentialGroup()
                    .addContainerGap()
                    .add(layout.createParallelGroup()
                        .add(jlMetrology)
                        .add(jlName))
                    .addPreferredGap(LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(GroupLayout.LEADING, false)
                        .add(jcbMetrology, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(jtfName, GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE))
                    .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .add(layout.createSequentialGroup()
                    .add(14, 14, 14)
                    .add(layout.createParallelGroup(GroupLayout.BASELINE)
                        .add(jlName)
                        .add(jtfName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(GroupLayout.BASELINE)
                        .add(jlMetrology)
                        .add(jcbMetrology, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(33, Short.MAX_VALUE))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - dmitriy lazarenko
    private JLabel jlName;
    private JLabel jlMetrology;
    private JTextField jtfName;
    private JComboBox jcbMetrology;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

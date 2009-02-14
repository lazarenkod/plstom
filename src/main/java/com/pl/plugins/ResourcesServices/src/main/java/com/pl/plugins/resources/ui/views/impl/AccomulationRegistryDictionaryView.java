/*
 * Created by JFormDesigner on Wed Oct 01 11:08:33 MSD 2008
 */

package com.pl.plugins.resources.ui.views.impl;

import javax.swing.*;
import org.jdesktop.layout.*;
import org.jdesktop.layout.GroupLayout;
import org.jdesktop.swingx.*;
import com.pl.plugins.commons.ui.views.impl.SimpleDictionaryView;
import com.pl.plugins.resources.ui.models.impl.AccomulationRegistryDictionaryModel;
import com.pl.plugins.resources.ui.controllers.impl.AccomulationRegistryDictionaryController;

/**
 * @author dmitriy lazarenko
 */
public class AccomulationRegistryDictionaryView extends SimpleDictionaryView<AccomulationRegistryDictionaryModel, AccomulationRegistryDictionaryController> {
    public AccomulationRegistryDictionaryView() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - dmitriy lazarenko
        scrollPane1 = new JScrollPane();
        jxtAccomTable = new JXTable();

        //======== this ========

        // JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(
            new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});


        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(jxtAccomTable);
        }

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .add(layout.createSequentialGroup()
                    .add(18, 18, 18)
                    .add(scrollPane1, GroupLayout.PREFERRED_SIZE, 417, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .add(layout.createSequentialGroup()
                    .add(15, 15, 15)
                    .add(scrollPane1, GroupLayout.PREFERRED_SIZE, 326, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(9, Short.MAX_VALUE))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - dmitriy lazarenko
    private JScrollPane scrollPane1;
    private JXTable jxtAccomTable;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

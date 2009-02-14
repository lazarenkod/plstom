/*
 * Created by JFormDesigner on Thu Oct 02 03:20:48 MSD 2008
 */

package com.pl.plugins.resources.ui.views.impl;

import java.util.*;
import javax.swing.*;
import com.toedter.calendar.*;
import org.jdesktop.layout.*;
import org.jdesktop.layout.GroupLayout;
import org.jdesktop.layout.LayoutStyle;
import org.jdesktop.swingx.*;

/**
 * @author dmitriy lazarenko
 */
public class AccomulationRegistryView extends JPanel {
    public AccomulationRegistryView() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - dmitriy lazarenko
        ResourceBundle bundle = ResourceBundle.getBundle("com.pl.plugins.resources.ui.Cards");
        jlbDate = new JLabel();
        jlbStore = new JLabel();
        jlbResource = new JLabel();
        jlbOperation = new JLabel();
        jlbAmount = new JLabel();
        jlbDocument = new JLabel();
        jdcDate = new JDateChooser();
        jcbStore = new JComboBox();
        jcbResource = new JComboBox();
        jtfAmount = new JTextField();
        jcbOperation = new JComboBox();
        jcbDocument = new JComboBox();

        //======== this ========

        // JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(
            new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});


        //---- jlbDate ----
        jlbDate.setText(bundle.getString("AccomulationRegistryView.jlbDate.text"));

        //---- jlbStore ----
        jlbStore.setText(bundle.getString("AccomulationRegistryView.jlbStore.text"));

        //---- jlbResource ----
        jlbResource.setText(bundle.getString("AccomulationRegistryView.jlbResource.text"));

        //---- jlbOperation ----
        jlbOperation.setText(bundle.getString("AccomulationRegistryView.jlbOperation.text"));

        //---- jlbAmount ----
        jlbAmount.setText(bundle.getString("AccomulationRegistryView.jlbAmount.text"));

        //---- jlbDocument ----
        jlbDocument.setText(bundle.getString("AccomulationRegistryView.jlbDocument.text"));

        //---- jdcDate ----
        jdcDate.setDateFormatString("dd.MM.yyyy mm:ss");
        jdcDate.setEnabled(false);

        //---- jtfAmount ----
        jtfAmount.setEditable(false);

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .add(layout.createSequentialGroup()
                    .add(12, 12, 12)
                    .add(layout.createParallelGroup()
                        .add(jlbStore)
                        .add(jlbDate)
                        .add(jlbResource)
                        .add(jlbAmount)
                        .add(jlbOperation)
                        .add(jlbDocument))
                    .addPreferredGap(LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(GroupLayout.LEADING, false)
                        .add(jtfAmount, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
                        .add(jcbOperation, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(jcbStore, 0, 178, Short.MAX_VALUE)
                        .add(jcbResource, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(jcbDocument, GroupLayout.PREFERRED_SIZE, 297, GroupLayout.PREFERRED_SIZE)
                        .add(jdcDate, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE))
                    .add(38, 38, 38))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .add(layout.createSequentialGroup()
                    .add(17, 17, 17)
                    .add(layout.createParallelGroup()
                        .add(jlbDate)
                        .add(jdcDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(GroupLayout.BASELINE)
                        .add(jlbStore)
                        .add(jcbStore, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(GroupLayout.BASELINE)
                        .add(jlbResource)
                        .add(jcbResource, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(GroupLayout.BASELINE)
                        .add(jlbAmount)
                        .add(jtfAmount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(GroupLayout.BASELINE)
                        .add(jlbOperation)
                        .add(jcbOperation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(GroupLayout.BASELINE)
                        .add(jlbDocument)
                        .add(jcbDocument, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(35, Short.MAX_VALUE))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - dmitriy lazarenko
    private JLabel jlbDate;
    private JLabel jlbStore;
    private JLabel jlbResource;
    private JLabel jlbOperation;
    private JLabel jlbAmount;
    private JLabel jlbDocument;
    private JDateChooser jdcDate;
    private JComboBox jcbStore;
    private JComboBox jcbResource;
    private JTextField jtfAmount;
    private JComboBox jcbOperation;
    private JComboBox jcbDocument;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

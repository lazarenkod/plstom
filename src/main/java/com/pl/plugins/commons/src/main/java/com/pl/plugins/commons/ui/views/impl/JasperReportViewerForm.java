/*
 * Created by JFormDesigner on Fri Oct 03 16:08:01 MSD 2008
 */

package com.pl.plugins.commons.ui.views.impl;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JRViewer;
import org.jdesktop.layout.GroupLayout;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;

/**
 * @author dmitriy lazarenko
 */

/**
 * Форма для просмотра отчета
 */
public class JasperReportViewerForm extends JPanel {
    public JasperReportViewerForm() {
        initComponents();
    }
     public void viewReport(JasperPrint jasperPrint) {
        JRViewer jrViewer = new JRViewer(jasperPrint, Locale.getDefault());
        setLayout(new BorderLayout());
        add(jrViewer, BorderLayout.CENTER);
    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - dmitriy lazarenko
        //======== this ========

        // JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(
            new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});


        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .add(0, 100, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .add(0, 100, Short.MAX_VALUE)
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - dmitriy lazarenko
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

/*
 * Created by JFormDesigner on Thu Oct 02 16:34:33 MSD 2008
 */

package com.pl.plugins.resources.ui.views.impl;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import com.toedter.calendar.*;
import org.jdesktop.layout.*;
import org.jdesktop.layout.GroupLayout;
import org.jdesktop.layout.LayoutStyle;
import org.jdesktop.swingx.*;

/**
 * @author dmitriy lazarenko
 */
public class ArrivalDocumentView extends JPanel {
    public ArrivalDocumentView() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - dmitriy lazarenko
        ResourceBundle bundle = ResourceBundle.getBundle("com.pl.plugins.resources.ui.views.ArrivalDocument");
        panel1 = new JPanel();
        jlbDate = new JLabel();
        jlbSupplier = new JLabel();
        jtfSupplier = new JTextField();
        jlbStore = new JLabel();
        jcbStore = new JComboBox();
        jdcDate = new JDateChooser();
        panel2 = new JPanel();
        scrollPane1 = new JScrollPane();
        jxtGoods = new JXTable();
        toolBar1 = new JToolBar();
        jbtnAddGood = new JButton();
        jbtnDelGood = new JButton();
        jbtnEditGood = new JButton();

        //======== this ========

        // JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(
            new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

        setLayout(new BorderLayout());

        //======== panel1 ========
        {
            panel1.setPreferredSize(new Dimension(600, 100));

            //---- jlbDate ----
            jlbDate.setText(bundle.getString("ArrivalDocumentView.jlbDate.text"));

            //---- jlbSupplier ----
            jlbSupplier.setText(bundle.getString("ArrivalDocumentView.jlbSupplier.text"));

            //---- jlbStore ----
            jlbStore.setText(bundle.getString("ArrivalDocumentView.jlbStore.text"));

            //---- jdcDate ----
            jdcDate.setDateFormatString("dd.MM.yyyy mm:ss");

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .add(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(panel1Layout.createParallelGroup()
                            .add(jlbDate, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                            .add(jlbStore)
                            .add(jlbSupplier))
                        .addPreferredGap(LayoutStyle.RELATED)
                        .add(panel1Layout.createParallelGroup(GroupLayout.LEADING, false)
                            .add(jdcDate, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
                            .add(jtfSupplier, GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                            .add(jcbStore, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(262, Short.MAX_VALUE))
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .add(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(panel1Layout.createParallelGroup()
                            .add(jlbDate)
                            .add(jdcDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.RELATED)
                        .add(panel1Layout.createParallelGroup()
                            .add(jlbSupplier, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                            .add(jtfSupplier, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.RELATED)
                        .add(panel1Layout.createParallelGroup(GroupLayout.BASELINE)
                            .add(jlbStore)
                            .add(jcbStore, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(20, Short.MAX_VALUE))
            );
        }
        add(panel1, BorderLayout.NORTH);

        //======== panel2 ========
        {
            panel2.setLayout(new BorderLayout());

            //======== scrollPane1 ========
            {

                //---- jxtGoods ----
                jxtGoods.setModel(new DefaultTableModel(
                    new Object[][] {
                    },
                    new String[] {
                        null, "\u2116"
                    }
                ));
                scrollPane1.setViewportView(jxtGoods);
            }
            panel2.add(scrollPane1, BorderLayout.CENTER);

            //======== toolBar1 ========
            {

                //---- jbtnAddGood ----
                jbtnAddGood.setIcon(new ImageIcon(getClass().getResource("/com/pl/plugins/commons/ui/views/impl/id_card_add.png")));
                toolBar1.add(jbtnAddGood);

                //---- jbtnDelGood ----
                jbtnDelGood.setIcon(new ImageIcon(getClass().getResource("/com/pl/plugins/commons/ui/views/impl/id_card_delete.png")));
                toolBar1.add(jbtnDelGood);

                //---- jbtnEditGood ----
                jbtnEditGood.setIcon(new ImageIcon(getClass().getResource("/com/pl/plugins/commons/ui/views/impl/id_card_information.png")));
                toolBar1.add(jbtnEditGood);
            }
            panel2.add(toolBar1, BorderLayout.NORTH);
        }
        add(panel2, BorderLayout.CENTER);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - dmitriy lazarenko
    private JPanel panel1;
    private JLabel jlbDate;
    private JLabel jlbSupplier;
    private JTextField jtfSupplier;
    private JLabel jlbStore;
    private JComboBox jcbStore;
    private JDateChooser jdcDate;
    private JPanel panel2;
    private JScrollPane scrollPane1;
    private JXTable jxtGoods;
    private JToolBar toolBar1;
    private JButton jbtnAddGood;
    private JButton jbtnDelGood;
    private JButton jbtnEditGood;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

/*
 * Created by JFormDesigner on Wed Oct 01 16:23:32 MSD 2008
 */

package com.pl.plugins.resources.ui.views.impl;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import org.jdesktop.layout.*;
import org.jdesktop.layout.GroupLayout;
import org.jdesktop.layout.LayoutStyle;
import com.pl.plugins.commons.ui.views.impl.SimpleView;
import com.pl.plugins.commons.ui.models.impl.SimpleModel;
import com.pl.plugins.commons.ui.controllers.impl.SimpleController;
import com.pl.plugins.resources.ui.models.impl.GoodsModel;
import com.pl.plugins.resources.ui.controllers.impl.GoodsController;
import org.jdesktop.swingx.*;

/**
 * @author dmitriy lazarenko
 */
public class GoodsView extends SimpleView<GoodsModel, GoodsController> {
    public GoodsView() {
        super(new GoodsModel(),new GoodsController());
        initComponents();
        bind();
    }

    @Override
    public void bind() {
        super.bind();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - dmitriy lazarenko
        ResourceBundle bundle = ResourceBundle.getBundle("com.pl.plugins.resources.ui.Cards");
        jlbGoodName = new JLabel();
        jtfGoodName = new JTextField();
        jpNorms = new JPanel();
        scrollPane1 = new JScrollPane();
        jxtNorms = new JXTable();
        toolBar1 = new JToolBar();
        jbtnAddNorm = new JButton();
        jbtnDelNorm = new JButton();
        jbtnEditNorm = new JButton();

        //======== this ========

        // JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(
            new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});


        //---- jlbGoodName ----
        jlbGoodName.setText(bundle.getString("ServiceView.jlbGoodName.text"));

        //======== jpNorms ========
        {
            jpNorms.setBorder(new TitledBorder(bundle.getString("ServiceView.jpNorms.border")));
            jpNorms.setLayout(new BorderLayout());

            //======== scrollPane1 ========
            {
                scrollPane1.setViewportView(jxtNorms);
            }
            jpNorms.add(scrollPane1, BorderLayout.CENTER);

            //======== toolBar1 ========
            {

                //---- jbtnAddNorm ----
                jbtnAddNorm.setIcon(new ImageIcon(getClass().getResource("/com/pl/plugins/commons/ui/views/impl/id_card_add.png")));
                toolBar1.add(jbtnAddNorm);

                //---- jbtnDelNorm ----
                jbtnDelNorm.setIcon(new ImageIcon(getClass().getResource("/com/pl/plugins/commons/ui/views/impl/id_card_delete.png")));
                toolBar1.add(jbtnDelNorm);

                //---- jbtnEditNorm ----
                jbtnEditNorm.setIcon(new ImageIcon(getClass().getResource("/com/pl/plugins/commons/ui/views/impl/id_card_information.png")));
                toolBar1.add(jbtnEditNorm);
            }
            jpNorms.add(toolBar1, BorderLayout.NORTH);
        }

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .add(layout.createSequentialGroup()
                    .addContainerGap()
                    .add(layout.createParallelGroup()
                        .add(jpNorms, GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
                        .add(layout.createSequentialGroup()
                            .add(jlbGoodName)
                            .addPreferredGap(LayoutStyle.RELATED)
                            .add(jtfGoodName, GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)))
                    .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .add(layout.createSequentialGroup()
                    .addContainerGap()
                    .add(layout.createParallelGroup(GroupLayout.BASELINE)
                        .add(jlbGoodName)
                        .add(jtfGoodName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .add(18, 18, 18)
                    .add(jpNorms, GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
                    .addContainerGap())
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - dmitriy lazarenko
    private JLabel jlbGoodName;
    private JTextField jtfGoodName;
    private JPanel jpNorms;
    private JScrollPane scrollPane1;
    private JXTable jxtNorms;
    private JToolBar toolBar1;
    private JButton jbtnAddNorm;
    private JButton jbtnDelNorm;
    private JButton jbtnEditNorm;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

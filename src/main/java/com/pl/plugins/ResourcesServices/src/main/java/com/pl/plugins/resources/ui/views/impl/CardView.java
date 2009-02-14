/*
 * Created by JFormDesigner on Sat Oct 04 10:04:43 MSD 2008
 */

package com.pl.plugins.resources.ui.views.impl;

import com.pl.plugins.commons.ui.views.impl.SimpleView;
import com.pl.plugins.customers.dal.services.impl.ClientsService;
import com.pl.plugins.resources.dal.dbo.CardDBO;
import com.pl.plugins.resources.ui.controllers.impl.CardController;
import com.pl.plugins.resources.ui.models.impl.CardModel;
import com.toedter.calendar.JDateChooser;
import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.layout.GroupLayout;
import org.jdesktop.layout.LayoutStyle;
import org.jdesktop.swingbinding.SwingBindings;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.util.ResourceBundle;

/**
 * @author dmitriy lazarenko
 */
public class CardView extends SimpleView<CardModel, CardController> {
    public CardView() {
        super(new CardModel(), new CardController());
        initComponents();
        bind();

    }

    public void initListeners() {

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Vlad Kimo
        ResourceBundle bundle = ResourceBundle.getBundle("com.pl.plugins.resources.ui.Cards");
        panel1 = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        jcbClient = new JComboBox();
        jdcIssueDate = new JDateChooser();
        jsPercent = new JSpinner();
        label4 = new JLabel();
        jtfCardNumber = new JTextField();

        //======== this ========

        // JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(
            new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});


        //======== panel1 ========
        {
            panel1.setBorder(new TitledBorder(bundle.getString("CardView.panel1.border")));

            //---- label1 ----
            label1.setText(bundle.getString("CardView.label1.text"));

            //---- label2 ----
            label2.setText(bundle.getString("CardView.label2.text"));

            //---- label3 ----
            label3.setText(bundle.getString("CardView.label3.text"));

            //---- jdcIssueDate ----
            jdcIssueDate.setDateFormatString("dd.MM.yyyy hh:mm");

            //---- jsPercent ----
            jsPercent.setModel(new SpinnerNumberModel(0, 0, 10, 5));

            //---- label4 ----
            label4.setText(bundle.getString("CardView.label4.text"));

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .add(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(panel1Layout.createParallelGroup()
                            .add(label4, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
                            .add(panel1Layout.createSequentialGroup()
                                .add(panel1Layout.createParallelGroup()
                                    .add(label2, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                                    .add(label1, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                                    .add(label3))
                                .addPreferredGap(LayoutStyle.RELATED)
                                .add(panel1Layout.createParallelGroup()
                                    .add(panel1Layout.createParallelGroup(GroupLayout.LEADING, false)
                                        .add(GroupLayout.TRAILING, jtfCardNumber)
                                        .add(GroupLayout.TRAILING, jcbClient, 0, 223, Short.MAX_VALUE)
                                        .add(jdcIssueDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                    .add(jsPercent, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(12, Short.MAX_VALUE))
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .add(panel1Layout.createSequentialGroup()
                        .add(panel1Layout.createParallelGroup(GroupLayout.BASELINE)
                            .add(label4)
                            .add(jtfCardNumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.RELATED)
                        .add(panel1Layout.createParallelGroup(GroupLayout.BASELINE)
                            .add(label1)
                            .add(jcbClient, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.RELATED)
                        .add(panel1Layout.createParallelGroup()
                            .add(label2)
                            .add(jdcIssueDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.RELATED)
                        .add(panel1Layout.createParallelGroup(GroupLayout.BASELINE)
                            .add(label3, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                            .add(jsPercent, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(35, Short.MAX_VALUE))
            );
        }

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .add(layout.createSequentialGroup()
                    .addContainerGap()
                    .add(panel1, GroupLayout.PREFERRED_SIZE, 353, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(111, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .add(layout.createSequentialGroup()
                    .addContainerGap()
                    .add(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(15, Short.MAX_VALUE))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    @Override
    public void bind() {
        Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE,
                getModel().getProperty(), BeanProperty.create(CardDBO.CARD_NUMBER),
                jtfCardNumber, BeanProperty.create("text")).bind();

//        SwingBindings.createJComboBoxBinding(
//                AutoBinding.UpdateStrategy.READ, ClientsService.getInstance().getAll(), jcbClient).bind();

        Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE,
                getModel().getProperty(), BeanProperty.create(CardDBO.CLIENT),
                jcbClient, BeanProperty.create("selectedItem")).bind();
        Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE,
                     getModel().getProperty(), BeanProperty.create(CardDBO.ISSUE_DATE),
                     jdcIssueDate, BeanProperty.create("date")).bind();
        Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE,
                     getModel().getProperty(), BeanProperty.create(CardDBO.PERCENT),
                     jsPercent, BeanProperty.create("value")).bind();
        
    }
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Vlad Kimo
    private JPanel panel1;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JComboBox jcbClient;
    private JDateChooser jdcIssueDate;
    private JSpinner jsPercent;
    private JLabel label4;
    private JTextField jtfCardNumber;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

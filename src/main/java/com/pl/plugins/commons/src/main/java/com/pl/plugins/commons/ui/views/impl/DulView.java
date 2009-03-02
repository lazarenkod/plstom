/*
 * Created by JFormDesigner on Fri Oct 03 08:52:27 MSD 2008
 */

package com.pl.plugins.commons.ui.views.impl;

import com.pl.plugins.commons.dal.dbo.DulDBO;
import com.pl.plugins.commons.ui.controllers.impl.DulController;
import com.pl.plugins.commons.ui.models.impl.DulModel;
import com.toedter.calendar.JDateChooser;
import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.layout.GroupLayout;
import org.jdesktop.layout.LayoutStyle;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

/**
 * @author Vlad Kimo
 */
public class DulView extends View<DulModel, DulController> {

    public DulView() {

        super(new DulModel(), new DulController());

        initComponents();

        bind();
    }

    @Override
    public void bind() {
       super.bind();
       Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE,
                 getModel().getProperty(), BeanProperty.create(DulDBO.TYPE),
                 jtfType, BeanProperty.create("text")).bind();

        Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE,
                 getModel().getProperty(), BeanProperty.create(DulDBO.SERIES),
                 jtfSeries, BeanProperty.create("text")).bind();

        Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE,
                 getModel().getProperty(), BeanProperty.create(DulDBO.NUM),
                 jtfNum, BeanProperty.create("text")).bind();

        Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE,
                 getModel().getProperty(), BeanProperty.create(DulDBO.ISSUE_DATE),
                 jdcIssuerDate, BeanProperty.create("date")).bind();

        Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE,
                 getModel().getProperty(), BeanProperty.create(DulDBO.ISSUE_DIVISION),
                 jtfIssuerCode, BeanProperty.create("text")).bind();

        Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE,
                 getModel().getProperty(), BeanProperty.create(DulDBO.ISSUER),
                 jtfIssuerName, BeanProperty.create("date")).bind();
    }

    private void button1ActionPerformed(ActionEvent e) {
        getModel().getProperty().setNum("dffffffff");
        
    }

    private void button1StateChanged(ChangeEvent e) {
        // TODO add your code here         
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        ResourceBundle bundle = ResourceBundle.getBundle("com.pl.plugins.commons.ui.views.CommonViews");
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
        button1 = new JButton();

        //======== this ========
        setBorder(new TitledBorder(null, bundle.getString("DulView.this.border"), TitledBorder.LEADING, TitledBorder.TOP));
        setMinimumSize(new Dimension(300, 210));

        //---- label1 ----
        label1.setText(bundle.getString("DulView.label1.text"));

        //---- label2 ----
        label2.setText(bundle.getString("DulView.label2.text"));

        //---- label3 ----
        label3.setText(bundle.getString("DulView.label3.text"));

        //---- label4 ----
        label4.setText(bundle.getString("DulView.label4.text"));

        //---- label5 ----
        label5.setText(bundle.getString("DulView.label5.text"));

        //---- label6 ----
        label6.setText(bundle.getString("DulView.label6.text"));

        //---- button1 ----
        button1.setText(bundle.getString("DulView.button1.text"));
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                button1ActionPerformed(e);
            }
        });

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .add(layout.createSequentialGroup()
                    .addContainerGap()
                    .add(layout.createParallelGroup()
                        .add(layout.createSequentialGroup()
                            .add(label6)
                            .add(148, 148, 148))
                        .add(layout.createSequentialGroup()
                            .add(layout.createParallelGroup()
                                .add(GroupLayout.TRAILING, jtfIssuerName, GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
                                .add(GroupLayout.TRAILING, layout.createSequentialGroup()
                                    .add(layout.createParallelGroup()
                                        .add(layout.createParallelGroup(GroupLayout.TRAILING)
                                            .add(GroupLayout.LEADING, jdcIssuerDate, GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                                            .add(GroupLayout.LEADING, label1)
                                            .add(GroupLayout.LEADING, jtfSeries, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE))
                                        .add(label3))
                                    .add(50, 50, 50)
                                    .add(layout.createParallelGroup()
                                        .add(label2)
                                        .add(jtfNum, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                                        .add(label4, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                                        .add(jtfIssuerCode, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)))
                                .add(label5, GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE))
                            .addContainerGap())
                        .add(layout.createSequentialGroup()
                            .add(jtfType, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
                            .add(46, 46, 46)
                            .add(button1)
                            .add(52, 52, 52))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .add(GroupLayout.TRAILING, layout.createSequentialGroup()
                    .add(label6)
                    .addPreferredGap(LayoutStyle.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(layout.createParallelGroup(GroupLayout.BASELINE)
                        .add(jtfType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .add(button1))
                    .addPreferredGap(LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(GroupLayout.BASELINE)
                        .add(label2)
                        .add(label1))
                    .addPreferredGap(LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(GroupLayout.BASELINE)
                        .add(jtfNum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .add(jtfSeries, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(GroupLayout.BASELINE)
                        .add(label3)
                        .add(label4))
                    .add(3, 3, 3)
                    .add(layout.createParallelGroup()
                        .add(jdcIssuerDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .add(jtfIssuerCode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.RELATED)
                    .add(label5)
                    .addPreferredGap(LayoutStyle.RELATED)
                    .add(jtfIssuerName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .add(9, 9, 9))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
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
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

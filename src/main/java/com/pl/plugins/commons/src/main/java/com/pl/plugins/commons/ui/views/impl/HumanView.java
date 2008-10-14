/*
 * Created by JFormDesigner on Thu Sep 25 17:30:10 MSD 2008
 */
/*
 * Created by JFormDesigner on Thu Sep 25 17:30:10 MSD 2008
 */

package com.pl.plugins.commons.ui.views.impl;

import com.pl.plugins.commons.dal.dbo.DulDBO;
import com.pl.plugins.commons.dal.dbo.HumanDBO;
import com.pl.plugins.commons.dal.enums.Gender;
import com.pl.plugins.commons.ui.controllers.impl.HumanController;
import com.pl.plugins.commons.ui.models.impl.HumanModel;
import com.pl.plugins.commons.ui.utils.dialog.impl.SimpleDialogDescriptor;
import com.pl.plugins.core.CorePlugin;
import com.toedter.calendar.JDateChooser;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.*;
import org.jdesktop.layout.GroupLayout;
import org.jdesktop.layout.LayoutStyle;
import org.jdesktop.swingbinding.SwingBindings;
import org.jdesktop.observablecollections.ObservableList;
import org.jdesktop.observablecollections.ObservableCollections;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * @author Vlad Kimo
 */
public class HumanView extends SimpleView<HumanModel, HumanController> {

    private ResourceBundle bundle = ResourceBundle.getBundle("com.pl.plugins.commons.ui.views.CommonViews");

    private DulView dulView = null;

    private SimpleDialog sdDul = null;
    private ObservableList<DulDBO> dulsList = null;

    public HumanView() {

        this(new HumanModel(), new HumanController());
    }

    public HumanView(HumanModel model, HumanController controller) {

        super(model, controller);

        initComponents();

        initLisiners();
    }

    @Override
    public void onShow() {

        super.onShow();
    }

    @Override
    public void onHide() {

        super.onHide();
    }

    private void initLisiners() {

        jbtnAddDul.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                showDulDialog(new DulDBO(getModel().getProperty()));
            }
        });

        jbtnDelDul.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

            }
        });

        jbtnEditDul.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                showDulDialog((DulDBO) jlDuls.getSelectedValue());
            }
        });
    }                                           

    private void showDulDialog(DulDBO dul) {

        if (dulView == null || sdDul == null) {

            dulView = new DulView();
            dulView.getModel().setProperty(dul);
            dulView.bind();
            SimpleDialogDescriptor dd = new SimpleDialogDescriptor(bundle.getString("SimpleDialog.jbtnOk.text"),
                    bundle.getString("SimpleDialog.jbtnCancel.text"));
            dd.setOkAction(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    dulsList.add(dulView.getModel().getProperty());
                    sdDul.setVisible(false);
                }
            });

            dd.setCancelAction(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    sdDul.setVisible(false);
                }
            });

            sdDul = new SimpleDialog((JFrame) CorePlugin.getAppContext().getBean("mainForm"), dd);
            sdDul.setContentPanel(dulView);
        }

        sdDul.setVisible(true);

    }

    /**
     * ѕерезагружает свойства модели из репозитори€.
     */
    @Override
    public void reload() {
        super.reload();
    }

    @Override
    public void bind() {
        super.bind();

        if (getModel().getProperty() != null) {

            if (bindingGroup != null)
                bindingGroup.unbind();
            else
                bindingGroup = new BindingGroup();

            Binding binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
                    getModel().getProperty(), BeanProperty.create(HumanDBO.FMANE),
                    jtfFname, BeanProperty.create("text"));
            bindingGroup.addBinding(binding);

            binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
                    getModel().getProperty(), BeanProperty.create(HumanDBO.LMANE),
                    jtfLname, BeanProperty.create("text"));
            bindingGroup.addBinding(binding);

            binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
                    getModel().getProperty(), BeanProperty.create(HumanDBO.LMANE),
                    jtfLname, BeanProperty.create("text"));
            bindingGroup.addBinding(binding);

            binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
                    getModel().getProperty(), BeanProperty.create(HumanDBO.PATRONYMIC),
                    jtfPatrotymic, BeanProperty.create("text"));
            bindingGroup.addBinding(binding);

            binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
                    getModel().getProperty(), BeanProperty.create(HumanDBO.ADDRESS),
                    jtaAddress, BeanProperty.create("text"));
            bindingGroup.addBinding(binding);

            binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
                    getModel().getProperty(), BeanProperty.create(HumanDBO.BIRTHDAY),
                    jdcBirthday, BeanProperty.create("date"));
            bindingGroup.addBinding(binding);

            binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
                    getModel().getProperty(), BeanProperty.create(HumanDBO.GENDER),
                    jcbGender, BeanProperty.create("selectedItem"));
            binding.setConverter(new Converter<Boolean, Gender>() {

                @Override
                public Gender convertForward(Boolean isSelected) {
                    return isSelected ? Gender.MAN : Gender.WOMAN;
                }

                @Override
                public Boolean convertReverse(Gender gender) {
                    return gender.equals(Gender.MAN) ;
                }
            }
            );
            bindingGroup.addBinding(binding);
            dulsList = ObservableCollections.observableList((List<DulDBO>) getModel().getProperty().getDuls());
            if (dulsList != null) {
                binding = SwingBindings.createJListBinding(UpdateStrategy.READ_WRITE, dulsList, jlDuls);
//                binding.setConverter(uinew Converter() {
//                    @Override
//                    public Object convertForward(Object o) {
//                        if (o instanceof DulDBO) {
//                            DulDBO dul = (DulDBO) o;
//                            return "1111" + dul.getNum() + dul.getSeries();
//                        }
//                        return null;
//                    }
//
//                    @Override
//                    public Object convertReverse(Object o) {
//                        return null;
//                    }
//                });
                bindingGroup.addBinding(binding);
            }
            bindingGroup.bind();
        }
    }

    @Override
    protected boolean bindOnce() {

        if (jcbGender == null)
            return false;

        SwingBindings.createJComboBoxBinding(UpdateStrategy.READ, Gender.getAll(), jcbGender).bind();

        return true;
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        ResourceBundle bundle = ResourceBundle.getBundle("com.pl.plugins.commons.ui.views.CommonViews");
        label1 = new JLabel();
        jtfLname = new JTextField();
        label2 = new JLabel();
        jtfFname = new JTextField();
        label3 = new JLabel();
        jtfPatrotymic = new JTextField();
        label4 = new JLabel();
        jcbGender = new JComboBox();
        label5 = new JLabel();
        scrollPane1 = new JScrollPane();
        jlDuls = new JList();
        label6 = new JLabel();
        jbtnAddDul = new JButton();
        jbtnDelDul = new JButton();
        jbtnEditDul = new JButton();
        jdcBirthday = new JDateChooser();
        scrollPane2 = new JScrollPane();
        jtaAddress = new JTextArea();
        label7 = new JLabel();

        //======== this ========
        setBorder(new TitledBorder(null, bundle.getString("HumanInfoView.this.border"), TitledBorder.LEADING, TitledBorder.TOP));

        //---- label1 ----
        label1.setText(bundle.getString("HumanInfoView.label1.text"));

        //---- label2 ----
        label2.setText(bundle.getString("HumanInfoView.label2.text"));

        //---- label3 ----
        label3.setText(bundle.getString("HumanInfoView.label3.text"));

        //---- label4 ----
        label4.setText(bundle.getString("HumanInfoView.label4.text"));

        //---- label5 ----
        label5.setText(bundle.getString("HumanInfoView.label5.text"));

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(jlDuls);
        }

        //---- label6 ----
        label6.setText(bundle.getString("HumanInfoView.label6.text"));

        //---- jbtnAddDul ----
        jbtnAddDul.setIcon(new ImageIcon(getClass().getResource("/com/pl/plugins/commons/ui/views/impl/id_card_add.png")));

        //---- jbtnDelDul ----
        jbtnDelDul.setIcon(new ImageIcon(getClass().getResource("/com/pl/plugins/commons/ui/views/impl/id_card_delete.png")));

        //---- jbtnEditDul ----
        jbtnEditDul.setIcon(new ImageIcon(getClass().getResource("/com/pl/plugins/commons/ui/views/impl/id_card_information.png")));

        //======== scrollPane2 ========
        {

            //---- jtaAddress ----
            jtaAddress.setFont(jtaAddress.getFont().deriveFont(jtaAddress.getFont().getSize() + 1f));
            scrollPane2.setViewportView(jtaAddress);
        }

        //---- label7 ----
        label7.setText(bundle.getString("HumanInfoView.label7.text"));

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .add(layout.createSequentialGroup()
                    .addContainerGap()
                    .add(layout.createParallelGroup()
                        .add(layout.createParallelGroup()
                            .add(jtfLname, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
                            .add(label1)
                            .add(label2)
                            .add(jtfFname, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
                            .add(GroupLayout.TRAILING, layout.createParallelGroup(GroupLayout.LEADING, false)
                                .add(label3)
                                .add(jtfPatrotymic, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                                .add(layout.createSequentialGroup()
                                    .add(3, 3, 3)
                                    .add(label4))))
                        .add(label5)
                        .add(jdcBirthday, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
                        .add(jcbGender, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
                    .add(17, 17, 17)
                    .add(layout.createParallelGroup()
                        .add(layout.createSequentialGroup()
                            .add(layout.createParallelGroup()
                                .add(layout.createParallelGroup()
                                    .add(label6)
                                    .add(layout.createSequentialGroup()
                                        .add(jbtnAddDul, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                                        .add(18, 18, 18)
                                        .add(jbtnDelDul, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                        .add(18, 18, 18)
                                        .add(jbtnEditDul, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
                                    .add(scrollPane1, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))
                                .add(label7))
                            .add(15, 15, 15))
                        .add(layout.createSequentialGroup()
                            .add(scrollPane2, GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                            .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .add(layout.createSequentialGroup()
                    .add(layout.createParallelGroup(GroupLayout.BASELINE)
                        .add(label1)
                        .add(label6))
                    .addPreferredGap(LayoutStyle.RELATED)
                    .add(layout.createParallelGroup()
                        .add(layout.createSequentialGroup()
                            .add(jtfLname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.RELATED)
                            .add(label2)
                            .add(6, 6, 6)
                            .add(jtfFname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.RELATED)
                            .add(label3)
                            .add(6, 6, 6)
                            .add(jtfPatrotymic, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.RELATED)
                            .add(label4))
                        .add(scrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.RELATED)
                    .add(layout.createParallelGroup()
                        .add(layout.createSequentialGroup()
                            .add(layout.createParallelGroup()
                                .add(jdcBirthday, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                                .add(jbtnAddDul, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                            .add(7, 7, 7)
                            .add(layout.createParallelGroup()
                                .add(label5)
                                .add(label7)))
                        .add(layout.createParallelGroup(GroupLayout.TRAILING)
                            .add(jbtnEditDul, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                            .add(jbtnDelDul, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)))
                    .addPreferredGap(LayoutStyle.RELATED)
                    .add(layout.createParallelGroup()
                        .add(jcbGender, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .add(scrollPane2, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(18, Short.MAX_VALUE))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JTextField jtfLname;
    private JLabel label2;
    private JTextField jtfFname;
    private JLabel label3;
    private JTextField jtfPatrotymic;
    private JLabel label4;
    private JComboBox jcbGender;
    private JLabel label5;
    private JScrollPane scrollPane1;
    private JList jlDuls;
    private JLabel label6;
    private JButton jbtnAddDul;
    private JButton jbtnDelDul;
    private JButton jbtnEditDul;
    private JDateChooser jdcBirthday;
    private JScrollPane scrollPane2;
    private JTextArea jtaAddress;
    private JLabel label7;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
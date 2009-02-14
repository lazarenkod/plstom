/*
 * Created by JFormDesigner on Fri Sep 19 23:22:50 MSD 2008
 */

package com.pl.plugins.staff.ui.views.impl;

import com.jgoodies.binding.list.ArrayListModel;
import com.pl.plugins.commons.dal.enums.ModelOperations;
import com.pl.plugins.commons.ui.models.ISimpleDictionaryModel;
import com.pl.plugins.commons.ui.utils.dialog.impl.SimpleDialogDescriptor;
import com.pl.plugins.commons.ui.views.ISimpleDictionaryToolBar;
import com.pl.plugins.commons.ui.views.impl.SimpleDialog;
import com.pl.plugins.commons.ui.views.impl.SimpleDictionaryView;
import com.pl.plugins.core.CorePlugin;
import com.pl.plugins.staff.dal.dbo.EmployerDBO;
import com.pl.plugins.staff.ui.controllers.impl.EmployerDictionaryController;
import com.pl.plugins.staff.ui.models.impl.EmployerDictionaryModel;
import com.pl.plugins.staff.ui.models.impl.EmployerTableModel;
import org.jdesktop.layout.GroupLayout;
import org.jdesktop.swingx.JXTable;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

/**
 * @author Vlad Kimo
 */
public class EmployerDictionaryView extends SimpleDictionaryView<EmployerDictionaryModel, EmployerDictionaryController> {

    ResourceBundle bundle = ResourceBundle.getBundle("com.pl.plugins.staff.ui.views.StaffView");

    private EmployerView employerView = null;

    private EmployerDictionaryController sdc = null;

    private EmployerTableModel employerTableModel = null;

    private ISimpleDictionaryToolBar simpleDictionaryToolBar = null;


    public EmployerDictionaryView() {

        this(new EmployerDictionaryModel(), new EmployerDictionaryController());
    }

    public EmployerDictionaryView(EmployerDictionaryModel model, EmployerDictionaryController controller) {

        super(model, controller);

        initComponents();

        sdc = controller;
        sdc.setModel(model);

        employerView = new EmployerView();

        onShow();
    }

    @Override
    public void reload() {
        //TODO нужно подумать над необходимостью каждый раз создавать модель
        employerTableModel = new EmployerTableModel(new ArrayListModel(((
                ISimpleDictionaryModel) getModel()).getAll()));
    }

    @Override
    public void bind() {
  
        if (employerTableModel != null) {
            jxtStaff.setModel(employerTableModel);
        }
    }


    @Override
    public void onShow() {

        super.onShow();

        simpleDictionaryToolBar = (ISimpleDictionaryToolBar) CorePlugin.getAppContext().getBean("simpleDictionaryToolBar");

        attachDictionaryToolBar(simpleDictionaryToolBar);
        simpleDictionaryToolBar.plug();
        simpleDictionaryToolBar.setVisible(true);

        reload();
        bind();
    }

    @Override
    public void onHide() {

        super.onHide();

        simpleDictionaryToolBar.setVisible(false);
    }

    @Override
    public void onNewAction() {
        showEmployerDialog(new EmployerDBO(), ModelOperations.ADD);
    }

    @Override
    public void onDeleteAction() {
        reload();
        bind();
    }

    @Override
    public void onEditAction() {

        if(jxtStaff.getRowCount() > 0 && jxtStaff.getSelectedRow() >= 0 &&
           jxtStaff.getSelectedRow() <jxtStaff.getRowCount()){
            
            showEmployerDialog(employerTableModel.getRow(jxtStaff.getSelectedRow()), ModelOperations.UPDATE);
        }
    }

    @Override
    public void onRefreshAction() {
        reload();
        bind();
    }

    private void showEmployerDialog(EmployerDBO employerDBO, final ModelOperations mode) {

        final SimpleDialogDescriptor dd = new SimpleDialogDescriptor(bundle.getString("SimpleDialog.jbtnOk.text"),
                bundle.getString("SimpleDialog.jbtnCancel.text"));

        final SimpleDialog sdStaff = new SimpleDialog((JFrame) CorePlugin.getAppContext().getBean("mainForm"), dd);

        employerView.getController().update(employerDBO);

        dd.setOkAction(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                sdc.addOrUpdate(employerView.getModel().getProperty());
                onRefreshAction();
                sdStaff.setVisible(false);
            }
        });

        dd.setCancelAction(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                sdStaff.setVisible(false);
            }
        });

        sdStaff.setContentPanel(employerView);

        sdStaff.setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Vlad Kimo
        ResourceBundle bundle = ResourceBundle.getBundle("com.pl.plugins.staff.ui.views.StaffView");
        scrollPane1 = new JScrollPane();
        jxtStaff = new JXTable();

        //======== this ========
        setBorder(new TitledBorder(bundle.getString("StaffView.this.border")));

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

        //======== scrollPane1 ========
        {

            //---- jxtStaff ----
            //jxtStaff.setAutoCreateRowSorter(true);
            jxtStaff.setAutoStartEditOnKeyStroke(false);
            jxtStaff.setModel(new DefaultTableModel());
            jxtStaff.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
            scrollPane1.setViewportView(jxtStaff);
        }

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                        .add(layout.createSequentialGroup()
                        .add(scrollPane1, GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE)
                        .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup()
                        .add(GroupLayout.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .add(scrollPane1, GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE)
                        .addContainerGap())
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Vlad Kimo
    private JScrollPane scrollPane1;
    private JXTable jxtStaff;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

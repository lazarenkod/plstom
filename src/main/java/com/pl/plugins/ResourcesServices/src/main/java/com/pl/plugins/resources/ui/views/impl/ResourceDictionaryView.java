/*
 * Created by JFormDesigner on Wed Oct 01 15:18:11 MSD 2008
 */

package com.pl.plugins.resources.ui.views.impl;

import com.pl.plugins.commons.ui.views.impl.SimpleDictionaryView;
import com.pl.plugins.commons.ui.views.impl.SimpleDictionaryToolBar;
import com.pl.plugins.commons.ui.views.ISimpleDictionaryToolBar;
import com.pl.plugins.commons.ui.models.ISimpleDictionaryModel;
import com.pl.plugins.resources.ui.controllers.impl.ResourceDictionaryController;
import com.pl.plugins.resources.ui.models.impl.ResourceDictionaryModel;
import com.pl.plugins.resources.ui.models.impl.ResourceTableModel;
import com.pl.plugins.resources.dal.dbo.ResourceDBO;
import com.pl.plugins.resources.ResourcesPLPlugin;
import com.pl.plugins.core.CorePlugin;
import org.jdesktop.layout.GroupLayout;
import org.jdesktop.swingx.JXTable;

import javax.swing.*;

/**
 * @author dmitriy lazarenko
 */
public class ResourceDictionaryView extends SimpleDictionaryView<ResourceDictionaryModel,ResourceDictionaryController> {

    private ResourceTableModel resourceTableModel = null;
    private ResourceView resourceView = null;

    ISimpleDictionaryToolBar simpleDictionaryToolBar = null;

    public ResourceDictionaryView() {
        super(new ResourceDictionaryModel(), new ResourceDictionaryController());
        resourceView = new ResourceView();
        initComponents();
    }

    @Override
    public void onNewAction() {
        resourceView.getModel().setProperty(new ResourceDBO());
    }

    @Override
    public void onDeleteAction() {
        super.onDeleteAction();
    }

    @Override
    public void onEditAction() {
        ResourceDBO resourceItem = resourceTableModel.getRow(jxtResource.getSelectedRow());
        resourceView.getModel().setProperty(resourceItem);
    }

    @Override
    public void onRefreshAction() {
        super.onRefreshAction();
    }

    @Override
    public void onFindAction() {
        super.onFindAction();
    }

    @Override
    public void bind() {
        if(resourceTableModel!=null)
            jxtResource.setModel(resourceTableModel);
    }

    @Override
    public void onShow() {

        simpleDictionaryToolBar = (ISimpleDictionaryToolBar) CorePlugin.getAppContext().getBean("simpleDictionaryToolBar");

        attachDictionaryToolBar(simpleDictionaryToolBar);
        simpleDictionaryToolBar.plug();
        simpleDictionaryToolBar.setVisible(true);
    }

    @Override
    public void onHide() {
        simpleDictionaryToolBar.setVisible(false);
    }

    @Override
    public void reload() {
            resourceTableModel = new ResourceTableModel((ListModel)
                 ((ISimpleDictionaryModel) getModel()).getAll());
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - dmitriy lazarenko
        scrollPane1 = new JScrollPane();
        jxtResource = new JXTable();

        //======== this ========

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
            scrollPane1.setViewportView(jxtResource);
        }

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                        .add(layout.createSequentialGroup()
                        .add(17, 17, 17)
                        .add(scrollPane1, GroupLayout.PREFERRED_SIZE, 335, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(48, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup()
                        .add(layout.createSequentialGroup()
                        .add(16, 16, 16)
                        .add(scrollPane1, GroupLayout.PREFERRED_SIZE, 264, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(114, Short.MAX_VALUE))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - dmitriy lazarenko
    private JScrollPane scrollPane1;
    private JXTable jxtResource;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

/*
 * Created by JFormDesigner on Wed Oct 01 12:34:06 MSD 2008
 */

package com.pl.plugins.resources.ui.views.impl;

import javax.swing.*;
import org.jdesktop.layout.*;
import org.jdesktop.layout.GroupLayout;
import org.jdesktop.swingx.*;
import com.pl.plugins.commons.ui.views.impl.SimpleDictionaryView;
import com.pl.plugins.commons.ui.views.impl.SimpleDictionaryToolBar;
import com.pl.plugins.commons.ui.views.ISimpleDictionaryToolBar;
import com.pl.plugins.commons.ui.models.ISimpleDictionaryModel;
import com.pl.plugins.resources.ui.models.impl.MetrologyTableModel;
import com.pl.plugins.resources.ui.models.impl.CardDictionaryModel;
import com.pl.plugins.resources.ui.models.impl.MetrologyDictionaryModel;
import com.pl.plugins.resources.ui.controllers.impl.CardDictionaryController;
import com.pl.plugins.resources.ui.controllers.impl.MetrologyDictionaryController;
import com.pl.plugins.resources.dal.dbo.MetrologyDBO;
import com.pl.plugins.resources.ResourcesPLPlugin;
import com.pl.plugins.core.CorePlugin;

/**
 * @author dmitriy lazarenko
 */
public class MetrologyDictionaryView extends SimpleDictionaryView<MetrologyDictionaryModel,MetrologyDictionaryController> {

    private MetrologyTableModel metrologyTableModel=null;
    private MetrologyView metrologyView=null;

    ISimpleDictionaryToolBar simpleDictionaryToolBar = null;

    public MetrologyDictionaryView() {
        super(new MetrologyDictionaryModel(),new MetrologyDictionaryController());
        metrologyView=new MetrologyView();
        initComponents();
    }

    @Override
    public void onNewAction() {
        metrologyView.getModel().setProperty(new MetrologyDBO());
    }

    @Override
    public void onDeleteAction() {
        super.onDeleteAction();
    }

    @Override
    public void onEditAction() {
        MetrologyDBO metrologyItem = metrologyTableModel.getRow(jxtMetrologies.getSelectedRow());
        metrologyView.getModel().setProperty(metrologyItem);
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
        if(metrologyTableModel!=null)
            jxtMetrologies.setModel(metrologyTableModel);
    }

    @Override
    public void onShow() {
            
        simpleDictionaryToolBar = (ISimpleDictionaryToolBar) CorePlugin.getAppContext().getBean("simpleDictionaryToolBar");

        attachDictionaryToolBar(simpleDictionaryToolBar);
        simpleDictionaryToolBar.plug();
        simpleDictionaryToolBar.setVisible(true);
        //dataChanged(this);

    }

    @Override
    public void onHide() {
        simpleDictionaryToolBar.setVisible(false);
    }

    @Override
    public void reload() {
        metrologyTableModel = new MetrologyTableModel((ListModel)
                ((ISimpleDictionaryModel) getModel()).getAll());
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - dmitriy lazarenko
        scrollPane1 = new JScrollPane();
        jxtMetrologies = new JXTable();

        //======== this ========

        // JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(
            new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});


        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(jxtMetrologies);
        }

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .add(layout.createSequentialGroup()
                    .addContainerGap()
                    .add(scrollPane1, GroupLayout.PREFERRED_SIZE, 379, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .add(GroupLayout.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(11, Short.MAX_VALUE)
                    .add(scrollPane1, GroupLayout.PREFERRED_SIZE, 283, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - dmitriy lazarenko
    private JScrollPane scrollPane1;
    private JXTable jxtMetrologies;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

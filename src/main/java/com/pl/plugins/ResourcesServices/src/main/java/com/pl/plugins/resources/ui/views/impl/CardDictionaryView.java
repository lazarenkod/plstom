/*
 * Created by JFormDesigner on Wed Oct 01 17:40:12 MSD 2008
 */

package com.pl.plugins.resources.ui.views.impl;

import com.pl.plugins.commons.ui.models.ISimpleDictionaryModel;
import com.pl.plugins.commons.ui.views.impl.SimpleDictionaryToolBar;
import com.pl.plugins.commons.ui.views.impl.SimpleDictionaryView;
import com.pl.plugins.commons.ui.views.ISimpleDictionaryToolBar;
import com.pl.plugins.resources.dal.dbo.CardDBO;
import com.pl.plugins.resources.ui.controllers.impl.CardDictionaryController;
import com.pl.plugins.resources.ui.models.impl.CardDictionaryModel;
import com.pl.plugins.resources.ui.models.impl.CardTableModel;
import com.pl.plugins.resources.ResourcesPLPlugin;
import com.pl.plugins.core.CorePlugin;
import org.jdesktop.layout.GroupLayout;
import org.jdesktop.swingx.JXTable;

import javax.swing.*;

/**
 * @author dmitriy lazarenko
 */
public class CardDictionaryView extends SimpleDictionaryView<CardDictionaryModel,CardDictionaryController> {

    CardTableModel cardTableModel = null;
    CardView cardView = null;

    ISimpleDictionaryToolBar simpleDictionaryToolBar = null;

    public CardDictionaryView() {
        super(new CardDictionaryModel(), new CardDictionaryController());
        cardView = new CardView();
        initComponents();
    }

    @Override
    public void bind() {
        if (cardTableModel != null) {
            jxtCard.setModel(cardTableModel);
        }
    }

    @Override
    public void reload() {
        cardTableModel = new CardTableModel((ListModel)
                ((ISimpleDictionaryModel) getModel()).getAll());
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
    public void onNewAction() {
        cardView.getModel().setProperty(new CardDBO());
    }

    @Override
    public void onDeleteAction() {
        super.onDeleteAction();
    }

    @Override
    public void onEditAction() {
        CardDBO cardItem = cardTableModel.getRow(jxtCard.getSelectedRow());
        cardView.getModel().setProperty(cardItem);
    }

    @Override
    public void onRefreshAction() {
        super.onRefreshAction();
    }

    @Override
    public void onFindAction() {
        super.onFindAction();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - dmitriy lazarenko
        scrollPane1 = new JScrollPane();
        jxtCard = new JXTable();

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
            scrollPane1.setViewportView(jxtCard);
        }

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                        .add(layout.createSequentialGroup()
                        .add(26, 26, 26)
                        .add(scrollPane1, GroupLayout.PREFERRED_SIZE, 373, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup()
                        .add(layout.createSequentialGroup()
                        .addContainerGap()
                        .add(scrollPane1, GroupLayout.PREFERRED_SIZE, 310, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(14, Short.MAX_VALUE))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - dmitriy lazarenko
    private JScrollPane scrollPane1;
    private JXTable jxtCard;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

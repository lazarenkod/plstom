/*
 * Created by JFormDesigner on Tue Sep 23 10:41:19 MSD 2008
 */

package com.pl.plugins.commons.ui.views.impl;

import java.awt.*;
import javax.swing.border.*;
import com.jgoodies.forms.factories.*;
import com.pl.plugins.commons.ui.views.ISimpleDictionaryToolBar;
import com.pl.plugins.core.ui.IMainForm;
import com.pl.plugins.core.ui.impl.MainForm;
import com.pl.plugins.core.CorePlugin;

import javax.swing.*;

import java.awt.event.ActionListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Vlad Kimo
 */
public class SimpleDictionaryToolBar extends JToolBar implements ISimpleDictionaryToolBar {

    private Log log= LogFactory.getLog(getClass());

    private ActionListener alNew = null;
    private ActionListener alEdit = null;
    private ActionListener alDelete = null;
    private ActionListener alRefresh = null;
    private ActionListener alFind = null;

    private IMainForm mainForm = null;

    private static SimpleDictionaryToolBar instance = null;

    public static SimpleDictionaryToolBar getInstance() {

        if(instance == null){
            instance = new SimpleDictionaryToolBar();
        }

        return instance;
    }

    private SimpleDictionaryToolBar() {
        initComponents();
    }

    public JToolBar getToolBar(){
        return jtbToolBar;
    }

    public IMainForm getMainForm() {
        return mainForm;
    }

    public void setMainForm(IMainForm mainForm) {
        this.mainForm = mainForm;
    }

    public void setNewAction(ActionListener value){
        if(alNew != null)
            jbNew.addActionListener(alNew);
        alNew = value;
        jbNew.addActionListener(value);
    }

    public void setEditAction(ActionListener value){
        if(alEdit != null)
            jbEdit.addActionListener(alEdit );
        alEdit = value;
        jbEdit.addActionListener(value);
    }

    public void setDeleteAction(ActionListener value){
        if(alDelete != null)
            jbDelete.addActionListener(alDelete);
        alDelete = value;
        jbDelete.addActionListener(value);
    }

    public void setRefreshAction(ActionListener value){
        if(alRefresh != null)
            jbRefresh.addActionListener(alRefresh);
        alRefresh = value;
        jbRefresh.addActionListener(value);
    }

    public void setFindAction(ActionListener value){
        if(alFind != null)
            jbFind.addActionListener(alFind);
        alFind = value;
        jbFind.addActionListener(value);
    }

    public void setVisible(boolean value){
        jtbToolBar.setVisible(value);
    }

    public JToolBar getToolBarContainer(){
        return mainForm.getMainToolBar();
    }
    
    public void plug(){
        IMainForm frame= (IMainForm) CorePlugin.getAppContext().getBean("mainForm");
        frame.getMainToolBar().add(jtbToolBar);
        frame.getMainToolBar().doLayout();
        frame.getMainToolBar().invalidate();
     }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Vlad Kimo
        jtbToolBar = new JToolBar();
        jbNew = new JButton();
        jbEdit = new JButton();
        jbDelete = new JButton();
        jbRefresh = new JButton();
        jbFind = new JButton();

        //======== jtbToolBar ========
        {
            jtbToolBar.setFloatable(false);

            //---- jbNew ----
            jbNew.setText("New");
            jbNew.setIcon(new ImageIcon(getClass().getResource("/com/pl/plugins/commons/ui/views/impl/document_new.png")));
            jtbToolBar.add(jbNew);
            jtbToolBar.addSeparator();

            //---- jbEdit ----
            jbEdit.setText("Edit");
            jbEdit.setIcon(new ImageIcon(getClass().getResource("/com/pl/plugins/commons/ui/views/impl/document_edit.png")));
            jtbToolBar.add(jbEdit);
            jtbToolBar.addSeparator();

            //---- jbDelete ----
            jbDelete.setText("Delete");
            jbDelete.setIcon(new ImageIcon(getClass().getResource("/com/pl/plugins/commons/ui/views/impl/document_delete.png")));
            jtbToolBar.add(jbDelete);
            jtbToolBar.addSeparator();

            //---- jbRefresh ----
            jbRefresh.setText("Refresh");
            jbRefresh.setIcon(new ImageIcon(getClass().getResource("/com/pl/plugins/commons/ui/views/impl/document_refresh.png")));
            jtbToolBar.add(jbRefresh);
            jtbToolBar.addSeparator();

            //---- jbFind ----
            jbFind.setText("Find");
            jbFind.setIcon(new ImageIcon(getClass().getResource("/com/pl/plugins/commons/ui/views/impl/document_find.png")));
            jtbToolBar.add(jbFind);
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Vlad Kimo
    private JToolBar jtbToolBar;
    private JButton jbNew;
    private JButton jbEdit;
    private JButton jbDelete;
    private JButton jbRefresh;
    private JButton jbFind;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

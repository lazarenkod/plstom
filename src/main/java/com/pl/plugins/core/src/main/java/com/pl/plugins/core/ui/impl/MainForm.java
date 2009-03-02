/*
 * Created by JFormDesigner on Wed Sep 17 22:27:07 MSD 2008
 */

package com.pl.plugins.core.ui.impl;

import java.awt.event.*;

import com.pl.plugins.core.ui.IMainForm;
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;

import javax.swing.*;
import java.awt.*;
import java.util.ResourceBundle;

/**
 * @author unknown
 */
public class MainForm extends JFrame implements IMainForm {

    private static MainForm instance;

    private MainForm() {
        initComponents();
        initLookAndFeel();
    }

    public static MainForm getInstance() {

        if (instance == null)
            instance = new MainForm();
        return instance;
    }

    private void initLookAndFeel() {

        try {

            UIManager.setLookAndFeel(WindowsLookAndFeel.class.getCanonicalName());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        SwingUtilities.updateComponentTreeUI(this);
    }

       public JMenuBar getMainMenuBar() {
        return jmbMainMenuBar;
    }

    public JToolBar getMainToolBar() {
        return jtbMainToolBar;
    }

    public void setMainToolBar(JToolBar value) {
        jtbMainToolBar = value;
    }

    public JToolBar getStatusBar() {
        return jtbStatusBar;
    }

    public JProgressBar getProgressBar() {
        return jpbProgress;
    }

    public void setProgressLabelText(String text){
        labelProgress.setText(text);
    }
    
    private void initComponents() {

        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        ResourceBundle bundle = ResourceBundle.getBundle("com.pl.plugins.core.ui.resources");
        jmbMainMenuBar = new JMenuBar();
        jtbMainToolBar = new JToolBar();
        jtbStatusBar = new JToolBar();
        labelProgress = new JLabel();
        jpbProgress = new JProgressBar();
        jtpPluginTabPanel = new JTabbedPane();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle(bundle.getString("MainForm.this.title"));
        setIconImage(new ImageIcon(getClass().getResource("/com/pl/plugins/core/appicon.jpg")).getImage());
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        setJMenuBar(jmbMainMenuBar);
        contentPane.add(jtbMainToolBar, BorderLayout.NORTH);

        //======== jtbStatusBar ========
        {

            //---- labelProgress ----
            labelProgress.setText(bundle.getString("MainForm.labelProgress.text"));
            jtbStatusBar.add(labelProgress);
            jtbStatusBar.addSeparator();

            //---- jpbProgress ----
            jpbProgress.setStringPainted(true);
            jpbProgress.setToolTipText(bundle.getString("MainForm.jpbProgress.toolTipText"));
            jpbProgress.setPreferredSize(new Dimension(50, 20));
            jpbProgress.setMaximumSize(new Dimension(150, 20));
            jpbProgress.setString("10%");
            jtbStatusBar.add(jpbProgress);
            jtbStatusBar.addSeparator();
        }
        contentPane.add(jtbStatusBar, BorderLayout.SOUTH);
        contentPane.add(jtpPluginTabPanel, BorderLayout.CENTER);
        setSize(840, 535);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    public JTabbedPane getJtpPluginTabPanel() {
        return jtpPluginTabPanel;
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JMenuBar jmbMainMenuBar;
    private JToolBar jtbMainToolBar;
    private JToolBar jtbStatusBar;
    private JLabel labelProgress;
    private JProgressBar jpbProgress;
    private JTabbedPane jtpPluginTabPanel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

/*
 * Created by JFormDesigner on Fri Oct 03 15:21:52 MSD 2008
 */

package com.pl.plugins.commons.ui.views.impl;

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

import com.pl.plugins.commons.ui.utils.dialog.IDialogDescriptor;
import org.jdesktop.swingx.*;

/**
 * @author Vlad Kimo
 */
public class SimpleDialog extends JDialog {

    private IDialogDescriptor dialogDescriptor = null;
    private View dialogContent = null;

    public SimpleDialog(Frame owner, IDialogDescriptor dialogDescriptor) {

        super(owner);
        initComponents();

        setDialogDescriptor(dialogDescriptor);
    }

    public SimpleDialog(Dialog owner, IDialogDescriptor dialogDescriptor) {

        super(owner);
        initComponents();

        setDialogDescriptor(dialogDescriptor);
    }

    public void setDialogDescriptor(IDialogDescriptor dialogDescriptor) {

        this.dialogDescriptor = dialogDescriptor;

        if(dialogDescriptor != null){

            setTitle(dialogDescriptor.getTitle());

            buttonBar.removeAll();

            for(JButton button : dialogDescriptor.getButtons()){
                buttonBar.add(button);
            }
        }
    }

    public void setContentPanel(View dialogContent){

        if(dialogContent != null){

            this.dialogContent = dialogContent;

            dialogPane.add(dialogContent, BorderLayout.CENTER);
            pack();
            setResizable(false);
        }
    }

    private void thisWindowOpened(WindowEvent e) {
        dialogContent.onShow();
    }

    private void thisWindowClosing(WindowEvent e) {
        dialogContent.onHide();
    }

    private void thisComponentHidden(ComponentEvent e) {
        dialogContent.onHide();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        dialogPane = new JPanel();
        buttonBar = new JPanel();

        //======== this ========
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                thisWindowClosing(e);
            }
            @Override
            public void windowOpened(WindowEvent e) {
                thisWindowOpened(e);
            }
        });
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentHidden(ComponentEvent e) {
                thisComponentHidden(e);
            }
        });
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setLayout(new BorderLayout());

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 85, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0};
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel dialogPane;
    private JPanel buttonBar;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

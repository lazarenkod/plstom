package com.pl.plugins.commons.ui.utils.dialog.impl;

import javax.swing.*;
import java.util.ArrayList;
import java.awt.event.ActionListener;

/**
 * Created by IntelliJ IDEA.
 * User: Vlad
 * Date: 04.10.2008
 * Time: 11:12:29
 * To change this template use File | Settings | File Templates.
 */
public class SimpleDialogDescriptor extends DialogDescriptor{

    JButton jbtnOk, jbtnCancel;

    public SimpleDialogDescriptor(String okText, String cancelText){

        jbtnOk = new JButton(okText);
        jbtnCancel = new JButton(cancelText);

        ArrayList<JButton> buttons = new ArrayList<JButton>();
        buttons.add(jbtnOk);
        buttons.add(jbtnCancel);
        
        setButtons(buttons);
    }

    public void setOkAction(ActionListener action){
        jbtnOk.removeActionListener(action);
        jbtnOk.addActionListener(action);
    }

    public void setCancelAction(ActionListener action){
        jbtnCancel.removeActionListener(action);
        jbtnCancel.addActionListener(action);
    }
}

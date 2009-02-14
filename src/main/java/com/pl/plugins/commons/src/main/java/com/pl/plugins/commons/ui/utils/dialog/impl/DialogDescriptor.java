package com.pl.plugins.commons.ui.utils.dialog.impl;

import com.pl.plugins.commons.ui.utils.dialog.IDialogDescriptor;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Vlad
 * Date: 04.10.2008
 * Time: 10:45:44
 * To change this template use File | Settings | File Templates.
 */
public class DialogDescriptor implements IDialogDescriptor {

    private ArrayList<JButton> buttons = null;

    private String title = null;

    public DialogDescriptor(){

    }

    public ArrayList<JButton> getButtons() {
        return buttons;
    }

    protected void setButtons(ArrayList<JButton> buttons) {
        this.buttons = buttons;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

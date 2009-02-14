package com.pl.plugins.commons.ui.utils.dialog;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Vlad
 * Date: 04.10.2008
 * Time: 11:11:06
 * To change this template use File | Settings | File Templates.
 */
public interface IDialogDescriptor {

    ArrayList<JButton> getButtons();

    //void setButtons(ArrayList<JButton> buttons);

    String getTitle();

    void setTitle(String title);
}

package com.pl.plugins.core.ui;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: Mostovoy.Vladislav
 * Date: 08.10.2008
 * Time: 14:53:37
 */
public interface IMainForm {

    JMenuBar getMainMenuBar();
    JToolBar getMainToolBar();
    void setMainToolBar(JToolBar jtb);
    JToolBar getStatusBar();
}

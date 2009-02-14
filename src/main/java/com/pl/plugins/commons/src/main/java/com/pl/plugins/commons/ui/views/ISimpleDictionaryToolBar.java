package com.pl.plugins.commons.ui.views;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Created by IntelliJ IDEA.
 * User: Mostovoy.Vladislav
 * Date: 23.09.2008
 * Time: 16:11:28
 * To change this template use File | Settings | File Templates.
 */
public interface ISimpleDictionaryToolBar {
    JToolBar getToolBar();

    void setNewAction(ActionListener value);

    void setEditAction(ActionListener value);

    void setDeleteAction(ActionListener value);

    void setRefreshAction(ActionListener value);

    void setFindAction(ActionListener value);

    void plug();

    void setVisible(boolean value);
}

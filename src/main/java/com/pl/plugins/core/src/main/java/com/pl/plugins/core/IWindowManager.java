package com.pl.plugins.core;

import com.pl.plugins.core.ui.TopComponent;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 21.02.2009
 * Time: 14:58:03
 */
public interface IWindowManager {
    JPanel findTopComponent(String preferredId);

    void addPanel(TopComponent tc);

    void removePanel(TopComponent tc);

    void setActivePanel(TopComponent tc);
}

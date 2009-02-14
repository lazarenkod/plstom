/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 13.02.2009
 * Time: 12:24:59
 */
package com.pl.plugins.commons.ui.uinew.core.ui;

import javax.swing.*;

public class WindowManager {
    private static WindowManager instance = new WindowManager();

    public static WindowManager getDefault() {
        return instance;
    }

    private WindowManager() {
    }
    public JPanel findTopComponent(String preferredId){
        return null;///todo сделать WindowManager 
    }
}

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 13.02.2009
 * Time: 12:24:59
 */
package com.pl.plugins.core;

import com.pl.plugins.core.ui.TopComponent;
import com.pl.plugins.core.ui.impl.MainForm;
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;

import javax.swing.*;

/**
 *  ласс который должен управл€ть взаимодействи€ми с окнами
 */
public class WindowManager implements IWindowManager {
    private MainForm mainForm;

    private WindowManager(MainForm mainForm) {
        this.mainForm = mainForm;
        initLookAndFeel();
    }

    public JPanel findTopComponent(String preferredId) {
        return null;///todo сделать WindowManager 
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

        SwingUtilities.updateComponentTreeUI(mainForm);
    }

    /**
     * ƒобавл€ет панель к списку табов
     *
     * @param tc
     */
    public void addPanel(TopComponent tc) {
        mainForm.getJtpPluginTabPanel().addTab(tc.getTitle(), tc);
        mainForm.getJtpPluginTabPanel().setSelectedComponent(tc);
    }

    /**
     * ”дал€ет панель из списка табов
     *
     * @param tc
     */
    public void removePanel(TopComponent tc) {
        mainForm.getJtpPluginTabPanel().remove(tc);

    }

    private JMenuBar getMenuBar() {
        return mainForm.getMainMenuBar();
    }

    public JToolBar getToolBar() {
        return mainForm.getMainToolBar();
    }

    private JProgressBar getProgressBar() {
        return mainForm.getProgressBar();
    }

    private void setProgressText(String text) {
        mainForm.setProgressLabelText(text);
    }

    //todo —делать механизм отображени€ долгих операций
    public void startLongOperation(String operationTitle) {
        setProgressText(operationTitle);
        getProgressBar().setIndeterminate(true);
    }

    public void endLongOperation() {
        setProgressText("");
        getProgressBar().setIndeterminate(false);

    }

    public void setProgressValue(int value) {
//        getProgressBar().set
    }

    public void setActivePanel(TopComponent tc) {
        mainForm.getJtpPluginTabPanel().setSelectedComponent(tc);
    }
}

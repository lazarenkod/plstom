package com.pl.plugins.core.ui;

import com.pl.plugins.core.CorePlugin;
import com.pl.plugins.core.WindowManager;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 16.02.2009
 * Time: 17:07:37
 */
public abstract class TopComponent  extends JPanel{

    private WindowManager windowManager;
    protected String title=""; 

    protected TopComponent() {
        windowManager =  (WindowManager) CorePlugin.getAppContext().getBean("windowManager");
    }

    /**
     * @return Предпочтительный ID topComponent-а
     */
    public String getPrefferedId() {
        return null;
    }

    /**
     * @return Название которое будет отображаться в заголовке вкладки
     */
    public String getTitle() {
        return title;
    }

    /**
     * Отображает компонент
     */
    public void open(){
        windowManager.addPanel(this);
    }

    /**
     * скрывает компонент
     */
    public void close(){
        windowManager.removePanel(this);
    }
}

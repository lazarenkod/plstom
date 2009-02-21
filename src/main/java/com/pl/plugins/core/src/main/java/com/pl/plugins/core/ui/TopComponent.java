package com.pl.plugins.core.ui;

import com.pl.plugins.core.CorePlugin;
import com.pl.plugins.core.IWindowManager;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 16.02.2009
 * Time: 17:07:37
 */
public abstract class TopComponent  extends JPanel{
                  
    private IWindowManager windowManager;
    protected String title=""; 

    protected TopComponent() {
        windowManager =  (IWindowManager) CorePlugin.getAppContext().getBean("windowManager");
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

    public void requestActive(){
        windowManager.setActivePanel(this);
    }
}

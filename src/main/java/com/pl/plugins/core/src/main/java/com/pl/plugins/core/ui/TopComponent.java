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
     * @return ���������������� ID topComponent-�
     */
    public String getPrefferedId() {
        return null;
    }

    /**
     * @return �������� ������� ����� ������������ � ��������� �������
     */
    public String getTitle() {
        return title;
    }

    /**
     * ���������� ���������
     */
    public void open(){
        windowManager.addPanel(this);
    }

    /**
     * �������� ���������
     */
    public void close(){
        windowManager.removePanel(this);
    }
}

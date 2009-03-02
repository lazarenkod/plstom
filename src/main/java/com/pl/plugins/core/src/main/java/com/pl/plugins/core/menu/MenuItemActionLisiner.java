package com.pl.plugins.core.menu;

import org.java.plugin.PluginManager;
import org.java.plugin.registry.ExtensionPoint;
import org.java.plugin.registry.Extension;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import com.pl.plugins.core.CorePlugin;

/**
 * Created by IntelliJ IDEA.
 * User: Mostovoy.Vladislav
 * Date: 16.10.2008
 * Time: 9:37:56
 * Класс предназначен для реакции на выбор пункта меню. Метод actionPerformed
 * вызывает actionPerformed  у конкретного представителя IActionNode 
 */

class MenuItemActionListener implements ActionListener {

    private String instanceClass;

    MenuItemActionListener(String instanceClass) {
        this.instanceClass = instanceClass;
    }

    public void actionPerformed(ActionEvent e) {

        PluginManager pluginManager = CorePlugin.getPluginManager();

        ExtensionPoint exPoint = pluginManager.getRegistry().getExtensionPoint(CorePlugin.PLUGIN_ID, "PLPlugin");

        for (Extension ext : exPoint.getConnectedExtensions()) {

            ClassLoader classLoader = pluginManager.getPluginClassLoader(ext.getDeclaringPluginDescriptor());

            try {

                Class<?> actionClass = classLoader.loadClass(instanceClass);
                if (actionClass.isAssignableFrom(IActionNode.class)) {
                    Object ob = actionClass.newInstance();
                    actionClass.getMethod("performAction", null).invoke(ob, null);

//                 JPanel pluginContainer = new JPanel();
//                 IWindowManager windowManager= (IWindowManager) CorePlugin.getAppContext().getBean("windowManager");
//                 IMainForm mainForm= (MainForm) CorePlugin.getAppContext().getBean("mainForm");
//                 mainForm.attachPlugin(pluginContainer, displayName);

                }
            } catch (Exception ex) {

                ex.printStackTrace();
            }
        }
    }

    public void setInstanceClass(String instanceClass) {
        this.instanceClass = instanceClass;
    }
}

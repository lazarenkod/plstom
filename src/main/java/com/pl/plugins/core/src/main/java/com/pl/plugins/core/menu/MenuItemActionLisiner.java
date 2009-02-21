package com.pl.plugins.core.menu;

import org.java.plugin.PluginManager;
import org.java.plugin.registry.ExtensionPoint;
import org.java.plugin.registry.Extension;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import com.pl.plugins.core.CorePlugin;
import com.pl.plugins.core.ui.impl.MainForm;
import com.pl.plugins.core.ui.IMainForm;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: Mostovoy.Vladislav
 * Date: 16.10.2008
 * Time: 9:37:56
 */
class MenuItemActionListener implements ActionListener {

    private String instanceClass;
    private String displayName;

    MenuItemActionListener(String instanceClass, String displayName) {
        this.instanceClass = instanceClass;
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void actionPerformed(ActionEvent e) {

        PluginManager pluginManager= CorePlugin.getPluginManager();

        ExtensionPoint exPoint = pluginManager.getRegistry().getExtensionPoint(CorePlugin.PLUGIN_ID, "PLPlugin");

        for (Extension ext : exPoint.getConnectedExtensions()) {

            ClassLoader classLoader = pluginManager.getPluginClassLoader(ext.getDeclaringPluginDescriptor());

            if (instanceClass.equals(ext.getDeclaringPluginDescriptor().getPluginClassName()))
            try {

                    Class<?> pluginClass = classLoader.loadClass(instanceClass);

                    Object ob = pluginClass.newInstance();

                    JPanel pluginContainer = new JPanel();
                    IMainForm mainForm= (MainForm) CorePlugin.getAppContext().getBean("mainForm");
                    mainForm.attachPlugin(pluginContainer, displayName);

                    pluginClass.getMethod("init", new Class[]{JComponent.class}).invoke(ob, new Object[]{pluginContainer});

            }catch (Exception ex) {

                ex.printStackTrace();
            }
        }
    }

    public void setInstanceClass(String instanceClass) {
            this.instanceClass = instanceClass;
        }
    }

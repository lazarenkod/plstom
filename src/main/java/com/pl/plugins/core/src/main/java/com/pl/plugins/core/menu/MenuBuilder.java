package com.pl.plugins.core.menu;

import com.pl.plugins.core.CorePlugin;
import org.apache.commons.digester.Digester;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.java.plugin.PluginManager;
import org.java.plugin.registry.Extension;
import org.java.plugin.registry.ExtensionPoint;
import org.java.plugin.registry.PluginDescriptor;
import org.xml.sax.SAXException;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 29.09.2008
 * Time: 17:23:56
 */
public class MenuBuilder {

    private LayersParser layersParser = null;

    public MenuBuilder(LayersParser layersParser) {
        this.layersParser = layersParser;
    }

    public void buildMenu(JMenuBar menuBar) {
        try {
        
            buildMenu(layersParser.parse() .getFolders(), menuBar, null);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    private void buildMenu(ArrayList<FolderNode> folderNodes, JMenuBar menuBar, JMenu menu) {

        for (FolderNode folder : folderNodes) {

            ArrayList<AtrNode> atrs = folder.getAtrs();

            JMenu subMenu = new JMenu();
            subMenu.setText(folder.getDisplayName());
            subMenu.setName(folder.getName());


            String name = null;
            String instanceClass = null;
            String displayName = null;

            for (AtrNode atr : atrs) {

                if (atr.getName().equals("name"))
                    name = atr.getStringvalue();
                if (atr.getName().equals("instanceClass"))
                    instanceClass = atr.getStringvalue();
                if (atr.getName().equals("displayName"))
                    displayName = atr.getStringvalue();
            }

            if (name != null && displayName != null && instanceClass != null) {

                JMenuItem menuItem = new JMenuItem();
                menuItem.setText(displayName);
                menuItem.setName(name);
                menuItem.addActionListener(new MenuItemActionListener(instanceClass,displayName));

                subMenu.add(menuItem);
            }

            if (menu == null)
                menuBar.add(subMenu);
            else
                menu.add(subMenu);

            buildMenu(folder.getChildFolders(), menuBar, subMenu);
        }
    }
}





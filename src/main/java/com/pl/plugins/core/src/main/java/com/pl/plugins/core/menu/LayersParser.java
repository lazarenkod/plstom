package com.pl.plugins.core.menu;

import org.java.plugin.PluginManager;
import org.java.plugin.registry.ExtensionPoint;
import org.java.plugin.registry.Extension;
import org.java.plugin.registry.PluginDescriptor;
import org.xml.sax.SAXException;
import org.apache.commons.digester.Digester;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.pl.plugins.core.CorePlugin;

/**
 * Created by IntelliJ IDEA.
 * User: Mostovoy.Vladislav
 * Date: 16.10.2008
 * Time: 10:00:47
 */
public class LayersParser {

    private Log log = LogFactory.getLog(LayersParser.class);

    private PluginManager pluginManager = null;

    public LayersParser(PluginManager pluginManager) {
        this.pluginManager = pluginManager;
    }

    public Layer parse() throws IOException, SAXException {

            Digester digester = new Digester();
            digester.setValidating(false);
            digester.addObjectCreate("filesystem", "com.pl.plugins.core.menu.Layer");
            digester.addObjectCreate("*/folder", "com.pl.plugins.core.menu.FolderNode");
            digester.addSetProperties("*/folder", "name", "name");
            digester.addSetProperties("*/folder", "displayName", "displayName");
            digester.addObjectCreate("*/folder/attr", "com.pl.plugins.core.menu.AtrNode");
            digester.addSetProperties("*/folder/attr", "name", "atrName");
            digester.addSetProperties("*/folder/attr", "stringvalue", "atrValue");
            digester.addSetNext("*/folder/attr", "addAtr");
            digester.addSetNext("*/folder", "addFolder");

            if (pluginManager!=null)
                log.debug("pluginmanager is not null"+pluginManager+"\n");
            else
                log.debug("plugin manager is null");

            ExtensionPoint exPoint = pluginManager.getRegistry().
                                     getExtensionPoint(CorePlugin.PLUGIN_ID, "PLPlugin");

            ArrayList<Layer> layersList = new ArrayList<Layer>();

            for (Extension ext : exPoint.getConnectedExtensions()) {

                ClassLoader classLoader = pluginManager.getPluginClassLoader(ext.getDeclaringPluginDescriptor());

                try {
                        pluginManager.activatePlugin(ext.getExtendedPluginId());
                        pluginManager.getRegistry().getDependingPlugins(ext.getDeclaringPluginDescriptor());

                        for (PluginDescriptor o : pluginManager.getRegistry().getDependingPlugins(ext.getDeclaringPluginDescriptor()))
                        {
                            pluginManager.activatePlugin(o.getId());
                        }

                        pluginManager.enablePlugin(ext.getDeclaringPluginDescriptor(),true);

                        String className = ext.getParameter("class").valueAsString();

                        Class<?> plugClass = classLoader.loadClass(className);

                        InputStreamReader is = new InputStreamReader(plugClass.getResourceAsStream("layer.xml"));

                        digester.setClassLoader(plugClass.getClassLoader());

                        Layer layer = (Layer) digester.parse(is);
                        layersList.add(layer);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            Layer result = layersList.get(0);

            for (int i = 1; i < layersList.size(); i++) {

                Layer layer = layersList.get(i);
                merge(result.getFolders(), layer.getFolders());
            }

            return result;
        }

        private static void merge(ArrayList<FolderNode> mainFolders, ArrayList<FolderNode> oneFolders) {

            for (FolderNode oneFolder : oneFolders) {

                boolean found = false;

                for (FolderNode mainFolder : mainFolders) {
                    if (mainFolder.getName().equals(oneFolder.getName())) {
                        found = true;
                        break;
                    }
                }

                if (!found)
                    mainFolders.add(oneFolder);

                if (oneFolder.getChildFolders() != null && !oneFolder.getChildFolders().isEmpty())
                    for (FolderNode mainFolder : mainFolders) {
                        merge(mainFolder.getChildFolders(), oneFolder.getChildFolders());
                    }
            }
        }
}

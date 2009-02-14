package com.pl.plugins.core;

import com.pl.plugins.core.menu.LayersParser;
import com.pl.plugins.core.menu.MenuBuilder;
import com.pl.plugins.core.ui.impl.MainForm;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.java.plugin.PluginManager;
import org.java.plugin.boot.Application;
import org.java.plugin.boot.ApplicationPlugin;
import org.java.plugin.registry.Extension;
import org.java.plugin.registry.PluginDescriptor;
import org.java.plugin.registry.ExtensionPoint;
import org.java.plugin.util.ExtendedProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Properties;
import java.util.ArrayList;


/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 15.09.2008
 * Time: 10:16:47
 */
public class CorePlugin extends ApplicationPlugin implements Application {

    public static final String PLUGIN_ID = "com.pl.plugins.core";

    private static ApplicationContext appContext =  null;

    private static  PluginManager pluginManager;

    private Log log= LogFactory.getLog(getClass());
    private File dataFolder;

    private MainForm frame;

    private JTabbedPane tabbedPane;

    public static PluginManager getPluginManager() {
        return pluginManager;
    }

    public static ApplicationContext getAppContext() {
        return appContext;
    }

    protected Application initApplication(ExtendedProperties config, String[] strings) throws Exception {

        dataFolder = new File(config.getProperty("dataFolder",
                "." + File.separator + "data"));
        dataFolder = dataFolder.getCanonicalFile();

        log.debug("data folder - " + dataFolder);

        if (!dataFolder.isDirectory() && !dataFolder.mkdirs()) {
            throw new Exception("data folder " + dataFolder + " not found");
        }

        return this;
    }

    public File getDataFolder(final PluginDescriptor descr) throws IOException {

        File result = new File(dataFolder, descr.getId());

        if (!result.isDirectory() && !result.mkdirs()) {
            throw new IOException("can't create data folder " + result
                    + " for plug-in " + descr.getId());
        }
        
        return result;
    }

    protected void doStart() throws Exception {

    }

    protected void doStop() throws Exception {

    }

    public void startApplication() throws Exception {

        pluginManager = PluginManager.lookup(this);

        appContext =  new ClassPathXmlApplicationContext(findSpringConfigs());
        
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });

    }
    
    public String[] findSpringConfigs()
    {
        if (pluginManager!=null)
            log.debug("pluginmanager is not null"+pluginManager+"\n");
        else
            log.debug("plugin manager is null");

        ExtensionPoint exPoint = pluginManager.getRegistry().
                                 getExtensionPoint(CorePlugin.PLUGIN_ID, "PLPlugin");

        ArrayList<String> configs = new ArrayList<String>();
        configs.add("com/pl/plugins/core/pluginContext.xml");

        for (Extension ext : exPoint.getConnectedExtensions()) {
            try {

                    String conf = ext.getParameter("springConfig").valueAsString();
                    configs.add(conf);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String[] s = new String[configs.size()];

        return configs.toArray(s);
    }
    protected void createAndShowGUI() {

        frame = (MainForm) getAppContext().getBean("mainForm");

        log.info("pluginManager: "+pluginManager+"\n");

        MenuBuilder mb=new MenuBuilder(new LayersParser((pluginManager)));

        mb.buildMenu(frame.getMainMenuBar());

        frame.setVisible(true);
    }

    /*
    * This method activates (initializes Tool) if it wasn't activated yet.
    */
    protected void activateTool(final JComponent toolComponent) {

        Extension ext =
                (Extension) toolComponent.getClientProperty("extension");

        frame.setTitle("[PL Stomatology] - "
                + ext.getParameter("name").valueAsString());

        IPLPlugin tool = (IPLPlugin) toolComponent.getClientProperty("toolInstance");

        if (tool == null) {
            try {
                // Activate plug-in that declares extension.
                getManager().activatePlugin(
                        ext.getDeclaringPluginDescriptor().getId());
                // Get plug-;in class loader.
                ClassLoader classLoader = getManager().getPluginClassLoader(
                        ext.getDeclaringPluginDescriptor());
                // Load Tool class.
                Class<?> toolCls = classLoader.loadClass(
                        ext.getParameter("class").valueAsString());
                // Create Tool instance.
                tool = (IPLPlugin) toolCls.newInstance();
                // Initialize class instance according to interface contract.
                tool.init(toolComponent);
            } catch (Throwable t) {
                toolComponent.setLayout(new BorderLayout());
                toolComponent.add(new JLabel(t.toString()), BorderLayout.NORTH);
                JScrollPane scrollPane = new JScrollPane();
                toolComponent.add(scrollPane, BorderLayout.CENTER);
                StringBuffer sb = new StringBuffer();
                String nl = System.getProperty("line.separator");
                Throwable err = t;
                while (err != null) {
                    if (err != t) {
                        sb.append(nl).append("Caused by " + err).append(nl).append(nl);
                    }
                    StackTraceElement[] stackTrace = err.getStackTrace();
                    for (int i = 0; i < stackTrace.length; i++) {
                        sb.append(stackTrace[i].toString()).append(nl);
                    }
                    err = err.getCause();
                }
                JTextArea textArea = new JTextArea(sb.toString());
                textArea.setBackground(java.awt.SystemColor.control);
                textArea.setEditable(false);
                scrollPane.setViewportView(textArea);
                textArea.setCaretPosition(0);
                return;
            }
            toolComponent.putClientProperty("toolInstance", tool);
        }
    }

    private File getConfigFile() throws IOException {
        File result = new File(getDataFolder(getDescriptor()),
                "config.properties");
        if (!result.exists() && !result.createNewFile()) {
            throw new IOException("can't create configuration file " + result);
        }
        return result;
    }

    protected void saveState() {
        Properties props = new Properties();
        props.setProperty("window.X", "" + frame.getX());
        props.setProperty("window.Y", "" + frame.getY());
        props.setProperty("window.width", "" + frame.getWidth());
        props.setProperty("window.height", "" + frame.getHeight());
        JComponent toolComponent = (JComponent) tabbedPane.getComponent(
                tabbedPane.getSelectedIndex());
        Extension ext =
                (Extension) toolComponent.getClientProperty("extension");
        props.setProperty("window.activeTool", ext.getUniqueId());
        try {
            OutputStream strm = new FileOutputStream(getConfigFile(), false);
            try {
                props.store(strm,
                        "This is automatically generated configuration file.");
            } finally {
                strm.close();
            }
        } catch (Exception e) {
            log.error("can't save program state", e);
        }
    }

    private void readState() {
        Properties props = new Properties();
        try {
            InputStream strm = new FileInputStream(getConfigFile());
            try {
                props.load(strm);
            } finally {
                strm.close();
            }
        } catch (Exception e) {
            log.error("can't load program state", e);
        }
        frame.setBounds(
                Integer.parseInt(props.getProperty("window.X", "10"), 10),
                Integer.parseInt(props.getProperty("window.Y", "10"), 10),
                Integer.parseInt(props.getProperty("window.width", "600"), 10),
                Integer.parseInt(props.getProperty("window.height", "500"), 10));
        String activeTool = props.getProperty("window.activeTool");
        if (activeTool == null) {
            activateTool((JComponent) tabbedPane.getComponent(
                    tabbedPane.getSelectedIndex()));
        } else {
            boolean activated = false;
            for (int i = 0; i < tabbedPane.getTabCount(); i++) {
                JComponent toolComponent =
                        (JComponent) tabbedPane.getComponent(i);
                Extension ext =
                        (Extension) toolComponent.getClientProperty("extension");
                if (activeTool.equals(ext.getUniqueId())) {
                    activateTool(toolComponent);
                    tabbedPane.setSelectedIndex(i);
                    activated = true;
                    break;
                }
            }
            if (!activated) {
                activateTool((JComponent) tabbedPane.getComponent(
                        tabbedPane.getSelectedIndex()));
            }
        }
    }

}

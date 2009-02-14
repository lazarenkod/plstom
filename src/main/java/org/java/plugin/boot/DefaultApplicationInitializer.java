/*****************************************************************************
 * Java Plug-in Framework (JPF)
 * Copyright (C) 2004-2007 Dmitry Olshansky
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 *****************************************************************************/
package org.java.plugin.boot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.java.plugin.ObjectFactory;
import org.java.plugin.Plugin;
import org.java.plugin.PluginManager;
import org.java.plugin.PluginManager.PluginLocation;
import org.java.plugin.registry.IntegrityCheckReport;
import org.java.plugin.registry.ManifestInfo;
import org.java.plugin.registry.ManifestProcessingException;
import org.java.plugin.registry.PluginRegistry;
import org.java.plugin.util.ExtendedProperties;
import org.java.plugin.util.IoUtil;
import org.java.plugin.util.ResourceManager;

/**
 * Default implementation of the application initializer interface.
 * <p>
 * Supported configuration parameters:
 * <dl>
 *   <dt>org.java.plugin.boot.applicationPlugin</dt>
 *   <dd>ID of plug-in to start. There is no default value for this parameter.
 *     In common scenario, this is the only parameter that you must provide.</dd>
 *   <dt>org.java.plugin.boot.integrityCheckMode</dt>
 *   <dd>Regulates how to check plug-ins integrity when running JPF. Possible
 *     values: <code>full</code>, <code>light</code>, <code>off</code>. The
 *     default value is <code>full</code>.</dd>
 *   <dt>org.java.plugin.boot.pluginsCollector</dt>
 *   <dd>Plug-ins location collector class, for details see
 *     {@link org.java.plugin.boot.PluginsCollector}. Default is
 *     {@link org.java.plugin.boot.DefaultPluginsCollector}.</dd>
 *   <dt>org.java.plugin.boot.pluginsWhiteList</dt>
 *   <dd>Location of the file with plug-in identifiers that should be only
 *     accepted by this application initializer. This is optional parameter.</dd>
 *   <dt>org.java.plugin.boot.pluginsBlackList</dt>
 *   <dd>Location of the file with plug-in identifiers that should not be
 *     accepted by this application initializer. This is optional parameter.</dd>
 * </dl>
 * Note that all given configuration parameters are passed to
 * {@link org.java.plugin.ObjectFactory#newInstance(ExtendedProperties)}
 * when running JPF (see bellow). This allows you to configure JPF precisely.
 * </p>
 * <p><i>Black and white lists of plug-ins</i></p>
 * <p>
 * In some situations you may want to temporary exclude some of your plug-ins
 * from the application scope. This may be achieved with help of while and black
 * lists - simple plain text files that contain lists of plug-in identifiers to
 * be included/excluded from the application. Each identifies should be in
 * separate line. You may provide unique plug-in ID also.
 * </p>
 * <p><i>What is application plug-in?</i></p>
 * <p>
 * When application starts, the
 * {@link org.java.plugin.boot.Boot#main(String[])} method executed calling
 * {@link #initApplication(BootErrorHandler, String[])} to get initialized
 * instance of {@link org.java.plugin.boot.Application}
 * (or {@link org.java.plugin.boot.ServiceApplication}) class. The method
 * {@link #initApplication(BootErrorHandler, String[])} in this implementation
 * scans plug-in repositories to collect all available plug-in files and folders
 * (using special class that can be customized),
 * instantiates JPF and publishes all discovered plug-ins. After that it asks
 * {@link org.java.plugin.PluginManager} for an <b>Application Plug-in</b> with
 * ID provided as configuration parameter. Returned class instance is expected
 * to be of type {@link org.java.plugin.boot.ApplicationPlugin} and it's method
 * {@link org.java.plugin.boot.ApplicationPlugin#initApplication(ExtendedProperties, String[])}
 * called.
 * </p>
 * <p>
 * To the mentioned <code>initApplication</code> method passed a subset of
 * configuration properties whose names start with application plug-in ID
 * followed with dot character <code>'.'</code> (see
 * {@link org.java.plugin.util.ExtendedProperties#getSubset(String)} for
 * details).
 * </p>
 * <p>
 * As a result of the described procedure, the <code>Boot</code> get instance of
 * {@link org.java.plugin.boot.Application} interface, so it can start
 * application calling
 * {@link org.java.plugin.boot.Application#startApplication()} method.
 * </p> 
 * 
 * @version $Id$
 */
public class DefaultApplicationInitializer implements ApplicationInitializer {
    protected static final String PARAM_APPLICATION_PLUGIN =
        "org.java.plugin.boot.applicationPlugin"; //$NON-NLS-1$
    protected static final String PARAM_INTEGRITY_CHECK_MODE =
        "org.java.plugin.boot.integrityCheckMode"; //$NON-NLS-1$
    protected static final String PARAM_PLUGINS_COLLECTOR =
        "org.java.plugin.boot.pluginsCollector"; //$NON-NLS-1$
    protected static final String PARAM_PLUGINS_WHITE_LIST =
        "org.java.plugin.boot.pluginsWhiteList"; //$NON-NLS-1$
    protected static final String PARAM_PLUGINS_BLACK_LIST =
        "org.java.plugin.boot.pluginsBlackList"; //$NON-NLS-1$

    private Log log;
    private ExtendedProperties config;
    private String integrityCheckMode;
    private PluginsCollector collector;
    private Set<String> whiteList;
    private Set<String> blackList;

    /**
     * Configures this instance and application environment. The sequence is:
     * <ul>
     *   <li>Configure logging system. There is special code for supporting
     *     <code>Log4j</code> logging system only. All other systems support
     *     come from <code>commons-logging</code> package.</li>
     *   <li>Instantiate and configure {@link PluginsCollector} using
     *     configuration data.</li>
     * </ul>
     * @see org.java.plugin.boot.ApplicationInitializer#configure(
     *      org.java.plugin.util.ExtendedProperties)
     */
    public void configure(final ExtendedProperties configuration)
            throws Exception {
        // Initializing logging system.
        String log4jConfigKey = "log4j.configuration"; //$NON-NLS-1$
        if (System.getProperty(log4jConfigKey) == null) {
            // Trying to find log4j configuration.
            if (configuration.containsKey(log4jConfigKey)) {
                System.setProperty(log4jConfigKey,
                        configuration.getProperty(log4jConfigKey));
            } else {
                File log4jConfig = new File(
                        configuration.getProperty("applicationRoot") //$NON-NLS-1$
                        + File.separator + "log4j.properties"); //$NON-NLS-1$
                if (!log4jConfig.isFile()) {
                    log4jConfig = new File(
                            configuration.getProperty("applicationRoot") //$NON-NLS-1$
                            + File.separator + "log4j.xml"); //$NON-NLS-1$
                }
                if (log4jConfig.isFile()) {
                    try {
                        System.setProperty(log4jConfigKey,
                                IoUtil.file2url(log4jConfig).toExternalForm());
                    } catch (MalformedURLException e) {
                        // ignore
                    }
                }
            }
        }
        log = LogFactory.getLog(getClass());
        log.info("logging system initialized"); //$NON-NLS-1$
        log.info("application root is " //$NON-NLS-1$
                + configuration.getProperty("applicationRoot")); //$NON-NLS-1$
        config = configuration;
        integrityCheckMode =
            configuration.getProperty(PARAM_INTEGRITY_CHECK_MODE, "full"); //$NON-NLS-1$
        collector = getCollectorInstance(
                configuration.getProperty(PARAM_PLUGINS_COLLECTOR));
        collector.configure(configuration);
        log.debug("plug-ins collector is " + collector); //$NON-NLS-1$
        try {
            whiteList = loadList(
                    configuration.getProperty(PARAM_PLUGINS_WHITE_LIST, null));
        } catch (IOException ioe) {
            log.warn("failed loading white list", ioe); //$NON-NLS-1$
        }
        if (whiteList != null) {
            log.debug("white list loaded"); //$NON-NLS-1$
        }
        try {
            blackList = loadList(
                    configuration.getProperty(PARAM_PLUGINS_BLACK_LIST, null));
        } catch (IOException ioe) {
            log.warn("failed loading black list", ioe); //$NON-NLS-1$
        }
        if (blackList != null) {
            log.debug("black list loaded"); //$NON-NLS-1$
        }
    }
    
    private Set<String> loadList(final String location) throws IOException {
        if (location == null) {
            return null;
        }
        final Set<String> result = new HashSet<String>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(location), "UTF-8")); //$NON-NLS-1$
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.length() > 0) {
                    result.add(line);
                }
            }
        } finally {
            reader.close();
        }
        log.debug("read " + result.size() //$NON-NLS-1$
                + " list items from " + location); //$NON-NLS-1$
        return result;
    }
    
    private PluginsCollector getCollectorInstance(
            final String className) {
        if (className != null) {
            try {
                return (PluginsCollector) Class.forName(className)
                    .newInstance();
            } catch (InstantiationException ie) {
                log.warn("failed instantiating plug-ins collector " //$NON-NLS-1$
                        + className, ie);
            } catch (IllegalAccessException iae) {
                log.warn("failed instantiating plug-ins collector " //$NON-NLS-1$
                        + className, iae);
            } catch (ClassNotFoundException cnfe) {
                log.warn("failed instantiating plug-ins collector " //$NON-NLS-1$
                        + className, cnfe);
            }
        }
        return new DefaultPluginsCollector();
    }
    
    /**
     * Initializes application. The sequence is:
     * <ul>
     *   <li>Collect plug-ins locations using configured
     *     {@link PluginsCollector}.</li>
     *   <li>Get {@link PluginManager} instance from {@link ObjectFactory}
     *     using code
     *     <code>ObjectFactory.newInstance(config).createManager()</code>.</li>
     *   <li>Publish collected plug-ins using
     *     {@link PluginManager#publishPlugins(org.java.plugin.PluginManager.PluginLocation[])}.</li>
     *   <li>Check integrity if that's configured.</li>
     *   <li>Get application plug-in and call it
     *     <code>JpfApplication initApplication(Properties)</code> method.</li>
     *   <li>Return received instance of {@link Application} interface.</li>
     * </ul>
     * 
     * @see org.java.plugin.boot.ApplicationInitializer#initApplication(
     *      BootErrorHandler, String[])
     */
    public Application initApplication(final BootErrorHandler errorHandler,
            final String[] args) throws Exception {
        // Prepare parameters to start plug-in manager.
        log.debug("collecting plug-in locations"); //$NON-NLS-1$
        Collection<PluginLocation> pluginLocations = collector.collectPluginLocations();
        log.debug("collected " + pluginLocations.size() //$NON-NLS-1$
                + " plug-in locations, instantiating plug-in manager"); //$NON-NLS-1$
        // Create instance of plug-in manager.
        PluginManager pluginManager =
            ObjectFactory.newInstance(config).createManager();
        pluginLocations = filterPluginLocations(pluginManager.getRegistry(),
                pluginLocations);
        log.debug(pluginLocations.size() + " plug-in locations remain after " //$NON-NLS-1$
                + "applying filters, publishing plug-ins"); //$NON-NLS-1$
        // Publish discovered plug-in manifests and corresponding plug-in folders.
        pluginManager.publishPlugins(
                pluginLocations.toArray(
                        new PluginLocation[pluginLocations.size()]));
        if (!"off".equalsIgnoreCase(integrityCheckMode)) { //$NON-NLS-1$
            // Check plug-in's integrity.
            log.debug("checking plug-ins set integrity"); //$NON-NLS-1$
            IntegrityCheckReport integrityCheckReport =
                pluginManager.getRegistry().checkIntegrity(
                        "light".equalsIgnoreCase(integrityCheckMode) ? null //$NON-NLS-1$
                            : pluginManager.getPathResolver());
            log.info("integrity check done: errors - " //$NON-NLS-1$
                    + integrityCheckReport.countErrors() + ", warnings - " //$NON-NLS-1$
                    + integrityCheckReport.countWarnings());
            if (integrityCheckReport.countErrors() != 0) {
                // Something wrong in plug-ins set.
                log.info(integrityCheckReport2str(integrityCheckReport));
                if (!errorHandler.handleError(ResourceManager.getMessage(
                        Boot.PACKAGE_NAME, "integrityCheckFailed"), //$NON-NLS-1$
                        integrityCheckReport)) {
                    return null;
                }
            } else if (log.isDebugEnabled()
                    && ((integrityCheckReport.countErrors() > 0)
                    || (integrityCheckReport.countWarnings() > 0))) {
                log.debug(integrityCheckReport2str(integrityCheckReport));
            }
        }
        // application plug-in ID
        String appPluginId = config.getProperty(PARAM_APPLICATION_PLUGIN);
        log.info("application plug-in is " + appPluginId); //$NON-NLS-1$
        // get the start-up application plug-in
        Plugin appPlugin = pluginManager.getPlugin(appPluginId);
        log.debug("got application plug-in " + appPlugin //$NON-NLS-1$
                + ", initializing application"); //$NON-NLS-1$
        if (!(appPlugin instanceof ApplicationPlugin)) {
            log.error("application plug-in class " //$NON-NLS-1$
                    + appPlugin.getClass().getName()
                    + " doesn't assignable with " //$NON-NLS-1$
                    + ApplicationPlugin.class.getName());
            throw new ClassCastException(appPlugin.getClass().getName());
        }
        return ((ApplicationPlugin) appPlugin).initApplication(
                config.getSubset(appPluginId + "."), args); //$NON-NLS-1$
    }
    
    protected String integrityCheckReport2str(final IntegrityCheckReport report) {
        StringBuilder buf = new StringBuilder();
        buf.append("integrity check report:\r\n"); //$NON-NLS-1$
        buf.append("-------------- REPORT BEGIN -----------------\r\n"); //$NON-NLS-1$
        for (IntegrityCheckReport.ReportItem item : report.getItems()) {
            buf.append("\tseverity=").append(item.getSeverity()) //$NON-NLS-1$
                .append("; code=").append(item.getCode()) //$NON-NLS-1$
                .append("; message=").append(item.getMessage()) //$NON-NLS-1$
                .append("; source=").append(item.getSource()) //$NON-NLS-1$
                .append("\r\n"); //$NON-NLS-1$
        }
        buf.append("-------------- REPORT END -----------------"); //$NON-NLS-1$
        return buf.toString();
    }

    /**
     * This method may remove unwanted plug-in locations from the given list.
     * Standard implementation applies black/white lists logic.
     * @param registry plug-in registry to process manifests
     * @param pluginLocations collected plug-in locations to be filtered
     * @throws ManifestProcessingException 
     */
    protected Collection<PluginLocation> filterPluginLocations(final PluginRegistry registry,
            final Collection<PluginLocation> pluginLocations)
            throws ManifestProcessingException {
        if ((whiteList == null) && (blackList == null)) {
            return pluginLocations;
        }
        final List<PluginLocation> result = new LinkedList<PluginLocation>();
        for (PluginLocation pluginLocation : pluginLocations) {
            ManifestInfo manifestInfo = registry.readManifestInfo(
                    pluginLocation.getManifestLocation());
            if (whiteList != null) {
                if (isPluginInList(registry, manifestInfo, whiteList)) {
                    result.add(pluginLocation);
                }
                continue;
            }
            // blackList is not NULL here
            if (isPluginInList(registry, manifestInfo, blackList)) {
                continue;
            }
            result.add(pluginLocation);
        }
        return result;
    }
    
    private boolean isPluginInList(final PluginRegistry registry,
            final ManifestInfo manifestInfo, final Set<String> list) {
        if (list.contains(manifestInfo.getId())) {
            return true;
        }
        return list.contains(registry.makeUniqueId(manifestInfo.getId(),
                manifestInfo.getVersion()));
    }
}

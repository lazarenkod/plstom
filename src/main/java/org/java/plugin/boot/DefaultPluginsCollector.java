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

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import javax.xml.parsers.SAXParserFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.java.plugin.PluginManager.PluginLocation;
import org.java.plugin.standard.StandardPluginLocation;
import org.java.plugin.util.ExtendedProperties;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Default implementation of plug-ins collector interface. Supported
 * configuration parameters are:
 * <dl>
 *   <dt>org.java.plugin.boot.pluginsRepositories</dt>
 *   <dd>Comma separated list of local plug-in repositories, given folders will
 *     be scanned for plug-ins. Default value is <code>./plugins</code>.</dd>
 *   <dt>org.java.plugin.boot.pluginsLocationsDescriptors</dt>
 *   <dd>Comma separated list of URLs for XML syntax files that describe
 *     available plug-in locations (see file syntax bellow). No default value
 *     provided.</dd>
 * </dl>
 * <p>
 * Given repositories are scanned recursively collecting all folders that
 * contain <code>plugin.xml</code> or <code>plugin-fragment.xml</code> and
 * <code>*.zip</code> and <code>*.jar</code> files.
 * </p>
 * <p>
 * Plug-ins locations descriptor is a simple XML syntax file that stores
 * locations of all available plug-in manifests and contexts (in terms of
 * {@link org.java.plugin.PluginManager.PluginLocation}). Here is an example:
 * <pre>&lt;plugins&gt;
 *   &lt;plugin
 *     manifest="http://localhost/myPlugins/plugin1/plugin.xml"
 *     context="http://localhost/myPlugins/plugin1/"/&gt;
 *   &lt;plugin
 *     manifest="http://localhost/myPlugins/plugin2/plugin.xml"
 *     context="http://localhost/myPlugins/plugin2/"/&gt;
 *   &lt;plugin
 *     manifest="http://www.plugins.com/repository/plugin1/plugin.xml"
 *     context="http://www.plugins.com/repository/plugin1/"/&gt;
 *   &lt;plugin
 *     manifest="http://www.plugins.com/repository/plugin1/plugin.xml"
 *     context="http://www.plugins.com/repository/plugin1/"/&gt;
 * &lt;/plugins&gt;</pre>
 * Using such simple descriptor you may, for example, publish plug-ins on a site
 * to make them available for clients without needing to download plug-ins
 * manually.
 * </p>
 * @version $Id$
 */
public class DefaultPluginsCollector implements PluginsCollector {
    protected static final String PARAM_PLUGINS_REPOSITORIES =
        "org.java.plugin.boot.pluginsRepositories"; //$NON-NLS-1$
    protected static final String PARAM_PLUGINS_LOCATIONS_DESCRIPTORS =
        "org.java.plugin.boot.pluginsLocationsDescriptors"; //$NON-NLS-1$

    protected Log log = LogFactory.getLog(this.getClass());
    private List<File> repositories;
    private List<URL> descriptors;

    /**
     * @see org.java.plugin.boot.PluginsCollector#configure(
     *      org.java.plugin.util.ExtendedProperties)
     */
    public void configure(ExtendedProperties config) throws Exception {
        repositories = new LinkedList<File>();
        for (StringTokenizer st = new StringTokenizer(
                config.getProperty(PARAM_PLUGINS_REPOSITORIES,
                        '.' + File.separator + "plugins"), //$NON-NLS-1$
                        ",", false); st.hasMoreTokens();) { //$NON-NLS-1$
            String token = st.nextToken().trim();
            if (token.length() == 0) {
                continue;
            }
            repositories.add(new File(token).getCanonicalFile());
        }
        log.debug("found " + repositories.size() //$NON-NLS-1$
                + " local plug-ins repositories"); //$NON-NLS-1$
        descriptors = new LinkedList<URL>();
        for (StringTokenizer st = new StringTokenizer(
                config.getProperty(PARAM_PLUGINS_LOCATIONS_DESCRIPTORS, ""), //$NON-NLS-1$
                ",", false); st.hasMoreTokens();) { //$NON-NLS-1$
            String token = st.nextToken().trim();
            if (token.length() == 0) {
                continue;
            }
            descriptors.add(new URL(token));
        }
        log.debug("found " + descriptors.size() //$NON-NLS-1$
                + " plug-ins locations descriptors"); //$NON-NLS-1$
    }

    /**
     * @see org.java.plugin.boot.PluginsCollector#collectPluginLocations()
     */
    public Collection<PluginLocation> collectPluginLocations() {
        List<PluginLocation> result = new LinkedList<PluginLocation>();
        for (File file : repositories) {
            if (file.isDirectory()) {
                processFolder(file, result);
            } else if (file.isFile()) {
                processFile(file, result);
            } else {
                log.warn("unknown repository location " + file); //$NON-NLS-1$
            }
        }
        for (URL url : descriptors) {
            processDescriptor(url, result);
        }
        return result;
    }

    protected void processFolder(final File folder, final List<PluginLocation> result) {
        log.debug("processing folder - " + folder); //$NON-NLS-1$
        try {
            PluginLocation pluginLocation =
                StandardPluginLocation.create(folder);
            if (pluginLocation != null) {
                result.add(pluginLocation);
                return;
            }
        } catch (MalformedURLException mue) {
            log.warn("failed collecting plug-in folder " + folder //$NON-NLS-1$
                    + ", ignoring it", mue); //$NON-NLS-1$
            return;
        }
        File[] files = folder.listFiles();
        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            if (file.isDirectory()) {
                processFolder(file, result);
            } else if (file.isFile()) {
                processFile(file, result);
            }
        }
    }
    
    protected void processFile(final File file, final List<PluginLocation> result) {
        log.debug("processing file - " + file); //$NON-NLS-1$
        try {
            PluginLocation pluginLocation = StandardPluginLocation.create(file);
            if (pluginLocation != null) {
                result.add(pluginLocation);
            }
        } catch (MalformedURLException mue) {
            log.warn("failed collecting plug-in file " + file //$NON-NLS-1$
                    + ", ignoring it", mue); //$NON-NLS-1$
        }
    }
    
    private void processDescriptor(final URL url, final List<PluginLocation> result) {
        log.debug("processing plug-ins locations descriptor, URL=" + url); //$NON-NLS-1$
        try {
            SAXParserFactory.newInstance().newSAXParser().parse(
                    url.toExternalForm(),
                    new LocationsDescriptorHandler(result));
        } catch (Exception e) {
            log.warn("failed processing plug-ins locations descriptor, URL=" //$NON-NLS-1$
                    + url, e);
        }
    }
    
    private final class LocationsDescriptorHandler extends DefaultHandler {
        private final List<PluginLocation> resultData;

        LocationsDescriptorHandler(final List<PluginLocation> result) {
            resultData = result;
        }
        
        /**
         * @see org.xml.sax.helpers.DefaultHandler#startElement(
         *      java.lang.String, java.lang.String, java.lang.String,
         *      org.xml.sax.Attributes)
         */
        @Override
        public void startElement(final String uri, final String localName,
                final String qName, final Attributes attributes) {
            if (!"plugin".equals(qName)) { //$NON-NLS-1$
                return;
            }
            String manifest = attributes.getValue("manifest"); //$NON-NLS-1$
            if (manifest == null) {
                log.warn("manifest attribute missing"); //$NON-NLS-1$
                return;
            }
            URL manifestUrl;
            try {
                manifestUrl = new URL(manifest);
            } catch (MalformedURLException mue) {
                log.warn("invalid manifest URL - " + manifest, mue); //$NON-NLS-1$
                return;
            }
            String context = attributes.getValue("context"); //$NON-NLS-1$
            if (context == null) {
                log.warn("context attribute missing"); //$NON-NLS-1$
                return;
            }
            URL contextUrl;
            try {
                contextUrl = new URL(context);
            } catch (MalformedURLException mue) {
                log.warn("invalid context URL - " + context, mue); //$NON-NLS-1$
                return;
            }
            resultData.add(new StandardPluginLocation(contextUrl, manifestUrl));
            log.debug("got plug-in location from descriptor, manifestUrl=" //$NON-NLS-1$
                    + manifestUrl + ", contextURL=" + contextUrl); //$NON-NLS-1$
        }
    }
}

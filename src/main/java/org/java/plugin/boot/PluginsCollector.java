/*****************************************************************************
 * Java Plug-in Framework (JPF)
 * Copyright (C) 2004-2005 Dmitry Olshansky
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

import java.util.Collection;

import org.java.plugin.PluginManager.PluginLocation;
import org.java.plugin.util.ExtendedProperties;

/**
 * Interface to encapsulate logic for gathering information about available
 * plug-ins locations.
 * 
 * @version $Id$
 */
public interface PluginsCollector {
    /**
     * Configures this collector instance, this method will be called once
     * before any other method call in this class. There is no pre-defined
     * configuration parameters, see concrete implementations for supported
     * parameters.
     * @param configuration application configuration data from
     *                      <code>boot.properties</code> file and
     *                      <code>System</code> properties as defaults
     * @throws Exception if any error has occurred during collector configuring
     */
    void configure(ExtendedProperties configuration) throws Exception;
    
    /**
     * @return collection of all discovered
     * {@link org.java.plugin.PluginManager.PluginLocation plug-in locations}
     */
    Collection<PluginLocation> collectPluginLocations();
}

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

import org.java.plugin.util.ExtendedProperties;

/**
 * Interface to plug custom code into JPF based application boot procedure. The
 * implementation should contain logic on configuring and initializing (but not
 * starting) application.
 * 
 * @version $Id$
 */
public interface ApplicationInitializer {
    /**
     * Configures this initializer instance, this method will be called once
     * before any other method call in this class. There is no pre-defined
     * configuration parameters, see concrete implementations for supported
     * parameters.
     * @param config application configuration data from
     *               <code>boot.properties</code> file and <code>System</code>
     *               properties as defaults
     * @throws Exception if any error has occurred during initializer
     *                   configuring
     */
    void configure(ExtendedProperties config) throws Exception;
    
    /**
     * This method should configure and initialize an application instance to be
     * started.
     * @param errorHandler callback interface to report errors to the user,
     *                     it is recommended to use this handler only for
     *                     "non-fatal" errors and ask user via
     *                     {@link BootErrorHandler#handleError(String, Exception)}
     *                     or {@link BootErrorHandler#handleError(String, org.java.plugin.registry.IntegrityCheckReport)}
     *                     if he wants to abort application boot process
     * @param args command line arguments as they passed to program
     *             <code>main</code> method
     * @return initialized application instance or <code>null</code> if
     *         initializing failed
     * @throws Exception if any error has occurred during application
     *                   initializing
     */
    Application initApplication(BootErrorHandler errorHandler, String[] args)
            throws Exception;
}

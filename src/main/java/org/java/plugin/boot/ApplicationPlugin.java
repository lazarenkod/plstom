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

import org.java.plugin.Plugin;
import org.java.plugin.util.ExtendedProperties;

/**
 * This class is for "application" plug-ins - a JPF based program entry point.
 * The class is part of "standard boot scenario" when
 * {@link org.java.plugin.boot.DefaultApplicationInitializer} is used for
 * application initializing.
 * 
 * @version $Id$
 */
public abstract class ApplicationPlugin extends Plugin {
    /**
     * This method should instantiate and configure application instance that
     * will then be started.
     * @param config application configuration data, see
     *               {@link DefaultApplicationInitializer} for description on
     *               how plug-in configuration data composed from
     *               <code>boot.properties</code>
     * @param args command line arguments as they passed to program
     *             <code>main</code> method
     * @return initialized ready to start application instance
     * @throws Exception if any error has occurred during application
     *                   initializing
     */
    protected abstract Application initApplication(ExtendedProperties config,
            String[] args) throws Exception;
}

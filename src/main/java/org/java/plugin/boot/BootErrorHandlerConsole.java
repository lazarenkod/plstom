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

import org.java.plugin.registry.IntegrityCheckReport;

/**
 * Console out based error handler implementation, most suites good for
 * non-interactive service-style applications.
 * 
 * @version $Id$
 */
public class BootErrorHandlerConsole implements BootErrorHandler {
    /**
     * Prints given message to the "standard" error output.
     * @see org.java.plugin.boot.BootErrorHandler#handleFatalError(
     *      java.lang.String)
     */
    public void handleFatalError(final String message) {
        System.err.println(message);
    }

    /**
     * Prints given message and error stack trace to the "standard" error
     * output.
     * 
     * @see org.java.plugin.boot.BootErrorHandler#handleFatalError(
     *      java.lang.String, java.lang.Throwable)
     */
    public void handleFatalError(final String message, final Throwable t) {
        System.err.println(message);
        t.printStackTrace();
    }

    /**
     * Does the same as {@link #handleFatalError(String, Throwable)} always
     * returns <code>false</code>.
     * 
     * @see org.java.plugin.boot.BootErrorHandler#handleError(java.lang.String,
     *      java.lang.Exception)
     */
    public boolean handleError(final String message, final Exception e) {
        handleFatalError(message, e);
        return false;
    }

    /**
     * Does the same as {@link #handleFatalError(String)} always returns
     * <code>false</code>.
     * 
     * @see org.java.plugin.boot.BootErrorHandler#handleError(java.lang.String,
     *      org.java.plugin.registry.IntegrityCheckReport)
     */
    public boolean handleError(final String message,
            final IntegrityCheckReport report) {
        System.err.println(message);
        return false;
    }
}

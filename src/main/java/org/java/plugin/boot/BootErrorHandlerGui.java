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

import java.util.LinkedList;
import java.util.List;

import org.java.plugin.registry.IntegrityCheckReport;
import org.java.plugin.util.ResourceManager;

/**
 * Standard boot error handler for GUI applications. Uses Swing based dialogue
 * to show error details to user and prompt for input.
 * 
 * @version $Id$
 */
public class BootErrorHandlerGui implements BootErrorHandler {
    /**
     * @see org.java.plugin.boot.BootErrorHandler#handleFatalError(
     *      java.lang.String)
     */
    public void handleFatalError(String message) {
        ErrorDialog.showError(null,
                ResourceManager.getMessage(Boot.PACKAGE_NAME,
                "errorDialogueHeaderFatal"), message); //$NON-NLS-1$
    }

    /**
     * @see org.java.plugin.boot.BootErrorHandler#handleFatalError(
     *      java.lang.String, java.lang.Throwable)
     */
    public void handleFatalError(String message, Throwable t) {
        ErrorDialog.showError(null,
                ResourceManager.getMessage(Boot.PACKAGE_NAME,
                "errorDialogueHeaderFatal"), message, t); //$NON-NLS-1$
    }

    /**
     * @see org.java.plugin.boot.BootErrorHandler#handleError(java.lang.String,
     *      java.lang.Exception)
     */
    public boolean handleError(String message, Exception e) {
        return ErrorDialog.showWarning(null, ResourceManager.getMessage(
                Boot.PACKAGE_NAME, "errorDialogueHeaderNonFatal"), message, e); //$NON-NLS-1$
    }

    /**
     * @see org.java.plugin.boot.BootErrorHandler#handleError(java.lang.String,
     *      org.java.plugin.registry.IntegrityCheckReport)
     */
    public boolean handleError(String message, IntegrityCheckReport report) {
        List<String> items = new LinkedList<String>();
        for (IntegrityCheckReport.ReportItem item : report.getItems()) {
            if (item.getSeverity() != IntegrityCheckReport.Severity.ERROR) {
                continue;
            }
            items.add(item.getMessage());
        }
        //items.add("see log file " + Boot.BOOT_ERROR_FILE_NAME + " for details");
        return ErrorDialog.showWarning(null, ResourceManager.getMessage(
                Boot.PACKAGE_NAME, "errorDialogueHeaderNonFatal"), //$NON-NLS-1$
                message, items);
    }
}

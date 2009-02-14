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

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @version $Id$
 */
final class ControlThread extends Thread {
    static boolean isApplicationRunning(final InetAddress host,
            final int port) {
        try {
            Socket socket = new Socket(host, port);
            try {
                socket.setKeepAlive(true);
                String test = "" + System.currentTimeMillis(); //$NON-NLS-1$
                OutputStream out = socket.getOutputStream();
                InputStream in = null;
                try {
                    System.out.println("found running control service on " //$NON-NLS-1$
                            + host + ":" + port); //$NON-NLS-1$
                    out.write(("PING " + test).getBytes()); //$NON-NLS-1$
                    out.flush();
                    socket.shutdownOutput();
                    in = socket.getInputStream();
                    StringBuilder commandResult = new StringBuilder();
                    byte[] buf = new byte[16];
                    int len;
                    while ((len = in.read(buf)) != -1) {
                        commandResult.append(new String(buf, 0, len));
                    }
                    socket.shutdownInput();
                    if (commandResult.toString().startsWith("OK") //$NON-NLS-1$
                            && (commandResult.toString().indexOf(test) != -1)) {
                        System.out.println("PING command succeed"); //$NON-NLS-1$
                        return true;
                    }
                    System.out.println("PING command failed"); //$NON-NLS-1$
                } finally {
                    try {
                        out.close();
                    } catch (IOException ioe) {
                        // ignore
                    }
                    if (in != null) {
                        try {
                            in.close();
                        } catch (IOException ioe) {
                            // ignore
                        }
                    }
                }
            } finally {
                socket.close();
            }
        } catch (IOException ioe) {
            System.out.println(
                    "seems that there is no control service running on " //$NON-NLS-1$
                    + host + ":" + port); //$NON-NLS-1$
            //ioe.printStackTrace();
        }
        return false;
    }
    
    static boolean stopRunningApplication(final InetAddress host,
            final int port) {
        boolean result = false;
        try {
            Socket socket = new Socket(host, port);
            try {
                socket.setKeepAlive(true);
                OutputStream out = socket.getOutputStream();
                InputStream in = null;
                try {
                    System.out.println("found running control service on " //$NON-NLS-1$
                            + host + ":" + port); //$NON-NLS-1$
                    out.write("STOP".getBytes()); //$NON-NLS-1$
                    out.flush();
                    socket.shutdownOutput();
                    in = socket.getInputStream();
                    StringBuilder commandResult = new StringBuilder();
                    byte[] buf = new byte[16];
                    int len;
                    while ((len = in.read(buf)) != -1) {
                        commandResult.append(new String(buf, 0, len));
                    }
                    socket.shutdownInput();
                    if (commandResult.toString().startsWith("OK")) { //$NON-NLS-1$
                        System.out.println("STOP command succeed"); //$NON-NLS-1$
                        result = true;
                    } else {
                        System.out.println("STOP command failed"); //$NON-NLS-1$
                    }
                } finally {
                    try {
                        out.close();
                    } catch (IOException ioe) {
                        // ignore
                    }
                    if (in != null) {
                        try {
                            in.close();
                        } catch (IOException ioe) {
                            // ignore
                        }
                    }
                }
            } finally {
                socket.close();
            }
        } catch (IOException ioe) {
            System.out.println(
                    "seems that there is no control service running on " //$NON-NLS-1$
                    + host + ":" + port); //$NON-NLS-1$
            //ioe.printStackTrace();
        }
        if (result) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ie) {
                // ignore
            }
        }
        return result;
    }

    private Log log;

    private ServerSocket serverSocket;
    private final ServiceApplication application;
    private boolean appRunning;
    
    ControlThread(final InetAddress host, final int port,
            final ServiceApplication server) throws Exception {
        log = LogFactory.getLog(this.getClass());
        application = server;
        serverSocket = new ServerSocket(port, 1, host);
        appRunning = true;
        setName("jpf-application-control-thread"); //$NON-NLS-1$
    }
    
    /**
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        try {
            while (true) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    try {
                        if (handleRequest(clientSocket)) {
                            break;
                        }
                    } finally {
                        try {
                            clientSocket.close();
                        } catch (IOException ioe) {
                            // ignore
                        }
                    }
                } catch (Exception e) {
                    warn("error on server socket", e); //$NON-NLS-1$
                    break;
                }
            }
        } catch (Exception e) {
            error(e);
        } finally {
            try {
                serverSocket.close();
            } catch (IOException ioe) {
                warn("error closing server socket", ioe); //$NON-NLS-1$
            }
            if (appRunning) {
                stopApplication();
            }
        }
    }
    
    private synchronized boolean handleRequest(final Socket clientSocket) {
        debug("handling control request"); //$NON-NLS-1$
        if (!isValidRemoteHost(clientSocket.getInetAddress())) {
            warn("incoming connection to control socket registered" //$NON-NLS-1$
                    + " from REMOTE address " + clientSocket.getInetAddress() //$NON-NLS-1$
                    + ", attempt to execute command was IGNORED"); //$NON-NLS-1$
            try {
                clientSocket.close();
            } catch (IOException e) {
                // ignore
            }
            return false;
        }
        debug("processing control request"); //$NON-NLS-1$
        boolean result = false;
        try {
            String commandResult;
            InputStream in = clientSocket.getInputStream();
            OutputStream out = null;
            try {
                StringBuilder command = new StringBuilder();
                byte[] buf = new byte[16];
                int len;
                while ((len = in.read(buf)) != -1) {
                    command.append(new String(buf, 0, len));
                }
                clientSocket.shutdownInput();
                debug("got command - " + command); //$NON-NLS-1$
                if ("STOP".equals(command.toString())) { //$NON-NLS-1$
                    stopApplication();
                    result = true;
                    commandResult = "OK: stop done"; //$NON-NLS-1$
                } else if (command.toString().startsWith("PING")) { //$NON-NLS-1$
                    commandResult = "OK: " //$NON-NLS-1$
                        + command.substring("PING".length()); //$NON-NLS-1$
                } else {
                    commandResult = "ERROR: unknown command"; //$NON-NLS-1$
                }
                //debug("command executed");
                //debug("sending command result - " + commandResult);
                out = clientSocket.getOutputStream();
                out.write(commandResult.getBytes());
                out.flush();
                clientSocket.shutdownOutput();
                //debug("command result sent");
            } finally {
                try {
                    in.close();
                } catch (IOException ioe) {
                    // ignore
                }
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException ioe) {
                        // ignore
                    }
                }
            }
        } catch (IOException ioe) {
            error("error processing control request", ioe); //$NON-NLS-1$
        }
        return result;
    }
    
    private void stopApplication() {
        if (!appRunning) {
            debug("application not running"); //$NON-NLS-1$
            return;
        }
        appRunning = false;
        debug("stopping application"); //$NON-NLS-1$
        try {
            Boot.stopApplication(application);
            log = null;
        } catch (Exception e) {
            error("an error has occurred while stopping" //$NON-NLS-1$
                    + " application", e); //$NON-NLS-1$
        }
        debug("application stopped from control thread"); //$NON-NLS-1$
    }
    
    private boolean isValidRemoteHost(final InetAddress addr) {
        byte[] localAddr = serverSocket.getInetAddress().getAddress();
        byte[] remoteAddr = addr.getAddress();
        if (localAddr.length != remoteAddr.length) {
            return false;
        }
        for (int i = 0; i < remoteAddr.length; i++) {
            if (localAddr[i] != remoteAddr[i]) {
                return false;
            }
        }
        return true;
    }
    
    private void debug(final String msg) {
        if (log != null) {
            log.debug(msg);
        } else {
            System.out.println(msg);
        }
    }
    
    private void warn(final String msg) {
        if (log != null) {
            log.warn(msg);
        } else {
            System.out.println(msg);
        }
    }
    
    private void warn(final String msg, final Exception e) {
        if (log != null) {
            log.warn(msg, e);
        } else {
            System.out.println(msg);
            e.printStackTrace();
        }
    }
    
    private void error(final String msg, final Exception e) {
        if (log != null) {
            log.error(msg, e);
        } else {
            System.err.println(msg);
            e.printStackTrace();
        }
    }
    
    private void error(final Exception e) {
        if (log != null) {
            log.error(e);
        } else {
            e.printStackTrace();
        }
    }
}

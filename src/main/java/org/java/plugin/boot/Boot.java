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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

import org.apache.commons.logging.LogFactory;
import org.java.plugin.PluginManager;
import org.java.plugin.util.ExtendedProperties;
import org.java.plugin.util.IoUtil;
import org.java.plugin.util.ResourceManager;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Main class to get JPF based application running in different modes.
 * Application mode may be specified as <code>jpf.boot.mode</code> configuration
 * parameter or System property (via <code>-Djpf.boot.mode=</code> command line
 * argument). Supported values are:
 * <dl>
 *   <dt>start</dt>
 *   <dd>Runs application in "background" ("service") mode.</dd>
 *   <dt>stop</dt>
 *   <dd>Stops application, running in "background" mode.</dd>
 *   <dt>restart</dt>
 *   <dd>Restarts application, running in "background" mode. If it is not
 *     started, the action is the same as just starting application.</dd>
 *   <dt>shell</dt>
 *   <dd>Runs application in "shell" (or "interactive") mode. It is possible to
 *     control "service" style application from command line. Note, that
 *     already running application will be stopped first.</dd>
 *   <dt>load</dt>
 *   <dd>Only loads application but not starts it as in other modes. This mode
 *     is useful when doing application unit testing or when you only need to
 *     get initialized and ready to be started JPF environment.</dd>
 * </dl>
 * The "shell" mode is default. Application will be started in this mode if no
 * <code>jpf.boot.mode</code> configuration parameter can be found.
 * <p>
 * Application configuration is expected to be in Java properties format file.
 * File look-up procedure is the following:
 * </p>
 * <ul>
 *   <li>Check <code>jpf.boot.config</code> System property, if present, load
 *     configuration from that location</li>
 *   <li>Look for <code>boot.properties</code> file in the current folder.</li>
 *   <li>Look for <code>boot.properties</code> resource in classpath (using
 *     <code>Boot.class.getClassLoader().getResource("boot.properties")</code>
 *     and <code>Boot.class.getResource("boot.properties")</code> methods).</li>
 * </ul>
 * <p>
 * If configuration could not be found, a warning will be printed to console.
 * It is generally not an error to not use configuration file, you may provide
 * JPF configuration parameters as System properties. They are always used as
 * defaults for configuration properties.
 * </p>
 * <p>
 * Note that configuration properties will be loaded using
 * {@link org.java.plugin.util.ExtendedProperties specially extended}
 * version of {@link java.util.Properties} class, which supports parameters
 * substitution. If there is no <code>applicationRoot</code> property available
 * in the given configuration, the current folder will be published as default
 * value.
 * </p>
 * <p>
 * Standard configuration parameters are (all are optional when application is
 * running in "shell" mode):
 * <dl>
 *   <dt>jpf.boot.mode</dt>
 *   <dd>Application boot mode. Always available as System property also.
 *     Default value is <code>shell</code>.</dd>
 *   <dt>org.java.plugin.boot.appInitializer</dt>
 *   <dd>Application initializer class, for details see
 *   {@link org.java.plugin.boot.ApplicationInitializer}. Default is
 *   {@link org.java.plugin.boot.DefaultApplicationInitializer}.</dd>
 *   <dt>org.java.plugin.boot.errorHandler</dt>
 *   <dd>Error handler class, for details see
 *     {@link org.java.plugin.boot.BootErrorHandler}. Default is
 *     {@link org.java.plugin.boot.BootErrorHandlerConsole} for "service" style
 *     applications and {@link org.java.plugin.boot.BootErrorHandlerGui} for
 *     "interactive" applications.</dd>
 *   <dt>org.java.plugin.boot.controlHost</dt>
 *   <dd>Host to be used by background control service, no default values.</dd>
 *   <dt>org.java.plugin.boot.controlPort</dt>
 *   <dd>Port number to be used by background control service, no default
 *     values.</dd>
 *   <dt>org.java.plugin.boot.splashHandler</dt>
 *   <dd>Splash screen handler class, for details see
 *     {@link org.java.plugin.boot.SplashHandler}. Default is simple splash
 *     handler that can only display an image.</dd>
 *   <dt>org.java.plugin.boot.splashImage</dt>
 *   <dd>Path to an image file to be shown as splash screen. This may be any
 *       valid URL. If no file and no handler given, the splash screen will not
 *       be shown.</dd>
 *   <dt>org.java.plugin.boot.splashLeaveVisible</dt>
 *   <dd>If set to <code>true</code>, the Boot class will not hide splash screen
 *     at the end of boot procedure but delegate this function to application
 *     code. Default value is <code>false</code>.</dd>
 *   <dt>org.java.plugin.boot.splashDisposeOnHide</dt>
 *   <dd>If set to <code>false</code>, the Boot class will not dispose splash
 *     screen handler when hiding it. This allows you to reuse handler and show
 *     splash screen back after it was hidden. Default value is
 *     <code>true</code>.</dd>
 * </dl>
 *
 * @version $Id$
 */
public final class Boot {
    /**
     * Name of the file, where to put boot error details.
     */
    public static final String BOOT_ERROR_FILE_NAME = "jpf-boot-error.txt"; //$NON-NLS-1$
    
    /**
     * Boot configuration file location System property name.
     */
    public static final String PROP_BOOT_CONFIG = "jpf.boot.config"; //$NON-NLS-1$
    
    /**
     * Boot mode System property name.
     */
    public static final String PROP_BOOT_MODE = "jpf.boot.mode"; //$NON-NLS-1$
    
    /**
     * "shell" mode boot command value.
     */
    public static final String BOOT_MODE_SHELL = "shell"; //$NON-NLS-1$
    
    /**
     * "start" mode boot command value.
     */
    public static final String BOOT_MODE_START = "start"; //$NON-NLS-1$
    
    /**
     * "stop" mode boot command value.
     */
    public static final String BOOT_MODE_STOP = "stop"; //$NON-NLS-1$
    
    /**
     * "restart" mode boot command value.
     */
    public static final String BOOT_MODE_RESTART = "restart"; //$NON-NLS-1$
    
    /**
     * "load" mode boot command value.
     */
    public static final String BOOT_MODE_LOAD = "load"; //$NON-NLS-1$
    
    // This is for ResourceManager to look up resources.
    static final String PACKAGE_NAME = "org.java.plugin.boot"; //$NON-NLS-1$
    
    // Application bootstrap configuration parameter names goes here
    private static final String PARAM_CONTROL_HOST =
        "org.java.plugin.boot.controlHost"; //$NON-NLS-1$
    private static final String PARAM_CONTROL_PORT =
        "org.java.plugin.boot.controlPort"; //$NON-NLS-1$
    private static final String PARAM_ERROR_HANDLER =
        "org.java.plugin.boot.errorHandler"; //$NON-NLS-1$
    private static final String PARAM_APP_INITIALIZER =
        "org.java.plugin.boot.appInitializer"; //$NON-NLS-1$
    private static final String PARAM_SPLASH_HANDLER =
        "org.java.plugin.boot.splashHandler"; //$NON-NLS-1$
    private static final String PARAM_SPLASH_IMAGE =
        "org.java.plugin.boot.splashImage"; //$NON-NLS-1$
    private static final String PARAM_SPLASH_LEAVE_VISIBLE =
        "org.java.plugin.boot.splashLeaveVisible"; //$NON-NLS-1$
    private static final String PARAM_SPLASH_DISPOSE_ON_HIDE =
        "org.java.plugin.boot.splashDisposeOnHide"; //$NON-NLS-1$
    private static final String PARAM_SPLASH_CONFIG_PREFIX =
        "org.java.plugin.boot.splash."; //$NON-NLS-1$
    
    static SplashHandler splashHandler = null;

    /**
     * Call this method to start/stop application.
     * @param args command line arguments, not interpreted by this method but
     *             passed to
     *             {@link ApplicationPlugin#initApplication(ExtendedProperties, String[])}
     *             method
     */
    public static void main(final String[] args) {
        clearBootLog();
        // Load start-up configuration
        ExtendedProperties props = new ExtendedProperties(
                System.getProperties());
        try {
            InputStream strm = lookupConfig();
            try {
                props.load(strm);
            } finally {
                strm.close();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        String mode = props.getProperty(PROP_BOOT_MODE);
        if (mode != null) {
            mode = mode.trim().toLowerCase(Locale.ENGLISH);
        } else {
            // set SHELL mode by default
            mode = BOOT_MODE_SHELL;
        }
        props.setProperty(PROP_BOOT_MODE, mode);
        // Make sure that boot mode is always available as System property:
        System.setProperty(PROP_BOOT_MODE, mode);
        boolean useControlService = props.containsKey(PARAM_CONTROL_HOST)
            && props.containsKey(PARAM_CONTROL_PORT);
        BootErrorHandler errorHandler = getErrorHandlerInstance(
                props.getProperty(PARAM_ERROR_HANDLER), useControlService);
        try {
            if (props.getProperty("applicationRoot") == null) { //$NON-NLS-1$
                // Publish current folder as configuration parameter
                // to get it available as ${applicationRoot} variable
                // in extended properties syntax.
                String applicationRoot = new File(".").getCanonicalPath(); //$NON-NLS-1$
                props.put("applicationRoot", applicationRoot);  //$NON-NLS-1$
            }
            boot(props, useControlService, mode, errorHandler, args);
        } catch (Throwable t) {
            if (splashHandler != null) {
                splashHandler.setVisible(false);
                splashHandler = null;
            }
            bootLog(t);
            errorHandler.handleFatalError(ResourceManager.getMessage(
                    Boot.PACKAGE_NAME, "bootFailed"), t); //$NON-NLS-1$
            System.exit(1);
        }
    }
    
    /**
     * Boots application according to given configuration data.
     * @param config boot configuration data
     * @param useControlService if <code>true</code>, the control service will
     *                          started to allow handling application instance
     *                          from another process
     * @param mode application run mode
     * @param errorHandler boot errors handler instance
     * @param args command line arguments, not interpreted by this method but
     *             passed to
     *             {@link ApplicationPlugin#initApplication(ExtendedProperties, String[])}
     *             method
     * @return initialized application instance or <code>null</code>
     * @throws Exception if any un-handled error has occurred
     */
    public static Application boot(final ExtendedProperties config,
            final boolean useControlService, final String mode,
            final BootErrorHandler errorHandler, final String[] args)
            throws Exception {
        InetAddress controlHost = useControlService ? InetAddress.getByName(
                config.getProperty(PARAM_CONTROL_HOST)) : null;
        int controlPort = useControlService ? Integer.parseInt(
                config.getProperty(PARAM_CONTROL_PORT), 10) : 0;
        // handle given command
        if (useControlService && BOOT_MODE_STOP.equals(mode)) {
            if (!ControlThread.stopRunningApplication(controlHost,
                    controlPort)) {
                System.out.println("application not running"); //$NON-NLS-1$
            } else {
                System.out.println("application stopped"); //$NON-NLS-1$
            }
            return null;
        }
        if (useControlService && BOOT_MODE_START.equals(mode)) {
            if (ControlThread.isApplicationRunning(controlHost,
                    controlPort)) {
                errorHandler.handleFatalError(
                        "Application already running."); //$NON-NLS-1$
                return null;
            }
            Application application =
                initApplication(errorHandler, config, args);
            if (!(application instanceof ServiceApplication)) {
                errorHandler.handleFatalError(
                        "Application is not a service."); //$NON-NLS-1$
                return null;
            }
            ControlThread controlThread = new ControlThread(controlHost,
                    controlPort, (ServiceApplication) application);
            application.startApplication();
            controlThread.start();
            System.out.println(
                    "application started in BACKGROUND mode"); //$NON-NLS-1$
            return application;
        }
        if (useControlService && BOOT_MODE_RESTART.equals(mode)) {
            if (ControlThread.stopRunningApplication(controlHost,
                    controlPort)) {
                System.out.println("another instance of application stopped"); //$NON-NLS-1$
            }
            Application application =
                initApplication(errorHandler, config, args);
            if (!(application instanceof ServiceApplication)) {
                errorHandler.handleFatalError(
                        "Application is not a service."); //$NON-NLS-1$
                return null;
            }
            ControlThread controlThread = new ControlThread(controlHost,
                    controlPort, (ServiceApplication) application);
            application.startApplication();
            controlThread.start();
            System.out.println(
                    "application started in BACKGROUND mode"); //$NON-NLS-1$
            return application;
        }
        // SHELL or LOAD or an unknown modes
        if (useControlService
                && ControlThread.stopRunningApplication(controlHost,
                        controlPort)) {
            System.out.println("another instance of application stopped"); //$NON-NLS-1$
        }
        if (!BOOT_MODE_LOAD.equals(mode)) {
            initSplashHandler(config);
            if (splashHandler != null) {
                splashHandler.setVisible(true);
            }
        }
        Application application =
            initApplication(errorHandler, config, args);
        if (!BOOT_MODE_LOAD.equals(mode)) {
            application.startApplication();
            if ((splashHandler != null)
                    && !"true".equalsIgnoreCase(config.getProperty( //$NON-NLS-1$
                            PARAM_SPLASH_LEAVE_VISIBLE, "false"))) { //$NON-NLS-1$
                splashHandler.setVisible(false);
            }
            if ((application instanceof ServiceApplication)
                    && BOOT_MODE_SHELL.equals(mode)) {
                System.out.println("application started in SHELL mode"); //$NON-NLS-1$
                runShell();
                stopApplication(application);
            }
        }
        return application;
    }
    
    /**
     * Stops the application, shuts down plug-in manager and disposes log
     * service. Call this method before exiting interactive application. For
     * service applications this method will be called automatically by control
     * service or from shell.
     * @param application application instance being stopped
     * @throws Exception if any error has occurred during application stopping
     */
    public static void stopApplication(final Application application)
            throws Exception {
        if (application instanceof ServiceApplication) {
            ((ServiceApplication) application).stopApplication();
        }
        PluginManager pluginManager = PluginManager.lookup(application);
        if (pluginManager != null) {
            pluginManager.shutdown();
        }
        LogFactory.getLog(Boot.class).info("logging system finalized"); //$NON-NLS-1$
        LogFactory.getLog(Boot.class).info("---------------------------------"); //$NON-NLS-1$
        LogFactory.releaseAll();
    }
    
    /**
     * Returns current instance of splash screen handler if it is available or
     * <code>null</code>.
     * @return instance of splash handler or <code>null</code> if no active
     *         instance available
     */
    public static SplashHandler getSplashHandler() {
        return splashHandler;
    }
    
    /**
     * @param handler the new splash handler instance to set or
     *        <code>null</code> to dispose current handler directly
     */
    public static void setSplashHandler(final SplashHandler handler) {
        if ((handler == null) && (splashHandler != null)) {
            splashHandler.setVisible(false);
        }
        splashHandler = handler;
    }
    
    private static InputStream lookupConfig() throws IOException {
        String property = System.getProperty(PROP_BOOT_CONFIG);
        if (property != null) {
            return IoUtil.getResourceInputStream(str2url(property));
        }
        File file = new File("boot.properties"); //$NON-NLS-1$
        if (file.isFile()) {
            return new FileInputStream(file);
        }
        URL url = Boot.class.getClassLoader().getResource("boot.properties"); //$NON-NLS-1$
        if (url != null) {
            return IoUtil.getResourceInputStream(url);
        }
        url = Boot.class.getResource("boot.properties"); //$NON-NLS-1$
        if (url != null) {
            return IoUtil.getResourceInputStream(url);
        }
        throw new IOException("configuration file boot.properties not found"); //$NON-NLS-1$
    }
    
    private static URL str2url(final String str) throws MalformedURLException {
        int p = str.indexOf("!/"); //$NON-NLS-1$
        if (p == -1) {
            try {
                return new URL(str);
            } catch (MalformedURLException mue) {
                return IoUtil.file2url(new File(str));
            }
        }
        if (str.startsWith("jar:")) { //$NON-NLS-1$
            return new URL(str);
        }
        File file = new File(str.substring(0, p));
        if (file.isFile()) {
            return new URL("jar:" + IoUtil.file2url(file) + str.substring(p)); //$NON-NLS-1$
        }
        return new URL("jar:" + str); //$NON-NLS-1$
    }

    private static BootErrorHandler getErrorHandlerInstance(
            final String handler, final boolean isServiceApp) {
        if (handler != null) {
            try {
                return (BootErrorHandler) Class.forName(handler).newInstance();
            } catch (InstantiationException ie) {
                System.err.println("failed instantiating error handler " //$NON-NLS-1$
                        + handler);
                ie.printStackTrace();
            } catch (IllegalAccessException iae) {
                System.err.println("failed instantiating error handler " //$NON-NLS-1$
                        + handler);
                iae.printStackTrace();
            } catch (ClassNotFoundException cnfe) {
                System.err.println("failed instantiating error handler " //$NON-NLS-1$
                        + handler);
                cnfe.printStackTrace();
            }
        }
        return isServiceApp ? new BootErrorHandlerConsole()
                : (BootErrorHandler) new BootErrorHandlerGui();
    }

    private static void initSplashHandler(final ExtendedProperties config)
            throws Exception {
        String handlerClass = config.getProperty(PARAM_SPLASH_HANDLER);
        String splashImage = config.getProperty(PARAM_SPLASH_IMAGE);
        URL url = null;
        if ((splashImage != null) && (splashImage.length() > 0)) {
            try {
                url = new URL(splashImage);
            } catch (MalformedURLException mue) {
                // ignore
            }
            if (url == null) {
                File splashFile = new File(splashImage);
                if (splashFile.isFile()) {
                    url = IoUtil.file2url(splashFile);
                } else {
                    throw new FileNotFoundException("splash image file " //$NON-NLS-1$
                            + splashFile + " not found"); //$NON-NLS-1$
                }
            }
        }
        boolean disposeOnHide = !"false".equalsIgnoreCase( //$NON-NLS-1$
                config.getProperty(PARAM_SPLASH_DISPOSE_ON_HIDE, "true")); //$NON-NLS-1$
        if (handlerClass != null) {
            splashHandler = new SplashHandlerWrapper(disposeOnHide,
                    (SplashHandler) Class.forName(handlerClass).newInstance());
        }
        if ((splashHandler == null) && (url != null)) {
            splashHandler = new SplashHandlerWrapper(disposeOnHide,
                    new SimpleSplashHandler());
        }
        if (splashHandler != null) {
            if (url != null) {
                splashHandler.setImage(url);
            }
            splashHandler.configure(
                    config.getSubset(PARAM_SPLASH_CONFIG_PREFIX));
        }
    }
    
    private static Application initApplication(
            final BootErrorHandler errorHandler,
            final ExtendedProperties props, final String[] args)
            throws Exception {
        ApplicationInitializer appInitializer = null;
        String className = props.getProperty(PARAM_APP_INITIALIZER);
        if (className != null) {
            try {
                appInitializer = (ApplicationInitializer) Class.forName(
                        className).newInstance();
            } catch (InstantiationException ie) {
                System.err.println(
                        "failed instantiating application initializer " //$NON-NLS-1$
                        + className);
                ie.printStackTrace();
            } catch (IllegalAccessException iae) {
                System.err.println(
                        "failed instantiating application initializer " //$NON-NLS-1$
                        + className);
                iae.printStackTrace();
            } catch (ClassNotFoundException cnfe) {
                System.err.println(
                        "failed instantiating application initializer " //$NON-NLS-1$
                        + className);
                cnfe.printStackTrace();
            }
        }
        if (appInitializer == null) {
            appInitializer = new DefaultApplicationInitializer();
        }
        appInitializer.configure(props);
        Application result = appInitializer.initApplication(errorHandler, args);
        if (result == null) {
            throw new Exception(ResourceManager.getMessage(
                    Boot.PACKAGE_NAME, "bootAppInitFailed")); //$NON-NLS-1$
        }
        return result;
    }
    
    private static void runShell() {
        System.out.println("Press 'q' key to exit."); //$NON-NLS-1$
        do {
            int c;
            try {
                c = System.in.read();
            } catch (IOException ioe) {
                break;
            }
            if (('q' == (char) c) || ('Q' == (char) c)) {
                break;
            }
        } while (true);
    }
    
    private static void clearBootLog() {
        File file = new File(BOOT_ERROR_FILE_NAME);
        if (file.isFile()) {
            file.delete();
        }
    }
    
    private static void bootLog(final Throwable t) {
        try {
            Writer writer = new OutputStreamWriter(
                    new FileOutputStream(BOOT_ERROR_FILE_NAME, false),
                    "UTF-8"); //$NON-NLS-1$
            try {
                writer.write("JPF Application boot failed."); //$NON-NLS-1$
                writer.write(System.getProperty("line.separator")); //$NON-NLS-1$
                writer.write(ErrorDialog.getErrorDetails(t));
            } finally {
                writer.close();
            }
        } catch (Throwable t2) {
            throw new Error("boot failed", t); //$NON-NLS-1$
        }
    }
    
    private Boot() {
        // no-op
    }
}

final class SimpleSplashHandler implements SplashHandler {
    private float progress;
    private String text;
    private URL image;
    private boolean isVisible;

    /**
     * @see org.java.plugin.boot.SplashHandler#configure(
     *      org.java.plugin.util.ExtendedProperties)
     */
    public void configure(final ExtendedProperties config) {
        // no-op
    }
    
    /**
     * @see org.java.plugin.boot.SplashHandler#getProgress()
     */
    public float getProgress() {
        return progress;
    }

    /**
     * @see org.java.plugin.boot.SplashHandler#setProgress(float)
     */
    public void setProgress(final float value) {
        if ((value < 0) || (value > 1)) {
            throw new IllegalArgumentException(
                    "invalid progress value " + value); //$NON-NLS-1$
        }
        progress = value;
    }

    /**
     * @see org.java.plugin.boot.SplashHandler#getText()
     */
    public String getText() {
        return text;
    }

    /**
     * @see org.java.plugin.boot.SplashHandler#setText(java.lang.String)
     */
    public void setText(final String value) {
        text = value;
    }

    /**
     * @see org.java.plugin.boot.SplashHandler#getImage()
     */
    public URL getImage() {
        return image;
    }

    /**
     * @see org.java.plugin.boot.SplashHandler#setImage(java.net.URL)
     */
    public void setImage(final URL value) {
        image = value;
    }

    /**
     * @see org.java.plugin.boot.SplashHandler#isVisible()
     */
    public boolean isVisible() {
        return isVisible;
    }

    /**
     * @see org.java.plugin.boot.SplashHandler#setVisible(boolean)
     */
    public void setVisible(final boolean value) {
        if (isVisible == value) {
            return;
        }
        if (value) {
            SplashWindow.splash(image);
            isVisible = true;
            return;
        }
        SplashWindow.disposeSplash();
        isVisible = false;
    }

    /**
     * @see org.java.plugin.boot.SplashHandler#getImplementation()
     */
    public Object getImplementation() {
        return this;
    }
}

final class SplashHandlerWrapper implements SplashHandler {
    private final SplashHandler delegate;
    private final boolean isDisposeOnHide;

    SplashHandlerWrapper(final boolean disposeOnHide,
            final SplashHandler other) {
        isDisposeOnHide = disposeOnHide;
        delegate = other;
    }
    
    /**
     * @see org.java.plugin.boot.SplashHandler#configure(
     *      org.java.plugin.util.ExtendedProperties)
     */
    public void configure(final ExtendedProperties config) {
        delegate.configure(config);
    }

    /**
     * @see org.java.plugin.boot.SplashHandler#getProgress()
     */
    public float getProgress() {
        return delegate.getProgress();
    }

    /**
     * @see org.java.plugin.boot.SplashHandler#setProgress(float)
     */
    public void setProgress(float value) {
        delegate.setProgress(value);
    }

    /**
     * @see org.java.plugin.boot.SplashHandler#getText()
     */
    public String getText() {
        return delegate.getText();
    }

    /**
     * @see org.java.plugin.boot.SplashHandler#setText(java.lang.String)
     */
    public void setText(String value) {
        delegate.setText(value);
    }

    /**
     * @see org.java.plugin.boot.SplashHandler#getImage()
     */
    public URL getImage() {
        return delegate.getImage();
    }

    /**
     * @see org.java.plugin.boot.SplashHandler#setImage(java.net.URL)
     */
    public void setImage(URL value) {
        delegate.setImage(value);
    }

    /**
     * @see org.java.plugin.boot.SplashHandler#isVisible()
     */
    public boolean isVisible() {
        return delegate.isVisible();
    }

    /**
     * @see org.java.plugin.boot.SplashHandler#setVisible(boolean)
     */
    public void setVisible(boolean value) {
        delegate.setVisible(value);
        if (isDisposeOnHide && !delegate.isVisible()) {
            Boot.splashHandler = null;
        }
    }

    /**
     * @see org.java.plugin.boot.SplashHandler#getImplementation()
     */
    public Object getImplementation() {
        return delegate.getImplementation();
    }
    
}
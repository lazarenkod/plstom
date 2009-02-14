package com.pl.plugins.staff;

import com.pl.plugins.core.IPLPlugin;
import com.pl.plugins.staff.ui.views.impl.EmployerDictionaryView;
import org.java.plugin.Plugin;

import javax.swing.*;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 01.10.2008
 * Time: 13:29:21
 */
public class StaffPlPlugin extends Plugin implements IPLPlugin {

    public static final String PLUGIN_ID = "com.pl.plugins.staff";

    public StaffPlPlugin() {
    }

    /**
     * This method will be called once during plug-in activation before any
     * access to any code from this plug-in.
     *
     * @throws Exception if an error has occurred during plug-in start-up
     */
    protected void doStart() throws Exception {

    }

    /**
     * This method will be called once during plug-in deactivation. After
     * this method call, no other code from this plug-in can be accessed,
     * unless {@link #doStart()} method will be called again (but for another
     * instance of this class).
     *
     * @throws Exception if an error has occurred during plug-in shutdown
     */
    protected void doStop() throws Exception {

    }

    public void init(JComponent rootContainer) {
        EmployerDictionaryView employerDictionaryView =new EmployerDictionaryView();
        rootContainer.setLayout(new BorderLayout());
        rootContainer.add(employerDictionaryView, BorderLayout.CENTER);
        //rootContainer.a
    }
}

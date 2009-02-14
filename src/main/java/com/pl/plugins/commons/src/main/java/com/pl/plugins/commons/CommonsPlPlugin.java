package com.pl.plugins.commons;

import com.pl.plugins.commons.ui.views.impl.HumanView;
import com.pl.plugins.core.IPLPlugin;
import com.pl.plugins.core.CorePlugin;
import org.java.plugin.Plugin;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 30.09.2008
 * Time: 12:26:47
 */
public class CommonsPlPlugin extends Plugin implements IPLPlugin {

    public static final String PLUGIN_ID = "com.pl.plugins.commons";

    public CommonsPlPlugin() {

    }

    public void init(JComponent rootContainer) {
        HumanView hiw=new HumanView();
        rootContainer.setLayout(new BorderLayout());
        rootContainer.add(hiw, BorderLayout.CENTER);
    }

    /**
     * This method will be called once during plug-in activation before any
     * access to any code from this plug-in.
     *
     * @throws Exception if an error has occurred during plug-in start-up
     */
    @Override
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
    @Override
    protected void doStop() throws Exception {
    }
}

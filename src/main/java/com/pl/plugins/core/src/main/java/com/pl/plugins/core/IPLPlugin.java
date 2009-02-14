package com.pl.plugins.core;

import org.springframework.context.ApplicationContext;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 15.09.2008
 * Time: 10:44:24
 */
public interface IPLPlugin {

    void init(JComponent rootContainer);
}

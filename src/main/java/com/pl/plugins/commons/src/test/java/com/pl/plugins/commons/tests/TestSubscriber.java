package com.pl.plugins.commons.tests;

import com.pl.plugins.commons.ui.uinew.core.messages.ISubscriber;
import com.pl.plugins.commons.ui.uinew.core.messages.Message;

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 10.02.2009
 * Time: 18:29:59
 */
public class TestSubscriber implements ISubscriber {
    public void dataChanged(Message message) {
        System.out.println(""+ message);
    }
}

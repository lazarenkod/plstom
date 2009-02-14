package com.pl.plugins.commons.ui.uinew.domain.view.i18n;

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 13.02.2009
 * Time: 11:44:43
 */
public class NbBundle {
    public static String getMessage(Class clazz,String messageName){
        return new GBABundle(clazz).getMessage(messageName);
    }
}

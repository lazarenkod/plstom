package com.pl.plugins.commons.ui.uinew.domain.view.i18n;

import java.util.MissingResourceException;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 12.02.2009
 * Time: 17:56:26
 */
public interface IGBABundle extends Serializable {

    String BEAN_BUNDLE_LOCATION = "ru.otr.nbgba.domain.view.i18n.beans.";

    String DEFAULT_BUNDLE = "ru.otr.nbgba.domain.view.i18n.GlobalBundle";


    String getString(String key);


    String getString(String key, String defaultValue);


    String getMessage(String key) throws MissingResourceException;


    IGBABundle getParent();


    void setParent(IGBABundle parent);


    Object getObject(String key);
}

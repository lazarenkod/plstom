package com.pl.plugins.commons.ui.uinew.domain.view.i18n;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import com.pl.plugins.commons.ui.uinew.core.binding.AbstractBindingModelDTO;

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 12.02.2009
 * Time: 17:56:59
 */
public class GBABundle implements IGBABundle {

    private static final Log log = LogFactory.getLog(GBABundle.class);

    private ResourceBundle bundle;

    private IGBABundle parent;

    private String bundlePath;


    /**
     * Получение Bundle из аюсолютного пути к нему
     *
     * @param bundlePath путь у Bundle файлу
     */
    public GBABundle(final String bundlePath) {
        this.bundlePath = bundlePath;
        try {
            bundle = ResourceBundle.getBundle(this.bundlePath);
        } catch (MissingResourceException e) {
            //do nothing
            log.debug("Bundle '" + bundlePath + "' not found");
        }
    }


    /**
     * Получение Bundle через класс объекта. Также построение иерархии Bundle родительских классов
     *
     * @param beanClass класс объекта
     */
    public GBABundle(final Class<?> beanClass) {
        this(BEAN_BUNDLE_LOCATION + beanClass.getSimpleName());

        if (!beanClass.getSuperclass().equals(Object.class)) {
            //рекурсивно пробегаем по всем родителям класса, строя иерархию Bundle
            buildBundleHierarchy(beanClass.getSuperclass());
        }
    }

    /**
     * Задание готового Bundle.
     *
     * @param bundle Непустой ресурс бандл
     */
    public GBABundle(final ResourceBundle bundle) {
        this.bundle = bundle;
        this.bundlePath = bundle.getClass().getName();
    }

    /**
     * Построение иерархии Bundle
     * Если для какого-то класса в иерархии нет Bundle, то он опускается и за родительский Bundle берётся Bundle
     * вышестоящего класса. И так вплоть до класса Object
     *
     * @param clazz класс объекта
     * @return Bundle класса-родителя
     */
    private IGBABundle buildBundleHierarchy(final Class<?> clazz) {
        try {
            parent = new GBABundle(clazz);
        } catch (MissingResourceException e) {
            Class superClass = clazz.getSuperclass();
            if (superClass.equals(Object.class) || superClass.equals(AbstractBindingModelDTO.class)) {
                //TODO это заглушка. Убрать когда избавимся от ValidationMessages
//                parent = new GBABundle(ValidationMessages.VALIDATION_MESSAGES_BUNDLE);
            } else {
                parent = buildBundleHierarchy(clazz.getSuperclass());
            }
        }
        return parent;
    }


    public String getString(final String key) {
        return getMessage(key);
    }


    public String getString(final String key, final String defaultValue) {
        return getMessage(key);
    }


    public IGBABundle getParent() {
        return parent;
    }


    public void setParent(final IGBABundle parent) {
        this.parent = parent;
    }


    public String getMessage(final String key) {
        return String.valueOf(getObject(key));
    }


    public Object getObject(final String key) {
        if (bundle == null) {
            return "";
        }
        try {
            return bundle.getObject(key);
        } catch (MissingResourceException e) {
            if (parent != null) {
                return parent.getObject(key);
            }
        }
        throw new MissingResourceException("Can't find message for key '" + key + "'", bundlePath, key);
    }
}

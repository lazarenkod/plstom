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
     * ��������� Bundle �� ����������� ���� � ����
     *
     * @param bundlePath ���� � Bundle �����
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
     * ��������� Bundle ����� ����� �������. ����� ���������� �������� Bundle ������������ �������
     *
     * @param beanClass ����� �������
     */
    public GBABundle(final Class<?> beanClass) {
        this(BEAN_BUNDLE_LOCATION + beanClass.getSimpleName());

        if (!beanClass.getSuperclass().equals(Object.class)) {
            //���������� ��������� �� ���� ��������� ������, ����� �������� Bundle
            buildBundleHierarchy(beanClass.getSuperclass());
        }
    }

    /**
     * ������� �������� Bundle.
     *
     * @param bundle �������� ������ �����
     */
    public GBABundle(final ResourceBundle bundle) {
        this.bundle = bundle;
        this.bundlePath = bundle.getClass().getName();
    }

    /**
     * ���������� �������� Bundle
     * ���� ��� ������-�� ������ � �������� ��� Bundle, �� �� ���������� � �� ������������ Bundle ������ Bundle
     * ������������ ������. � ��� ������ �� ������ Object
     *
     * @param clazz ����� �������
     * @return Bundle ������-��������
     */
    private IGBABundle buildBundleHierarchy(final Class<?> clazz) {
        try {
            parent = new GBABundle(clazz);
        } catch (MissingResourceException e) {
            Class superClass = clazz.getSuperclass();
            if (superClass.equals(Object.class) || superClass.equals(AbstractBindingModelDTO.class)) {
                //TODO ��� ��������. ������ ����� ��������� �� ValidationMessages
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

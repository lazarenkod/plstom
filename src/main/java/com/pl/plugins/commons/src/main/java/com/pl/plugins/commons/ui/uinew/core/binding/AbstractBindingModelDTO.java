package com.pl.plugins.commons.ui.uinew.core.binding;

import com.jgoodies.binding.beans.Model;
import java.beans.PropertyChangeListener;
import java.beans.VetoableChangeListener;
import java.util.Date;
import org.apache.commons.beanutils.PropertyUtils;
import java.beans.PropertyDescriptor;

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 12.02.2009
 * Time: 18:14:08
 */

// TODO: write readObject() & writeObject()
public abstract class AbstractBindingModelDTO extends Model {
    private static final long serialVersionUID = 6631934147913082169L;
    public static final String DISPLAY_LABEL_CODE = "displayLabelCode";
    private String displayLabelCode = getClass().getName();

    public String getDisplayLabelCode() {
        return displayLabelCode;
    }

    public void setDisplayLabelCode(final String newCode) {
        if (displayLabelCode == null) {
            displayLabelCode = getClass().getName();
        }
        final Object old = displayLabelCode;
        displayLabelCode = newCode;
        firePropertyChange(DISPLAY_LABEL_CODE, old, newCode);
    }

    @SuppressWarnings({"DesignForExtension"})
    public void setPropertyChangeListeners(final PropertyChangeListener[] p) {

    }

    @SuppressWarnings({"DesignForExtension"})
    public void setVetoableChangeListeners(final VetoableChangeListener[] v) {

    }

    /**
     * Utility method to clone dates in models
     *
     * @param date date to clone
     * @return <code>null</code> if date is null, cloned date otherwise.
     */
    @SuppressWarnings({"TypeMayBeWeakened", "ReturnOfNull"})
    protected static Date cloneDate(final Date date) {
        if (date == null) {
            return null;
        } else {
            return (Date) date.clone();
        }
    }

    /**
     * »ницировать событи€ изменени€ по всем свойствам.
     */
    public void reFire() {
        super.fireMultiplePropertiesChanged();
        PropertyDescriptor[] descriptors = PropertyUtils.getPropertyDescriptors(this);
        for (PropertyDescriptor descriptor : descriptors) {
            try {
                if (descriptor.getReadMethod() == null || descriptor.getWriteMethod() == null) continue;
                super.firePropertyChange(descriptor.getName(), null, PropertyUtils.getProperty(this, descriptor.getName()));
            } catch (Exception ignored) {
            }
        }
    }
}
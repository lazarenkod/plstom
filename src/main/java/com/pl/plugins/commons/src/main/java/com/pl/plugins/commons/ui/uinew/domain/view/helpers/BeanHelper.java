package com.pl.plugins.commons.ui.uinew.domain.view.helpers;

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 12.02.2009
 * Time: 19:00:18
 */
import org.apache.commons.beanutils.BeanUtilsBean;


import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import com.pl.plugins.commons.ui.uinew.core.AbstractBindingModelDTO;

/**
 * Class, which collects several utility methods to use with beans.
 * Remember, that all properties are gotten with PropertyDescriptor
 *
 * @author nasokin.arseny
 * @see PropertyDescriptor
 * @see org.apache.commons.beanutils.BeanUtils
 */
@SuppressWarnings({"UnnecessaryFullyQualifiedName", "UtilityClass", "ClassWithoutLogger", "StaticMethodOnlyUsedInOneClass"})
public class BeanHelper {

    private BeanHelper() {
    }


    /**
     * returns display label code from the bean if this property is here.
     *
     * @param bean bean to get DisplayLabelCode for
     * @return display label code value or <code>null</code> if this property is defined or bean is not instance of the class AbstractBindingModelDTO
     * @throws NullPointerException if bean is null.
     */
    // TODO make this method more common.
    @SuppressWarnings({"AssignmentToNull"})
    public static String getDisplayLabelCode(final Object bean) {
        String value = null;
        if (bean instanceof AbstractBindingModelDTO) {
            value = ((AbstractBindingModelDTO) bean).getDisplayLabelCode();
            if (bean.getClass().getName().equals(value)) {
                value = null;
            }
        }
        return value;
    }


    /**
     * Gets the property from the bean.
     *
     * @param object   bean to get property from.
     * @param property what property to get.
     * @return property value.
     * @throws IntrospectionException    if an error occured.
     * @throws IllegalAccessException    if an error occured.
     * @throws InvocationTargetException if an error occured.
     */
    public static Object getPropertyValue(final Object object, final String property) throws IntrospectionException, IllegalAccessException, InvocationTargetException {
        final PropertyDescriptor descr = getPropertyDescriptor(property, object.getClass());
        final Method getterMethod = descr.getReadMethod();
        return getterMethod.invoke(object);
    }


    /**
     * Sets the property to the bean.
     *
     * @param object   bean to set property to.
     * @param property what property to set.
     * @param value    what value to set.
     * @return property value.
     * @throws IntrospectionException    if an error occured.
     * @throws IllegalAccessException    if an error occured.
     * @throws InvocationTargetException if an error occured.
     */

    public static Object setPropertyValue(final Object object, final String property, final Object value) throws IntrospectionException, IllegalAccessException, InvocationTargetException {
        final PropertyDescriptor descr = getPropertyDescriptor(property, object.getClass());
        final Method setterMethod = descr.getWriteMethod();
        return setterMethod.invoke(object, value);
    }


    /**
     * Gets the property from the bean.
     *
     * @param object   bean to get property from.
     * @param property what property to get.
     * @return property value.
     * @throws IllegalArgumentException if an error occured.
     */
    public static Object getPropertyValueI(final Object object, final String property) throws IllegalArgumentException {
        try {
            return getPropertyValue(object, property);
        } catch (IntrospectionException e) {
            throw new IllegalArgumentException(e);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException(e);
        } catch (InvocationTargetException e) {
            throw new IllegalArgumentException(e);
        }
    }


    /**
     * Gets the property from the bean.
     *
     * @param object       bean to get property from.
     * @param property     what property to get.
     * @param defaultValue what to return on error.
     * @return property value.
     */
    public static Object getPropertyValue(final Object object, final String property, final Object defaultValue) {
        try {
            return getPropertyValue(object, property);
        } catch (IntrospectionException e) {
            return defaultValue;
        } catch (IllegalAccessException e) {
            return defaultValue;
        } catch (InvocationTargetException e) {
            return defaultValue;
        }
    }


    /**
     * Gets PropertyDescriptor for given property
     *
     * @param property  property to get descriptor for
     * @param beanClazz class to get descriptor for
     * @return new PropertyDescripror
     * @throws IntrospectionException if an error occured
     */
    public static <B> PropertyDescriptor getPropertyDescriptor(final String property, final Class<B> beanClazz) throws IntrospectionException {
        return new PropertyDescriptor(property, beanClazz);
    }


    /**
     * Selects list of given property from collection of beans.
     * Sample usages:
     * <p/>
     * List&lt;String&gt; listOfIDs = selectFromBeans (BeanClassName.ID, BeanClass, ListOfBeans, String);
     *
     * @param property   which property is initerest in beans
     * @param beanClazz  class of one bean
     * @param beans      collection of beans to read beans from
     * @param fieldClass class for one field (needed for check)
     * @return selected property from collection of beans.
     * @throws java.beans.IntrospectionException
     *          if creating PropertyDescriptor fails
     */
    public static <T, B> List<T> selectFromBeans(final String property, final Class<B> beanClazz, final Collection<B> beans, final Class<T> fieldClass) throws IntrospectionException {
        return selectFromBeans(property, beanClazz, beans, fieldClass, false);
    }


    /**
     * Selects list of given property from collection of beans.
     *
     * @param property   which property is initerest in beans
     * @param beanClazz  class of one bean
     * @param beans      collection of beans to read beans from
     * @param fieldClass class for one field (needed for check)
     * @param selectNull if you want to select <code>null</code> values
     * @return selected property from collection of beans.
     * @throws java.beans.IntrospectionException
     *          if creating PropertyDescriptor fails
     */

    public static <T, B> List<T> selectFromBeans(final String property, final Class<B> beanClazz, final Collection<B> beans, final Class<T> fieldClass, final boolean selectNull) throws IntrospectionException {
        return selectFromBeans(getPropertyDescriptor(property, beanClazz), beans, selectNull, fieldClass);
    }


    /**
     * Selects list of given property from collection of beans.
     *
     * @param descriptor property descriptor for bean property.
     * @param beans      collection of beans to read beans from
     * @param selectNull if you want to select <code>null</code> values
     * @param fieldClass class for one field (needed for check)
     * @return selected property from collection of beans.
     */

    @SuppressWarnings({"unchecked", "TypeMayBeWeakened", "TooBroadScope", "ProhibitedExceptionThrown"})
    public static <T, B> List<T> selectFromBeans(final PropertyDescriptor descriptor, final Collection<B> beans, final boolean selectNull, final Class<T> fieldClass) {
        final List<T> result = new ArrayList<T>();
        final Method getter;

        if (descriptor == null || !descriptor.getPropertyType().equals(fieldClass)) {
            return result;
        }

        if (beans == null || beans.isEmpty()) {
            return result;
        }
        getter = descriptor.getReadMethod();

        for (final B object : beans) {
            if (object != null) {
                try {
                    final T propValue = (T) getter.invoke(object);
                    if (propValue != null || selectNull) {
                        result.add(propValue);
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return result;
    }


    /**
     * Filters list of beans with given filter.
     * <p/>
     * Sample use:
     * <p/>
     * Map&lt;String, Object&gt; filter = new HashMap&lt;String, Object&gt;();
     * filter.put("property1", "value1");
     * filter.put("property2", false);
     * filter.put("property3", getGoodValue());
     * List&lt;BeanClass&gt; filtered = filterBeans(beanList, BeanClass, filter, false);
     *
     * @param beans          bean list to filter
     * @param beanClass      class of one bean (needed to get property desctriptor)
     * @param filter         filter map to use (property -&gt; value)
     * @param skipNullValues if you want to skip null filter values from checking
     * @return filtered list of beans
     * @throws IntrospectionException if there are some problems to get property descriptor
     */
    public static <B> List<B> filterBeans(final List<B> beans, final Class<B> beanClass, final Map<String, Object> filter, final boolean skipNullValues) throws IntrospectionException {
        /*
         checks for filter
         */

        if (filter == null || filter.isEmpty()) {
            return beans;
        }

        /*
        checks for beans list
         */
        if (beans == null || beans.isEmpty()) {
            return Collections.emptyList();
        }

        final Map<PropertyDescriptor, Object> filterMap = new HashMap<PropertyDescriptor, Object>();
        for (final String property : filter.keySet()) {
            filterMap.put(getPropertyDescriptor(property, beanClass), filter.get(property));
        }
        return filterBeans(beans, filterMap, skipNullValues);
    }


    /**
     * Filters list of beans with given filter.
     *
     * @param beans          bean list to filter
     * @param filter         filter map to use (property -&gt; value)
     * @param skipNullValues if you want to skip null filter values from checking
     * @return filtered list of beans
     * @throws IllegalArgumentException if there are some problems to get read method from filter descriptors.
     */
    @SuppressWarnings({"ForLoopWithMissingComponent", "ProhibitedExceptionThrown", "MethodWithMultipleLoops"})
    public static <B> List<B> filterBeans(final List<B> beans, final Map<PropertyDescriptor, Object> filter, final boolean skipNullValues) {
        /*
         checks for filter
         */

        if (filter == null || filter.isEmpty()) {
            return beans;
        }

        /*
        checks for beans list
         */
        if (beans == null || beans.isEmpty()) {
            return Collections.emptyList();
        }
        final Map<Method, Object> readMethods = new HashMap<Method, Object>();
        for (final PropertyDescriptor descriptor : filter.keySet()) {
            if (descriptor == null || descriptor.getReadMethod() == null) {
                throw new IllegalArgumentException("Can't find read method");
            } else {
                readMethods.put(descriptor.getReadMethod(), filter.get(descriptor));
            }
        }

        /*
        result to return.
        */
        final List<B> result = new ArrayList<B>();
        for (final B bean : beans) {
            boolean accept = true;
            for (Iterator<Method> it = readMethods.keySet().iterator(); accept && it.hasNext();) {
                final Method method = it.next();
                try {
                    final Object value = method.invoke(bean);
                    final Object goodValue = readMethods.get(method);
                    if (goodValue != null) {
                        accept = goodValue.equals(value);
                    } else {
                        accept = skipNullValues || value == null;
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
            if (accept) {
                result.add(bean);
            }
        }
        return result;
    }


    /**
     * Получение списка значений по списку свойств
     *
     * @param bean          объект
     * @param propertyNames списко свойств объекта
     * @return список значений
     */
    public static Object[] getValuesByPropertyNames(Object bean, String[] propertyNames) {
        Object[] result = new Object[propertyNames.length];
        try {
            for (int i = 0; i < propertyNames.length; i++) {
                result[i] = getPropertyValue(bean, propertyNames[i]);
            }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * Конвертирование бина в Map<имя поля, значение>
     * @param bean объект
     * @return Map<имя поля, значение>
     */
    public static Map<String, Object> convertBeanToMap(Object bean) {
        if (bean == null) {
            return null;
        }
        try {
            BeanUtilsBean beanUtils = BeanUtilsBean.getInstance();
            PropertyDescriptor[] descriptors = beanUtils.getPropertyUtils().getPropertyDescriptors(bean);
            Map<String, Object> result = new HashMap<String, Object>();
            for (PropertyDescriptor descriptor : descriptors) {

                Object value = beanUtils.getProperty(bean, descriptor.getName());
                result.put(descriptor.getName(), value);
            }
            return result;

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }


}

package com.pl.plugins.commons.ui.uinew.domain.view.helpers;

import org.apache.commons.lang.StringUtils;


import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.pl.plugins.commons.ui.uinew.domain.view.validation.validators.annotations.NotEmpty;
import com.pl.plugins.commons.ui.uinew.domain.view.validation.validators.annotations.Length;


/**
 * @author nasokin.arseny
 */
public class ClassHelper {
    public static final String VALUE_FIELD = "value";

    public static final int METHOD_GETTER = 0;

    public static final int METHOD_SETTER = 1;

    private ClassHelper() {
    }

    public static String getDisplayLabelCodeOrName(final Object bean) {
        String result = BeanHelper.getDisplayLabelCode(bean);
        if (StringUtils.isEmpty(result)) {
            result = bean.getClass().getName();
        }
        return result;
    }

    /**
     * Finds field by name in the bean.
     * @param bean object where field should be found
     * @param fieldName field name to find.
     * @return field, if it has been found, null otherwise.
     */
    @SuppressWarnings({"UnusedCatchParameter", "ReturnOfNull"})
    public static Field getField(final Object bean, final String fieldName) {
        if (bean == null || StringUtils.isEmpty(fieldName)) {
            return null;
        }
        return getField(bean.getClass(), fieldName);
    }

    /**
     * Finds field by name in the bean class.
     * @param clazz bean class where field should be found
     * @param fieldName field name to find.
     * @return field, if it has been found, null otherwise.
     */
    @SuppressWarnings({"UnusedCatchParameter", "ReturnOfNull"})
    public static Field getField(Class clazz, final String fieldName) {
        while (clazz != null) {
            try {
                return clazz.getDeclaredField(fieldName);
            } catch (final NoSuchFieldException e) {
                clazz = clazz.getSuperclass();

            }
        }
        return null;
    }



    /**
     * Find getter for field
     *
     * @param clazz, class
     * @param fieldName, field name
     * @return "Get" method for field
     *
     * @throws NoSuchMethodException, if method not found
     */
    public static Method getGetterForField(Class clazz, String fieldName) throws NoSuchMethodException {
        String methodName = convertFieldNameToMethodName(fieldName, METHOD_GETTER);
        return findMethod(clazz, methodName);
    }

    /**
     * Find setter for field. This method doesn't apply to method params
     *
     * @param clazz, class
     * @param fieldName, field name
     * @return "Set" method for field
     *
     * @throws NoSuchMethodException, if method not found
     */
    public static Method getSetterForField(Class clazz, String fieldName) throws NoSuchMethodException {
        String methodName = convertFieldNameToMethodName(fieldName, METHOD_SETTER);
        return findMethod(clazz, methodName);
    }


    /**
     * Find method using method name only.
     *
     * @param clazz, class
     * @param methodName, method name
     * @return method
     * @throws NoSuchMethodException, if method was not found
     */
    public static Method findMethod(Class clazz, String methodName) throws NoSuchMethodException {
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if (method.getName().equalsIgnoreCase(methodName)) {
                return method;
            }
        }
        throw new NoSuchMethodException("method " + methodName +" not fould in " + clazz.getName());
    }


    /**
     * Convert field name to appropriate getter or setter method name
     *
     * @param fieldName, field name
     * @param methodType, METHOD_GETTER if need getter, or METHOD_SETTER, if need setter
     * @return method name
     */
    public static String convertFieldNameToMethodName(String fieldName, int methodType) {
        String firstLetter = String.valueOf(fieldName.charAt(0));

        StringBuffer result = new StringBuffer();
        if (methodType == METHOD_GETTER) {
            result.append("get");
        } else if (methodType == METHOD_SETTER) {
            result.append("set");
        }

        return result.append(firstLetter.toUpperCase()).
                append(fieldName.substring(1, fieldName.length())).toString();
    }

    /**
     * Получает из get (set) метода наименование поля
     * @param methodName Наименование метода
     * @return Наименование поля
     */
    public static String convertMethodNameToFiedlName(String methodName) {
        return StringUtils.uncapitalize(StringUtils.substring(methodName, 3));
    }


    @SuppressWarnings("unchecked")
    private static final Class<? extends Annotation>[] mandatoryAnnotations = new Class[]{
            NotEmpty.class,
            Length.class
    };

    /**
     * checks if field is mandatory
     * @param field field to check
     * @return checks is field mandatory?
     */
    public static boolean isMandatory(final AnnotatedElement field) {
        if (field == null) {
            throw new IllegalArgumentException("field must not be null");
        }
        for (final Class<? extends Annotation> clazz : mandatoryAnnotations) {
            if (field.isAnnotationPresent(clazz)) {
                return true;
            }
        }
        return false;
    }

    public static String getClassName(final Class<?> clazz) {
        if (clazz == null) {
            return null;
        } else {
            return clazz.getName();
        }
    }

    public static String getClassName (final Object object) {
        if (object == null) {
            return null;
        } else {
            return getClassName(object.getClass());
        }
    }

    /**
     * Filters collection of objects.
     * @param objects collection to filter.
     * @param filter what filter should be used.
     * @param checkNull if null objects should not be sliently ignored by filter.
     * @return filtered collection of the objects.
     */
    public static <E> List<E> filter(final Collection<E> objects, final IFilter<E> filter, final boolean checkNull) {
        final List<E> result = new ArrayList<E>();
        if (objects == null || objects.isEmpty() || filter == null) {
            return result;
        }

        for (final E object:objects) {
            if ((object != null || checkNull) && !filter.isFilterOut(object)) {
                result.add(object);
            }
        }
        return result;
    }


    /**
     * Filters collection of objects. Null objects will be sliently ignored by filter.
     * @param objects collection to filter.
     * @param filter what filter should be used.
     * @return filtered collection of the objects.
     */
    public static <E> List<E> filter(final Collection<E> objects, final IFilter<E> filter) {
        return filter(objects, filter, false);
    }


    public static interface IFilter<E>{
        boolean isFilterOut(E object);
    }
}

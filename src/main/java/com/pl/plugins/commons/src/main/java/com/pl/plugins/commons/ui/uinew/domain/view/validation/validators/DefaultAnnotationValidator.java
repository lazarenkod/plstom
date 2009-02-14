package com.pl.plugins.commons.ui.uinew.domain.view.validation.validators;

import java.util.Set;

import com.jgoodies.binding.beans.BeanUtils;
import com.jgoodies.validation.ValidationResult;
import com.pl.plugins.commons.ui.uinew.domain.view.i18n.IGBABundle;
import com.pl.plugins.commons.ui.uinew.domain.view.i18n.GBABundle;
import com.pl.plugins.commons.ui.uinew.domain.view.validation.validators.annotations.*;
import com.pl.plugins.commons.ui.uinew.domain.view.helpers.ClassHelper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author mukhanov.nikolay@otr.ru 15.01.2007  21:39:38
 *         ���������, ������� ���������� �� ����������
 */
public class DefaultAnnotationValidator extends AbstractValidator {

    private static final Log log = LogFactory.getLog(DefaultAnnotationValidator.class);

    /**
     * ����-����������
     */
    private Set<String> excludedFieldNames = new HashSet();

    private static Map<String, Class<? extends IAnnotationValidator>> ANNOTATIONS = new HashMap<String, Class<? extends IAnnotationValidator>>();

    /**
     * ��� �����������
     */
    private Map<String, IAnnotationValidator> used = new HashMap<String, IAnnotationValidator>();

    private ValidateKind validateKind;


    static {
        ANNOTATIONS.put(Length.class.getName(), LengthValidator.class);
//        ANNOTATIONS.put(DateField.class.getName(), DateValidator.class);
        ANNOTATIONS.put(NotEmpty.class.getName(), NotEmptyValidator.class);
//        ANNOTATIONS.put(Pattern.class.getName(), PatternValidator.class);
//        ANNOTATIONS.put(Email.class.getName(), EmailValidator.class);
//        ANNOTATIONS.put(IntegerField.class.getName(), IntegerFieldValidator.class);
//        ANNOTATIONS.put(DependOn.class.getName(), DependOnValidator.class);
//        ANNOTATIONS.put(Money.class.getName(), MoneyValidator.class);
    }


    /**
     * ������ �����������
     */
    public DefaultAnnotationValidator() {
        validateKind = ValidateKind.BY_FIELD_ANNOTATION;
    }


    /**
     * ������ �����������
     * TODO ��������. ���� ������� � ������������� ������
     */
    public DefaultAnnotationValidator(Object bean) {
        validateKind = ValidateKind.BY_FIELD_ANNOTATION;
    }


    /**
     * �����������
     *
     * @param beanBundle GBABundle, ������� ������ �������� ���� ����� ����
     */
    public DefaultAnnotationValidator(IGBABundle beanBundle) {
        super(beanBundle);
        validateKind = ValidateKind.BY_FIELD_ANNOTATION;
    }


    public DefaultAnnotationValidator(ValidateKind validateKind) {
        this.validateKind = validateKind;
    }


    public DefaultAnnotationValidator(Set<String> excludedFieldNames, ValidateKind validateKind) {
        this.excludedFieldNames = excludedFieldNames;
        this.validateKind = validateKind;
    }

    /**
     * �����������, �������� � �������� ���������� ��������� ������ �����-�����������
     *
     * @param excludedFieldNames ����, ������� ������ ���� ��������� �� ���������
     * @deprecated ������ ���� �������� ����� �� �� ������������. ���� ������� � ������������� ������
     */
    @Deprecated
    public DefaultAnnotationValidator(Object bean, Set<String> excludedFieldNames) {
        this.excludedFieldNames = excludedFieldNames;
    }


    /**
     * �����������, �������� � �������� ���������� ��������� ������ �����-�����������
     *
     * @param excludedFieldNames ����, ������� ������ ���� ��������� �� ���������
     */
    public DefaultAnnotationValidator(Set<String> excludedFieldNames) {
        validateKind = ValidateKind.BY_FIELD_ANNOTATION;
        this.excludedFieldNames = excludedFieldNames;
    }


    /**
     * ��������� GBABundle ��� ������� ����������
     *
     * @return
     */
    public IGBABundle getDefaultValidationBundle() {
        return new GBABundle(IValidator.DEFAULT_VALIDATION_BUNDLE_PATH + this.getClass().getSimpleName());
    }


    @SuppressWarnings({"ReturnOfNull"})
    public IAnnotationValidator getValidator(final String annotationClass) {
        IAnnotationValidator validator = used.get(annotationClass);

        if (validator != null) {
            return validator;
        }

        final Class<? extends IAnnotationValidator> c = ANNOTATIONS.get(annotationClass);

        if (c == null) {
            return null;
        }

        try {

            validator = c.newInstance();
            used.put(annotationClass, validator);
            return validator;
        } catch (InstantiationException exception) {
            log.error("cannot create validator for " + annotationClass, exception);
            return null;
        } catch (IllegalAccessException exception) {
            log.error("cannot create validator for " + annotationClass, exception);
            return null;
        }
    }


    /**
     * ���������
     *
     * @return ��������� ���������
     */
    public ValidationResult validate() {
        ValidationResult result = new ValidationResult();
        if (getBean() != null) {
            if (validateKind.equals(ValidateKind.BY_FIELD_ANNOTATION)) {
                validateByAnnotations(getBean(), result, excludedFieldNames != null ? excludedFieldNames : Collections.EMPTY_SET);
            } else if(validateKind.equals(ValidateKind.BY_METHOD_ANNOTATION)) {
                validateByMethodAnnotations(getBean(), result, excludedFieldNames != null ? excludedFieldNames : Collections.EMPTY_SET);
            } else {
                throw new RuntimeException("Unknown validation kind");
            }
        }
        return result;
    }


    /**
     * ��������� �� ����������
     *
     * @param bean              ���, ���� �������� ������������
     * @param validationResult  ��������� ���������
     * @param excludeFieldNames ����-����������
     *                          //TODO remove this method
     */
    protected static void validateAnnotatedFields(Object bean, ValidationResult validationResult, Set<String> excludeFieldNames) {
        throw new IllegalStateException("This method is deprecated");
    }


    /**
     * ��������� �� ����������
     *
     * @param bean              ���, ���� �������� ������������
     * @param validationResult  ��������� ���������
     * @param excludeFieldNames ����-����������
     */
    protected void validateByAnnotations(Object bean, ValidationResult validationResult, Set<String> excludeFieldNames) {
        try {
            Field[] fields = getAllFields(bean.getClass());
            for (Field fld : fields) {
                if (excludeFieldNames.contains(fld.getName())) {
                    continue;
                }
                Annotation[] annotations = fld.getAnnotations();
                for (Annotation annotation : annotations) {
                    doValidate(annotation, fld.getName(), bean, validationResult);
                }
            }
        } catch (IntrospectionException e) {
            throw new RuntimeException(e);
        }
    }


    protected void validateByMethodAnnotations(Object bean, ValidationResult validationResult, Set<String> excludeFieldNames) {
        try {
            Method[] methods = getAllMethods(bean.getClass());

            for (Method method : methods) {
                String fieldName = ClassHelper.convertMethodNameToFiedlName(method.getName());

                if (excludeFieldNames.contains(fieldName)) {
                    continue;
                }

                Annotation[] annotations = method.getAnnotations();
                for (Annotation annotation : annotations) {
                    doValidate(annotation, fieldName, bean, validationResult);
                }
            }
        } catch (IntrospectionException e) {
            throw new RuntimeException(e);
        }

    }


    private void doValidate(Annotation annotation, String fieldName, Object bean, ValidationResult validationResult) throws IntrospectionException {
        if (ANNOTATIONS.containsKey(annotation.annotationType().getName())) {
            IAnnotationValidator validator = getValidator(annotation.annotationType().getName());
            Object value = BeanUtils.getValue(bean, new PropertyDescriptor(fieldName, bean.getClass()));
            validator.setBeanBundle(this.getBeanBundle());
            validator.setValidatorBundle(this.getValidatorBundle());
            validator.validate(bean, fieldName, value, annotation, validationResult);
        }
    }


    private static Method[] getAllMethods(Class beanClazz) {
        List<Method> methods = new LinkedList<Method>();
        recursiveGetMethods(methods, beanClazz);
        return methods.toArray(new Method[methods.size()]);
    }


    private static void recursiveGetMethods(List<Method> methods, Class beanClazz) {
        if (beanClazz.getSuperclass() != null) {
            recursiveGetMethods(methods, beanClazz.getSuperclass());
        }
        CollectionUtils.addAll(methods, beanClazz.getDeclaredMethods());
    }


    /**
     * ��������� ���� ����� �������
     *
     * @param beanClazz ����� �������
     * @return ������ ����� �������
     */
    private static Field[] getAllFields(Class beanClazz) {
        List<Field> fields = new LinkedList<Field>();
        recursiveGetFields(fields, beanClazz);
        return fields.toArray(new Field[fields.size()]);
    }


    /**
     * ���������� ����� ���� � �������
     *
     * @param fields    ������ �����
     * @param beanClazz ����� �������
     */
    private static void recursiveGetFields(List<Field> fields, Class beanClazz) {
        if (beanClazz.getSuperclass() != null) {
            recursiveGetFields(fields, beanClazz.getSuperclass());
        }
        CollectionUtils.addAll(fields, beanClazz.getDeclaredFields());
    }


    public void setExcludedFields(Set<String> fieldNames) {
        this.excludedFieldNames = fieldNames;
    }

    public ValidationResult validate(Object o) {
        return null;           //todo ���������� ������ ������ ��������
    }

    /** ����������� ��� ������� �������� ��������� */
    public enum ValidateKind {
        /** ������������ ��������� ����� */
        BY_FIELD_ANNOTATION,
        /** ����������� ��������� ������� */
        BY_METHOD_ANNOTATION
    }
}

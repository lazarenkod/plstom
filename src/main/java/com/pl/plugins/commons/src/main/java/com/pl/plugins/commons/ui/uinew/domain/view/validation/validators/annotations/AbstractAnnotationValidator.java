package com.pl.plugins.commons.ui.uinew.domain.view.validation.validators.annotations;

import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.util.PropertyValidationSupport;
import com.pl.plugins.commons.ui.uinew.domain.view.i18n.IGBABundle;

import java.lang.annotation.Annotation;

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 12.02.2009
 * Time: 19:03:07
 */



public abstract class AbstractAnnotationValidator implements IAnnotationValidator {

    private PropertyValidationSupport support;

    private String fieldName;

    private IGBABundle beanBundle;

    private IGBABundle validatorBundle;

    protected static final String WUA = "Wrong use annotation ";


    protected AbstractAnnotationValidator() {
    }


    protected PropertyValidationSupport getPropertyValidationSupport(Object bean) {
        return new PropertyValidationSupport(bean, beanBundle.getMessage(bean.getClass().getName()));
    }


    public void setBeanBundle(IGBABundle beanBundle) {
        this.beanBundle = beanBundle;
    }


    public void setValidatorBundle(IGBABundle validatorBundle) {
        this.validatorBundle = validatorBundle;
    }


    public IGBABundle getBeanBundle() {
        return beanBundle;
    }


    public IGBABundle getValidatorBundle() {
        return validatorBundle;
    }


    public final void validate(Object bean, String fieldName, Object value, Annotation annotation, ValidationResult validationResult) {
        if (hasNull(value)) {
            this.support = getPropertyValidationSupport(bean);
            this.fieldName = fieldName;
            validateInernal(bean, fieldName, value, annotation, validationResult);
            validationResult.addAllFrom(this.support.getResult());
        }
    }


    protected void addErrorMessage(String fieldName, String message) {
        support.addError(fieldName, message);
    }


    protected void addErrorMessage(String message) {
        addErrorMessage(beanBundle.getMessage(this.fieldName), validatorBundle.getMessage(message));
    }


    protected void addErrorMessageCode(String message) {
        support.addError(beanBundle.getMessage(this.fieldName), validatorBundle.getMessage(message));
    }


    protected void addErrorMessageCode(String code, String addInfo) {
        support.addError(beanBundle.getMessage(this.fieldName), validatorBundle.getMessage(code) + (addInfo != null ? " " + addInfo : ""));
    }


    abstract void validateInernal(Object bean, String fieldName, Object value, Annotation annotation, ValidationResult validationResult);


    /**
     * Value may be null
     *
     * @param value value
     * @return may be null
     */
    abstract protected boolean hasNull(Object value);
}

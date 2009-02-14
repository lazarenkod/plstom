package com.pl.plugins.commons.ui.uinew.domain.view.validation.validators.annotations;

import com.pl.plugins.commons.ui.uinew.domain.view.i18n.IGBABundle;
import com.jgoodies.validation.ValidationResult;

import java.lang.annotation.Annotation;

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 12.02.2009
 * Time: 18:50:28
 */
public interface IAnnotationValidator {
     void validate(Object Bean, String fieldName, Object value, Annotation annotation, ValidationResult validationResult);


    void setBeanBundle(IGBABundle beanBundle);


    void setValidatorBundle(IGBABundle validatorBundle);
}

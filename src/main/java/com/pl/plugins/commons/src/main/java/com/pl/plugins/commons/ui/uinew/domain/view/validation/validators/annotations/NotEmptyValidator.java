package com.pl.plugins.commons.ui.uinew.domain.view.validation.validators.annotations;

import com.jgoodies.validation.ValidationResult;
import java.lang.annotation.Annotation;

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 12.02.2009
 * Time: 19:04:40
 */


public class NotEmptyValidator extends AbstractAnnotationValidator {


    public void validateInernal(Object bean, String fieldName, Object value, Annotation annotation, ValidationResult validationResult) {
        if ((value == null) || (value.toString().trim().length() == 0)) {
            addErrorMessage("field.must.be.not.empty");
        }
    }


    protected boolean hasNull(Object value) {
        return true;
    }
}

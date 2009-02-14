package com.pl.plugins.commons.ui.uinew.domain.view.validation.validators.annotations;

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 12.02.2009
 * Time: 19:02:12
 */
import com.jgoodies.validation.ValidationResult;

import java.lang.annotation.Annotation;


/**
 * @author mukhanov.nikolay@otr.ru 15.01.2007  15:43:11
 */
public class LengthValidator extends AbstractAnnotationValidator {

    private static final int UNKNOWN = -1;


    public void checkParams(Annotation annotation) throws IllegalArgumentException {
        if (!(annotation instanceof Length))
            throw new IllegalStateException(WUA + annotation);

        Length length = (Length) annotation;

        int max = length.max();
        int min = length.min();
        int requied = length.required();

        if (requied != UNKNOWN && requied <= 0)
            throw new IllegalStateException(WUA + length + ": `required' must be positive");

        if (requied != UNKNOWN && (max != UNKNOWN || min != UNKNOWN))
            throw new IllegalStateException(WUA + length + ": you cannot specify `required' with `min' and `max'");
        if (max != UNKNOWN && max <= 0)
            throw new IllegalStateException(WUA + length + ": `max' must be positive");

        if (min != UNKNOWN && min <= 0)
            throw new IllegalStateException(WUA + length + ": `min' must be positive");

        if (max != UNKNOWN && min != UNKNOWN && min >= max)
            throw new IllegalStateException(WUA + length + ": `max' must be greather than `min'");
    }


    public void validateInernal(Object bean, String fieldName, Object value, Annotation annotation, ValidationResult validationResult) {

        if (!(value instanceof String))
            throw new IllegalStateException(WUA + annotation);

        Length length = (Length) annotation;

        checkParams(length);

        int strLen = value.toString().length();

        if (length.required() != -1 && strLen != length.required()) {
            addErrorMessageCode("field.length.must.be.equals", String.valueOf(length.required()));
        }
        if (length.max() > 0 && strLen > length.max()) {
            addErrorMessageCode("field.length.more.declared", "(" + length.max() + ")");
        }
        if (length.min() != -1 && strLen <= length.min()) {
            addErrorMessageCode("field.length.less.declared", "(" + length.min() + ")");
        }
    }


    protected boolean hasNull(Object value) {
        return value != null;
    }
}

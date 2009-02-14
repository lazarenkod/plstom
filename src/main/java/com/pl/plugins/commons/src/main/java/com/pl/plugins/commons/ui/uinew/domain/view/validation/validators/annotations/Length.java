package com.pl.plugins.commons.ui.uinew.domain.view.validation.validators.annotations;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 12.02.2009
 * Time: 18:58:31
 */


/**
 * @author mukhanov.nikolay@otr.ru 15.01.2007  17:46:43
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface Length {
    int min() default -1;

    int max() default -1;

    int required() default -1;

    boolean nullable() default false;
}

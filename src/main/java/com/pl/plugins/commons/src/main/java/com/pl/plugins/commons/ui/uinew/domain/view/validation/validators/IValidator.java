package com.pl.plugins.commons.ui.uinew.domain.view.validation.validators;
import com.jgoodies.validation.Validator;
import com.pl.plugins.commons.ui.uinew.domain.view.i18n.IGBABundle;
/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 12.02.2009
 * Time: 18:30:39
 */

public interface IValidator<T> extends Validator {
    //todo исправить путь в бандле
    public static final String DEFAULT_VALIDATION_BUNDLE_PATH = "ru.otr.nbgba.domain.view.i18n.validators.";
    void setBean(T bean);
    void setBeanBundle(IGBABundle beanBundle);
}
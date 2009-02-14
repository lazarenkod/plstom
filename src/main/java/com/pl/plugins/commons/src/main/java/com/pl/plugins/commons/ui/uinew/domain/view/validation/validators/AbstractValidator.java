package com.pl.plugins.commons.ui.uinew.domain.view.validation.validators;

import com.jgoodies.validation.util.PropertyValidationSupport;
import com.pl.plugins.commons.ui.uinew.domain.view.i18n.IGBABundle;


/**
 * Базовый класс для всех валидаторов. Умеет искать Bundle messages для бинов и валидаторов
 *
 * @author shishov.alexander
 */
public abstract class AbstractValidator<T> implements IValidator<T> {

    private T bean;

    private IGBABundle beanBundle;

    private IGBABundle validatorBundle;


    /**
     * Конструктор валидатора
     *
     * @param beanBundle      GBABundle для бина
     * @param validatorBundle GBABundle для валидатора
     */
    public AbstractValidator(IGBABundle beanBundle, IGBABundle validatorBundle) {
        this.beanBundle = beanBundle;
        this.validatorBundle = validatorBundle;
    }


    /**
     * Конструктор валидатора
     * GBABundle для валидатора берётся по умолчанию из метода getDefaultValidationBundle()
     *
     * @param beanBundle GBABundle для бина
     */
    public AbstractValidator(IGBABundle beanBundle) {
        this.beanBundle = beanBundle;
    }


    /**
     * Конструктор валидатора
     * GBABundle для валидатора берётся по умолчанию из метода getDefaultValidationBundle()
     * GBABundle для бина передаётся через setBeanBundle
     */
    protected AbstractValidator() {
            this.validatorBundle = getDefaultValidationBundle();
    }


    /**
     * Создание PropertyValidationSupport
     *
     * @param bean объект
     * @return PropertyValidationSupport
     */
    protected PropertyValidationSupport getPropertyValidationSupport(Object bean) {
        return new PropertyValidationSupport(bean, beanBundle.getMessage(bean.getClass().getName()));

    }


    /**
     * Установка GBABundle для бина
     *
     * @param beanBundle GBABundle для бина
     */
    public void setBeanBundle(IGBABundle beanBundle) {
        this.beanBundle = beanBundle;
    }


    protected IGBABundle getBeanBundle() {
        if (beanBundle == null) {
            throw new NullPointerException("Bean bundle is null");
        }
        return beanBundle;
    }


    public IGBABundle getValidatorBundle() {
        return validatorBundle;
    }


    /**
     * Метод возвращает Bundle по умолчанию для текущего валидатора
     *
     * @return GBABundle для валидатора
     */
    //TODO после рефакторинга сделать абстрактным
    public IGBABundle getDefaultValidationBundle() {
        return null;
    }


    /**
     * Метод возвращает сообщение, соответствующее полю бина
     *
     * @param key ключ
     * @return сообщение
     */
    public String getBeanMessage(String key) {
        return getBeanBundle().getMessage(key);
    }


    /**
     * Метод возвращает сообщение валидации по ключу
     *
     * @param key ключ
     * @return сообщение
     */
    public String getValidationMessage(String key) {
        return this.validatorBundle.getMessage(key);
    }


    public void setBean(T bean) {
        this.bean = bean;
    }


    public T getBean() {
        return bean;
    }

}

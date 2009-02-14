package com.pl.plugins.commons.ui.uinew.domain.view.validation.validators;

import com.jgoodies.validation.util.PropertyValidationSupport;
import com.pl.plugins.commons.ui.uinew.domain.view.i18n.IGBABundle;


/**
 * ������� ����� ��� ���� �����������. ����� ������ Bundle messages ��� ����� � �����������
 *
 * @author shishov.alexander
 */
public abstract class AbstractValidator<T> implements IValidator<T> {

    private T bean;

    private IGBABundle beanBundle;

    private IGBABundle validatorBundle;


    /**
     * ����������� ����������
     *
     * @param beanBundle      GBABundle ��� ����
     * @param validatorBundle GBABundle ��� ����������
     */
    public AbstractValidator(IGBABundle beanBundle, IGBABundle validatorBundle) {
        this.beanBundle = beanBundle;
        this.validatorBundle = validatorBundle;
    }


    /**
     * ����������� ����������
     * GBABundle ��� ���������� ������ �� ��������� �� ������ getDefaultValidationBundle()
     *
     * @param beanBundle GBABundle ��� ����
     */
    public AbstractValidator(IGBABundle beanBundle) {
        this.beanBundle = beanBundle;
    }


    /**
     * ����������� ����������
     * GBABundle ��� ���������� ������ �� ��������� �� ������ getDefaultValidationBundle()
     * GBABundle ��� ���� ��������� ����� setBeanBundle
     */
    protected AbstractValidator() {
            this.validatorBundle = getDefaultValidationBundle();
    }


    /**
     * �������� PropertyValidationSupport
     *
     * @param bean ������
     * @return PropertyValidationSupport
     */
    protected PropertyValidationSupport getPropertyValidationSupport(Object bean) {
        return new PropertyValidationSupport(bean, beanBundle.getMessage(bean.getClass().getName()));

    }


    /**
     * ��������� GBABundle ��� ����
     *
     * @param beanBundle GBABundle ��� ����
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
     * ����� ���������� Bundle �� ��������� ��� �������� ����������
     *
     * @return GBABundle ��� ����������
     */
    //TODO ����� ������������ ������� �����������
    public IGBABundle getDefaultValidationBundle() {
        return null;
    }


    /**
     * ����� ���������� ���������, ��������������� ���� ����
     *
     * @param key ����
     * @return ���������
     */
    public String getBeanMessage(String key) {
        return getBeanBundle().getMessage(key);
    }


    /**
     * ����� ���������� ��������� ��������� �� �����
     *
     * @param key ����
     * @return ���������
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

package com.pl.plugins.commons.ui.uinew.domain.view.validation;

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 12.02.2009
 * Time: 17:41:03
 */
import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.beans.PropertyConnector;
import com.jgoodies.binding.value.ValueHolder;
import com.jgoodies.binding.value.ValueModel;
import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.ValidationResultModel;
import com.jgoodies.validation.message.SimpleValidationMessage;
import com.jgoodies.validation.util.DefaultValidationResultModel;
import com.jgoodies.validation.util.ValidationResultModelContainer;
import com.jgoodies.validation.view.ValidationComponentUtils;
import com.jgoodies.validation.view.ValidationResultViewFactory;
import com.pl.plugins.commons.ui.uinew.domain.view.i18n.IGBABundle;
import com.pl.plugins.commons.ui.uinew.domain.view.i18n.GBABundle;
import com.pl.plugins.commons.ui.uinew.domain.view.validation.validators.IValidator;
import com.pl.plugins.commons.ui.uinew.domain.view.validation.validators.DefaultAnnotationValidator;
import com.pl.plugins.commons.ui.uinew.core.binding.AbstractBindingModelDTO;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.*;


public class SimplePresentationModel<T> extends PresentationModel {

    private ValidationResultModel validationResultModel;

    final private Set<IValidator> validators = Collections.synchronizedSet(new HashSet<IValidator>());

    private ValidationResultModelContainer resultContainer = new ValidationResultModelContainer(new SimpleValidationMessage("Simple message"));

    private Container validableContainer;

    private IGBABundle beanBundle;

    private Class beanClazz;

    private ValueModel validationResultHolder;

    /**
     * @param object
     * @param resultContainer
     * @param validator
     * @deprecated use new SimplePresentationModel(bean) - удалить этот конутруктор
     */
    @Deprecated
    public SimplePresentationModel(T object, ValidationResultModelContainer resultContainer, IValidator validator) {
        super(object);
        this.validationResultModel = new DefaultValidationResultModel();
        this.resultContainer = resultContainer;
        this.resultContainer.add(this.validationResultModel);
        initEvents();
        validator.setBean(object);
        addValidator(validator);
    }


    public SimplePresentationModel(T bean, Container validableContainer, IValidator validator) {
        this(new ValueHolder(bean, true), validableContainer, validator, bean.getClass());
    }


    public SimplePresentationModel(T bean, Container validableContainer, IGBABundle newBeanBundle) {
        this(bean, validableContainer, new DefaultAnnotationValidator());
        setCustomBundle(newBeanBundle);
    }


    public SimplePresentationModel(T bean, Container validableContainer) {
        this(bean, validableContainer, new DefaultAnnotationValidator());
    }


    public SimplePresentationModel(ValueModel bean, Container validableContainer, Class beanClazz) {
        this(bean, validableContainer, new DefaultAnnotationValidator(), beanClazz);
    }


    public SimplePresentationModel(ValueModel bean, Container validableContainer, Class beanClazz, IGBABundle bundle) {
        this(bean, validableContainer, new DefaultAnnotationValidator(), beanClazz);
        setCustomBundle(bundle);
    }


    public SimplePresentationModel(ValueModel beanChannel, Container validableContainer, IValidator validator, Class beanClazz) {
        super(beanChannel);
        this.beanClazz = beanClazz;
        this.validableContainer = validableContainer;
        this.resultContainer.setExpanded(true);
        this.validationResultModel = new DefaultValidationResultModel();
        this.resultContainer.add(this.validationResultModel);
        try {
            beanBundle = new GBABundle(getBeanClazz());
        } catch (MissingResourceException e) {
            //не нашли файл с ресурсами для класса берём по умолчанию
            beanBundle = new GBABundle(IGBABundle.DEFAULT_BUNDLE);

        }

        validationResultHolder = new ValueHolder();
        validationResultHolder.setValue(Boolean.TRUE);

        initEvents();

        addValidator(validator);
    }


    public void setCustomBundle(IGBABundle newBeanBundle) {
        newBeanBundle.setParent(this.beanBundle);
        this.beanBundle = newBeanBundle;
        validate();
    }


    public ValidationResultModel getValidationResultModel() {
        return this.validationResultModel;
    }


    @Override
    public void beforeBeanChange(Object oldBean, Object newBean) {
        super.beforeBeanChange(oldBean, newBean);
        if (beanBundle == null && oldBean != null && newBean != null) {
            beanBundle = new GBABundle(newBean.getClass());
        }
    }


    /**
     * Создание связи между PresentationModel и ValidationResultModel при помощи лисенеров
     */
    private void initEvents() {
        PropertyChangeListener handler = new ValidationUpdateHandler();

        //реагировать на изменение бина в PresentationModel (методы setBean и getBean)
        addBeanPropertyChangeListener(handler);

        //реагировать на изменения значений бина (все изменения проходят через BeanChannel)
        getBeanChannel().addValueChangeListener(handler);

        this.resultContainer.addPropertyChangeListener(ValidationResultModel.PROPERTYNAME_RESULT, new ValidationResultModelHandler());
    }


    /**
     * Валидация полей при помощи валидаторов
     */
    private void validate() {
        ValidationResult validationResult = new ValidationResult();
        for (IValidator validator : validators) {
            validator.setBeanBundle(beanBundle);
            validator.setBean(getBean());
            ValidationResult result = validator.validate(null);//todo посмотреть
            if (result != null) {
                validationResult.addAllFrom(result);
            }
        }
        validationResultModel.setResult(validationResult);
    }


    /**
     * Handler, который отслеживает изменения валидации
     */
    private final class ValidationUpdateHandler implements PropertyChangeListener {
        public void propertyChange(PropertyChangeEvent evt) {
            validate();
        }
    }

    /**
     * @return массив валидаторов
     */
    public IValidator[] getValidators() {
        return validators.toArray(new IValidator[validators.size()]);
    }

    /**
     * Удалить валидатор
     *
     * @param validator - удаляемый валидатор
     */
    public void removeValidator(IValidator validator) {
        if (validator == null) {
            throw new NullPointerException("Validator is null");
        }
        validators.remove(validator);
        validate();
    }

    /**
     * Добавление валидатора к списку существующих
     *
     * @param validator валидатор
     */
    public void addValidator(IValidator validator) {
        if (validator == null) {
            throw new NullPointerException("Validator is null");
        }
        validators.add(validator);
        validate();
    }


    /**
     * Add control buttons
     *
     * @param button control button to add
     */
    public void addControlButton(final JButton button) {
        addControlBean(button, "enabled");
    }

    /**
     * Add the bean to the notification queue with setting hte
     *
     * @param bean         bean to notify
     * @param bindProperty the boolean property to set.
     */
    public void addControlBean(final Object bean, String bindProperty) {
        if (bean == null) {
            throw new IllegalArgumentException("bean must not be null");
        }
        clearResult();
        PropertyConnector.connectAndUpdate(validationResultHolder,
                bean, bindProperty);

        validate();
    }


    public Class getBeanClazz() {
        return beanClazz;
    }


    public IGBABundle getBeanBundle() {
        return beanBundle;
    }


    public void setExcludedFields(Set<String> fieldNames) {
//     fixme   for (IValidator validator : validators) {
//            if (validator instanceof DefaultAnnotationValidator) {
//                ((DefaultAnnotationValidator) validator).setExcludedFields(fieldNames);
//            }
//        }
        validate();
    }


    /**
     * Создание компонента, на который выводятся сообщения валидации
     *
     * @return компонент со списком сообщений валидации
     */
    public JComponent getValidationResultView() {
        return ValidationResultViewFactory.createReportList(this.resultContainer);
    }


    //TODO удалить этот метод
    @Deprecated
    public void addValidationResultListener(PropertyChangeListener listener) {
        this.validationResultModel.addPropertyChangeListener(ValidationResultModel.PROPERTYNAME_RESULT, listener);
        validate();
    }


    private class ValidationResultModelHandler implements PropertyChangeListener {
        public void propertyChange(PropertyChangeEvent evt) {
            updateComponentTreeMandatoryAndSeverity();
        }
    }


    protected void updateComponentTreeMandatoryAndSeverity() {
        validationResultHolder.setValue(!resultContainer.getExpandedValidationResult().hasErrors());
        ValidationComponentUtils.updateComponentTreeSeverityBackground(this.validableContainer, resultContainer.getExpandedValidationResult());
    }


    public Boolean getCheckValidationResult() {
        return (Boolean) validationResultHolder.getValue();
    }

    public ValidationResultModelContainer getResultContainer() {
        return resultContainer;
    }


    public void bindPresentationModel(SimplePresentationModel presentationModel) {
        this.resultContainer.add(presentationModel.getValidationResultModel());
        presentationModel.refresh();
    }


    public void unbindPresentationModel(SimplePresentationModel presentationModel) {
        presentationModel.getValidationResultModel().setResult(new ValidationResult());
        this.resultContainer.remove(presentationModel.getResultContainer());
    }


    public void refresh() {
        clearResult();
        validate();
    }


    public void clearResult() {
        this.validationResultModel.setResult(new ValidationResult());
    }

    public ValueModel getValidationResultHolder() {
        return validationResultHolder;
    }

    @Override
    public void setBean(Object o) {
        super.setBean(o);
        if (o instanceof AbstractBindingModelDTO) {
            ((AbstractBindingModelDTO) o).reFire();
        }
    }
}
package com.pl.plugins.commons.ui.uinew.model.impl;


import com.pl.plugins.commons.ui.uinew.model.IModel;
import com.pl.plugins.commons.ui.uinew.model.IModelSubscriber;

import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;

public class Model<P> implements IModel {
    /**
     * Свойство модели
     */
    private P property;

    private final Collection<IModelSubscriber<P>> subscribers = new CopyOnWriteArrayList<IModelSubscriber<P>>();

    /**
     * Конструктор
     *
     * @param property свойство модели
     */
    public Model(P property) {
        if (property == null)
            throw new NullPointerException("Пустой параметр");
        this.property = property;
    }

    /**
     * Получить свойство модели
     *
     * @return свойство модели
     */
    public P getProperty() {
        return property;
    }

    /**
     * Установить свойство модели
     *
     * @param property свойство модели
     */
    public void setProperty(P property) {
        if (property == null)
            throw new NullPointerException("Пустой параметр");
        this.property = property;
        notifySubscribers();
    }

    protected void notifySubscribers() {
        for (final IModelSubscriber subscriber : subscribers)
            notifySubscriber(subscriber);
    }

    private void notifySubscriber(IModelSubscriber subscriber) {
        assert subscriber != null;
        subscriber.dataChanged(this);
    }

    public void subscribe(IModelSubscriber subscriber) {
        if (subscriber == null)
            throw new NullPointerException("Illegal parameter");
        if (subscribers.contains(subscriber))
            throw new IllegalArgumentException("Re-subscription: " +
                    subscriber);
        subscribers.add(subscriber);
        notifySubscriber(subscriber);
    }

    public void unsubscribe(IModelSubscriber subscriber) {
        if (subscriber == null)
            throw new NullPointerException("Illegal parameter");
        if (!subscribers.contains(subscriber))
            throw new IllegalArgumentException("Unknown subscriber: " +
                    subscriber);
        subscribers.remove(subscriber);
	}

}
package com.pl.plugins.commons.ui.uinew.model;

/**
 * Created by IntelliJ IDEA.
 * User: Администратор
 * Date: 10.09.2008
 * Time: 8:23:24    df d
 */
public interface IPublisher {
    void subscribe(IModelSubscriber subscriber);
    void unsubscribe(IModelSubscriber subscriber);
}
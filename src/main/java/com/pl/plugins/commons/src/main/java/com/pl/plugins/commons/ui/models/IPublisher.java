package com.pl.plugins.commons.ui.models;

/**
 * Created by IntelliJ IDEA.
 * User: �������������
 * Date: 10.09.2008
 * Time: 8:23:24
 */
public interface IPublisher {
    void subscribe(ISubscriber subscriber);

    void unsubscribe(ISubscriber subscriber);
}

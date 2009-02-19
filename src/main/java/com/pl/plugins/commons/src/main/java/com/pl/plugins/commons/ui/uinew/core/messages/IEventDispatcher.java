package com.pl.plugins.commons.ui.uinew.core.messages;

/**
 * Created by IntelliJ IDEA.
 * User: Администратор
 * Date: 07.02.2009
 * Time: 23:23:49
 */
public interface IEventDispatcher {
    /**
     * Осуществляет подписку на все события данного класса
     * @param clazz
     */
    void subscribe(Class clazz,ISubscriber subscriber);
    void subscribe(Class clazz,EventType eventType,ISubscriber subscriber);
    void subscribe(SubscribingMessage subscribingMessage,ISubscriber subscriber);
    void unsubscribe(Class clazz,ISubscriber subscriber);
    void unsubscribe(Class clazz,EventType eventType,ISubscriber subscriber);
    void unsubscribe(SubscribingMessage subscribingMessage,ISubscriber subscriber);
    void dispatchMessage(Message message);
}

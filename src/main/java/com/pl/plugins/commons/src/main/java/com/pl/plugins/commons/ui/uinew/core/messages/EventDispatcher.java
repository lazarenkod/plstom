package com.pl.plugins.commons.ui.uinew.core.messages;

import com.google.common.collect.HashMultimap;
import org.apache.log4j.Logger;

import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Администратор
 * Date: 08.02.2009
 * Time: 0:56:28
 */
public class EventDispatcher implements IEventDispatcher {

    private HashMultimap<SubscribingMessage, ISubscriber> messageSubscribers = new HashMultimap<SubscribingMessage, ISubscriber>();
    private final Logger log = Logger.getLogger(getClass());

    public EventDispatcher() {
    }

    /**
     * Осуществляет подписку на все события данного класса
     *
     * @param clazz
     */
    public void subscribe(Class clazz, ISubscriber subscriber) {
        //добавляем подписчика на все события
        SubscribingMessage sm = new SubscribingMessage(clazz, EventType.CREATE);
        messageSubscribers.put(sm, subscriber);
        sm = new SubscribingMessage(clazz, EventType.CHANGE);
        messageSubscribers.put(sm, subscriber);
        sm = new SubscribingMessage(clazz, EventType.DELETE);
        messageSubscribers.put(sm, subscriber);
        sm = new SubscribingMessage(clazz, EventType.MULTIPLY_CREATE);
        messageSubscribers.put(sm, subscriber);
        sm = new SubscribingMessage(clazz, EventType.MULTIPLY_CHANGE);
        messageSubscribers.put(sm, subscriber);
        sm = new SubscribingMessage(clazz, EventType.MULTIPLY_DELETE);
        messageSubscribers.put(sm, subscriber);
    }

    public void subscribe(Class clazz, EventType eventType, ISubscriber subscriber) {
        SubscribingMessage sm = new SubscribingMessage(clazz, eventType);
        messageSubscribers.put(sm, subscriber);
    }

    public void subscribe(SubscribingMessage subscribingMessage, ISubscriber subscriber) {
        messageSubscribers.put(subscribingMessage, subscriber);
    }

    public void unsubscribe(Class clazz, ISubscriber subscriber) {
        SubscribingMessage sm = new SubscribingMessage(clazz, EventType.CREATE);
        messageSubscribers.remove(sm, subscriber);
        sm = new SubscribingMessage(clazz, EventType.CHANGE);
        messageSubscribers.remove(sm, subscriber);
        sm = new SubscribingMessage(clazz, EventType.DELETE);
        messageSubscribers.remove(sm, subscriber);
        sm = new SubscribingMessage(clazz, EventType.MULTIPLY_CREATE);
        messageSubscribers.remove(sm, subscriber);
        sm = new SubscribingMessage(clazz, EventType.MULTIPLY_CHANGE);
        messageSubscribers.remove(sm, subscriber);
        sm = new SubscribingMessage(clazz, EventType.MULTIPLY_DELETE);
        messageSubscribers.remove(sm, subscriber);
    }

    public void unsubscribe(Class clazz, EventType eventType, ISubscriber subscriber) {
        SubscribingMessage sm = new SubscribingMessage(clazz, eventType);
        messageSubscribers.remove(sm, subscriber);
    }

    public void unsubscribe(SubscribingMessage subscribingMessage, ISubscriber subscriber) {
        messageSubscribers.remove(subscribingMessage, subscriber);
    }

    public void dispatchMessage(final Message message) {
        //для распространения сообщение запускаем поток
        new Thread() {
            @Override
            public void run() {
                SubscribingMessage sm = new SubscribingMessage(message.getClazz(), message.getEventType());
                Set<ISubscriber> subscribers = messageSubscribers.get(sm);
                for (ISubscriber subscriber : subscribers) {
                    subscriber.dataChanged(message);
                    System.out.println(getClass() + " Message generated " + message);
           //             log.info(getClass()+ " Message generated " +message);  //TODO Доделать систему логирования
                }
            }
        }.start();

    }


}

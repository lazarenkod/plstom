package com.pl.plugins.commons.ui.uinew.core.messages;

/**
 * Created by IntelliJ IDEA.
 * User: Администратор
 * Date: 07.02.2009
 * Time: 18:41:42
 */

/**
 * Класс служит для организации подписки на события  определенного типа
 *
 */
public class SubscribingMessage {
    /**
     * Класс наблюдаемого объекта
     */
    private Class clazz;
    /**
     * Тип события над объектом
     */
    private EventType eventType;

    public SubscribingMessage(Class clazz, EventType eventType) {
        this.clazz = clazz;
        this.eventType = eventType;
    }

    public SubscribingMessage(Class clazz) {
        this.clazz = clazz;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubscribingMessage that = (SubscribingMessage) o;

        if (!clazz.equals(that.clazz)) return false;
        if (eventType != that.eventType) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = clazz.hashCode();
        result = 31 * result + eventType.hashCode();
        return result;
    }
}


package com.pl.plugins.commons.ui.uinew.core;

/**
 * Created by IntelliJ IDEA.
 * User: Администратор
 * Date: 07.02.2009
 * Time: 1:08:17
 */

/**
 * Класс Сообщение.
 * Представляет собой базовое сообщение системы.
 */
public class Message {
    /**
     * Класс объекта
     */
    private Class clazz;
    /**
     * тип события
     */
    private EventType eventType;
    /**
     * Само сообщение(объект который появился\изменился)
     */
    private Object message;
    /**
     * Объект вызвавший событие
     */
    private Object source;
    /**
     * дополнительное сообщение 
     */
    private String logMessage;

    public Message(Class clazz, EventType eventType, Object message, Object source, String logMessage) {
        this.clazz = clazz;
        this.eventType = eventType;
        this.message = message;
        this.source = source;
        this.logMessage = logMessage;
    }

    public Message(Class clazz, EventType eventType, Object message, Object source) {
        this.clazz = clazz;
        this.eventType = eventType;
        this.message = message;
        this.source = source;
    }

    public Message(Class clazz, EventType eventType, Object message) {
        this.clazz = clazz;
        this.eventType = eventType;
        this.message = message;
    }

//    public Message(Class clazz, Object message) {
//        this.clazz = clazz;
//        this.message = message;
////        eventType=EventType.ALL;
//    }

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

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public Object getSource() {
        return source;
    }

    public void setSource(Object source) {
        this.source = source;
    }

    public String getLogMessage() {
        return logMessage;
    }

    public void setLogMessage(String logMessage) {
        this.logMessage = logMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message1 = (Message) o;

        if (!clazz.equals(message1.clazz)) return false;
        if (eventType != message1.eventType) return false;
        if (!message.equals(message1.message)) return false;
        if (!source.equals(message1.source)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = clazz.hashCode();
        result = 31 * result + eventType.hashCode();
        result = 31 * result + message.hashCode();
        result = 31 * result + source.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Message {" +
                "clazz=" + clazz +
                ", eventType=" + eventType +
                ", message=" + message +
                ", source=" + source +
                ", logMessage='" + logMessage + '\'' +
                '}';
    }
}

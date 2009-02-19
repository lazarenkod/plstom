package com.pl.plugins.commons.tests;

import com.pl.plugins.commons.ui.uinew.core.messages.EventDispatcher;
import com.pl.plugins.commons.ui.uinew.core.messages.Message;
import com.pl.plugins.commons.ui.uinew.core.messages.EventType;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * EventDispatcher Tester.
 *
 * @author <Authors name>
 * @since <pre>02/10/2009</pre>
 * @version 1.0
 */
public class EventDispatcherTest extends TestCase {
    public EventDispatcherTest(String name) {
        super(name);
    }

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     *
     * Method: subscribe(Class clazz, ISubscriber subscriber)
     *
     */
    public void testSubscribeForClazzSubscriber() throws Exception {
        TestSubscriber ts=new TestSubscriber();
        TestSubscriber ts2=new TestSubscriber();
        EventDispatcher ed=new EventDispatcher();
        ed.subscribe(String.class,ts);
        ed.subscribe(String.class,ts2);
        ed.dispatchMessage(new Message(String.class, EventType.CREATE,"I'm a new string"));
    }


    /**
     *
     * Method: subscribe(Class clazz, EventType eventType, ISubscriber subscriber)
     *
     */
    public void testSubscribeForClazzEventTypeSubscriber() throws Exception {
        //TODO: Test goes here...
        
    }

    /**
     *
     * Method: subscribe(SubscribingMessage subscribingMessage, ISubscriber subscriber)
     *
     */
    public void testSubscribeForSubscribingMessageSubscriber() throws Exception {
        //TODO: Test goes here...
    }

    /**
     *
     * Method: unsubscribe(Class clazz, ISubscriber subscriber)
     *
     */
    public void testUnsubscribeForClazzSubscriber() throws Exception {
        //TODO: Test goes here...
    }

    /**
     *
     * Method: unsubscribe(Class clazz, EventType eventType, ISubscriber subscriber)
     *
     */
    public void testUnsubscribeForClazzEventTypeSubscriber() throws Exception {
        //TODO: Test goes here...
    }

    /**
     *
     * Method: unsubscribe(SubscribingMessage subscribingMessage, ISubscriber subscriber)
     *
     */
    public void testUnsubscribeForSubscribingMessageSubscriber() throws Exception {
        //TODO: Test goes here...
    }

    /**
     *
     * Method: dispatchMessage(Message message)
     *
     */
    public void testDispatchMessage() throws Exception {
        //TODO: Test goes here...
    }



    public static Test suite() {
        return new TestSuite(EventDispatcherTest.class);
    }
}

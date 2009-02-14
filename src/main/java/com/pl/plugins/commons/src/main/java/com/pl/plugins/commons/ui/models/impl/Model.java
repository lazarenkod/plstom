package com.pl.plugins.commons.ui.models.impl;

import com.pl.plugins.commons.ui.models.IModel;
import com.pl.plugins.commons.ui.models.ISubscriber;

import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;

public class Model implements IModel {
	
	private final Collection<ISubscriber> subscribers = new CopyOnWriteArrayList<ISubscriber>();

    protected void notifySubscribers() {
		for (final ISubscriber subscriber : subscribers)
			notifySubscriber(subscriber);
	}

	private void notifySubscriber(ISubscriber subscriber) {
		assert subscriber != null;
		subscriber.dataChanged(null);
	}
	
	public void subscribe(ISubscriber subscriber) {
		if (subscriber == null)
			throw new NullPointerException("Illegal parameter");
		if (subscribers.contains(subscriber))
                throw new IllegalArgumentException("Re-subscription: " +
					subscriber);
		subscribers.add(subscriber);
		notifySubscriber(subscriber);
	}

	public void unsubscribe(ISubscriber subscriber) {
		if (subscriber == null)
			throw new NullPointerException("Illegal parameter");
		if (!subscribers.contains(subscriber))
			throw new IllegalArgumentException("Unknown subscriber: " +
					subscriber);
		subscribers.remove(subscriber);
	}

}

package com.pl.plugins.commons.ui.models.impl;

import com.pl.plugins.commons.ui.models.ISubscriber;
import com.pl.plugins.commons.ui.models.IModel;

import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;

public class CommonModel implements IModel {
	
	private final Collection<ISubscriber> subscribers = new CopyOnWriteArrayList<ISubscriber>();
	
	protected void notifySubscribers() {
		for (final ISubscriber subscriber : subscribers)
			notifySubscriber(subscriber);
	}

	private void notifySubscriber(ISubscriber subscriber) {
		assert subscriber != null;
		subscriber.dataChanged(null);
	}
	
	public void subscribe(ISubscriber subscriber) throws IllegalArgumentException{

        if (subscriber == null)
			throw new IllegalArgumentException("Parameter subscriber must be not null");

        if (!subscribers.contains(subscriber))
			subscribers.add(subscriber);
	}

	public void unsubscribe(ISubscriber subscriber) throws IllegalArgumentException{

        if (subscriber == null)
			throw new NullPointerException("Parameter subscriber must be not null");

        if (subscribers.contains(subscriber))
			subscribers.remove(subscriber);
	}
}

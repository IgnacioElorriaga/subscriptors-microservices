package com.adidas.subscription.service.facade;

import com.adidas.subscription.client.model.EventRequest;

public interface SubscriptionEventServiceFacade {

	public void executeEvent(EventRequest request);
}

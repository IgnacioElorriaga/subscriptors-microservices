package com.adidas.subscription.service.facade;

import com.adidas.subscription.client.model.SubscriptionRequest;

public interface SubscriptionEventServiceFacade {

	public void executeEvent(SubscriptionRequest request);
}

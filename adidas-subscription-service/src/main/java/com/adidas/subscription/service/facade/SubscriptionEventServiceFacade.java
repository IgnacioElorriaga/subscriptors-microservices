package com.adidas.subscription.service.facade;

import com.adidas.subscription.service.dto.SubscriptionRequest;

public interface SubscriptionEventServiceFacade {

	public void executeEvent(SubscriptionRequest request);
}

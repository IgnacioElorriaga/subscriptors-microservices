package com.adidas.subscription.service.facade;

import com.adidas.subscription.client.model.SubscriptionRequest;
import com.adidas.subscription.service.dto.Subscription;

public interface SubscriptionDatabaseFacade {

	 Subscription save(SubscriptionRequest obj);
}

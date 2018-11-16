package com.adidas.subscription.service.facade;

import com.adidas.subscription.service.dto.Subscription;
import com.adidas.subscription.service.dto.SubscriptionRequest;

public interface SubscriptionDatabaseFacade {

	 Subscription save(SubscriptionRequest obj);
}

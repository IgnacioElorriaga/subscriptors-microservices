package com.adidas.subscription.service.facade;

import javax.validation.constraints.NotNull;

import com.adidas.subscription.client.model.SubscriptionRequest;
import com.adidas.subscription.service.dto.Subscription;

public interface SubscriptionEmailServiceFacade {

	public Subscription processEmail(@NotNull SubscriptionRequest request);
}

package com.adidas.subscription.service.facade;

import javax.validation.constraints.NotNull;

import com.adidas.subscription.service.dto.Subscription;
import com.adidas.subscription.service.dto.SubscriptionRequest;

public interface SubscriptionEmailServiceFacade {

	public Subscription processEmail(@NotNull SubscriptionRequest request);
}

package com.adidas.subscription.service.facade;

import javax.validation.constraints.NotNull;

import com.adidas.subscription.client.model.EmailRequest;

public interface SubscriptionEmailServiceFacade {

	public void processEmail(@NotNull final EmailRequest request);
}

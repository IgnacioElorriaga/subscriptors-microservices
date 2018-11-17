package com.adidas.subscription.service.facade;

import javax.validation.constraints.NotNull;

import com.adidas.subscription.client.model.EventRequest;

public interface SubscriptionEventServiceFacade {

	public void executeEvent(@NotNull final EventRequest request);
}

package com.adidas.subscription.client;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.adidas.subscription.client.model.EventRequest;
import com.adidas.subscription.client.model.EventResponse;

@Component
public class EventServiceFallback implements EventServiceFeignClient {

	@Override
	public EventResponse triggerSubscription(@NotNull EventRequest body) {
		return EventResponse.builder().answer("KO Service").build();
	}

}

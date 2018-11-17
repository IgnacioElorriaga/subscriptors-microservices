package com.adidas.subscription.service.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.adidas.subscription.client.model.EventRequest;
import com.adidas.subscription.service.dto.Subscription;
@Component
public class SubscriptionToEventRequest implements Converter<Subscription, EventRequest>{

	@Override
	public EventRequest convert(Subscription source) {
		return EventRequest.builder()
				.subscriptionId(source.getSubscriptionId())
				.build();
	}

}

package com.adidas.subscription.service.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.adidas.subscription.client.model.SubscriptionRequest;
import com.adidas.subscription.service.dto.Subscription;

@Component
public class SubscriptionToSubscriptionRequestConverter implements Converter<Subscription, SubscriptionRequest> {

	@Override
	public SubscriptionRequest convert(Subscription source) {
		return SubscriptionRequest.builder()
				.consent(source.getConsent())
				.dateOfBirth(source.getDateOfBirth().toString())
				.email(source.getEmail())
				.firstName(source.getFirstName())
				.gender(source.getGender())
				.build();
	}

}

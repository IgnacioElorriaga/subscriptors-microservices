package com.adidas.subscription.service.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.adidas.subscription.client.model.SubscriptionRequest;
import com.adidas.subscription.service.dto.Gender;
import com.adidas.subscription.service.dto.Subscription;

/**
 * Converted used to move the values from the {@linkplain Subscription internal} object to the {@linkplain SubscriptionRequest Request} object. <br/>
 * It uses the Converter interface of Spring.
 * 
 * @author nacho
 *
 */
@Component
public class SubscriptionToSubscriptionRequestConverter implements Converter<Subscription, SubscriptionRequest> {

	@Override
	public SubscriptionRequest convert(Subscription source) {
		return SubscriptionRequest.builder()
				.consent(source.getConsent())
				.dateOfBirth(source.getDateOfBirth().toString())
				.email(source.getEmail())
				.firstName(source.getFirstName())
				.gender(Gender.lookup(source.getGender()))
				.build();
	}

}

package com.adidas.subscription.service.converter;

import javax.validation.constraints.NotNull;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.adidas.subscription.client.model.SubscriptionRequest;
import com.adidas.subscription.service.Utils;
import com.adidas.subscription.service.dto.Gender;
import com.adidas.subscription.service.dto.Subscription;

/**
 * Converted used to move the values from the {@linkplain SubscriptionRequest Request} object to the internal {@linkplain Subscription} object. <br/>
 * It uses the Converter interface of Spring.
 * 
 * @author nacho
 *
 */
@Component
public class SubscriptionRequestToSubscriptionConverter implements Converter<SubscriptionRequest, Subscription> {

	@Override
	public Subscription convert(@NotNull final SubscriptionRequest source) {

		return Subscription.builder()
				.consent(source.getConsent())
				.dateOfBirth(Utils.obtainDate(source.getDateOfBirth()))
				.email(source.getEmail())
				.firstName(source.getFirstName())
				.gender(Gender.obtainValue(source.getGender()))
				.build();
	}



}

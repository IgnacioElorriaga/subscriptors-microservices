package com.adidas.subscription.service.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.adidas.subscription.client.model.DatabaseRequest;
import com.adidas.subscription.service.dto.SubscriptionRequest;
@Component
public class SubscriptionRequestToDatabaseRequest implements Converter<SubscriptionRequest, DatabaseRequest> {

	@Override
	public DatabaseRequest convert(SubscriptionRequest source) {
		return DatabaseRequest.builder()
				.consent(source.getConsent())
				.dateOfBirth(source.getDateOfBirth())
				.email(source.getEmail())
				.firstName(source.getFirstName())
				.gender(source.getGender())
				.build();
	}

}

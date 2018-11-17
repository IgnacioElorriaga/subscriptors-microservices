package com.adidas.subscription.service.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.adidas.subscription.client.model.EmailRequest;
import com.adidas.subscription.service.dto.SubscriptionRequest;
@Component
public class SubscriptionRequestToEmailRequest implements Converter<SubscriptionRequest, EmailRequest>{

	@Override
	public EmailRequest convert(SubscriptionRequest source) {
		return EmailRequest.builder()
				.email(source.getEmail())
				.build();
	}

}

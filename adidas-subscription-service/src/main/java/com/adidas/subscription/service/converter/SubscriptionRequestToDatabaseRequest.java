package com.adidas.subscription.service.converter;
import static com.adidas.subscription.service.dto.Gender.OTHER;
import static com.adidas.subscription.service.dto.Gender.lookup;
import static org.apache.commons.lang.StringUtils.EMPTY;
import static org.apache.commons.lang.StringUtils.isBlank;

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
				.firstName(isBlank(source.getFirstName()) ? EMPTY : source.getFirstName())
				.gender(isBlank(source.getGender()) ? lookup(OTHER) : source.getGender())
				.newsletterId(source.getNewsletterId())
				.build();
	}

}

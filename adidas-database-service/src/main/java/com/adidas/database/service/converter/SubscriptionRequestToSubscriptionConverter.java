package com.adidas.database.service.converter;
import static org.apache.commons.lang.StringUtils.EMPTY;
import static org.apache.commons.lang.StringUtils.isEmpty;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.adidas.database.service.model.Subscription;
import com.adidas.database.service.model.SubscriptionRequest;
/**
 * Converted to switch form the domain of the controller layer to the Bean of the Service layer. <br/>
 * It will transform the nulls values to empty or default
 * 
 * @author nacho
 *
 */
@Component
public class SubscriptionRequestToSubscriptionConverter implements Converter<SubscriptionRequest, Subscription> {

	@Override
	public Subscription convert(final SubscriptionRequest source) {
		return Subscription.builder()
		.consent(source.getConsent())
		.dateOfBirth(source.getDateOfBirth())
		.email(source.getEmail().toLowerCase())
		.firstName(StringUtils.isEmpty(source.getFirstName()) ? EMPTY : source.getFirstName())
		.gender(isEmpty(source.getGender()) ? EMPTY : source.getGender())
		.newsletterId(source.getNewsletterId())
		.build();
	}

}

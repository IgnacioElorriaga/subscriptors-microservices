package com.adidas.subscription.service.converter;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.adidas.subscription.client.model.DatabaseRequest;
import com.adidas.subscription.service.converter.SubscriptionRequestToDatabaseRequest;
import com.adidas.subscription.service.dto.SubscriptionRequest;

@RunWith(MockitoJUnitRunner.class)
public class SubscriptionRequestToDatabaseRequestTest {

	private SubscriptionRequestToDatabaseRequest converter = new SubscriptionRequestToDatabaseRequest();
	
	@Test
	public void allFieldsOk() {
		DatabaseRequest actual = converter.convert(SubscriptionRequest.builder()
				.consent(Boolean.FALSE)
				.dateOfBirth("1980-08-23")
				.email("abcde@ad.co.uk")
				.firstName("Selena")
				.gender("female")
				.newsletterId(12367)
				.build());
		SubscriptionRequest expected = SubscriptionRequest.builder()
		.consent(Boolean.FALSE)
		.dateOfBirth("1980-08-23")
		.email("abcde@ad.co.uk")
		.firstName("Selena")
		.gender("female")
		.newsletterId(12367)
		.build();
		
		assertEquals(expected.getConsent(), actual.getConsent());
		assertEquals(expected.getDateOfBirth(), actual.getDateOfBirth());
		assertEquals(expected.getEmail(), actual.getEmail());
		assertEquals(expected.getFirstName(), actual.getFirstName());
		assertEquals(expected.getGender(), actual.getGender());
		assertEquals(expected.getNewsletterId(), actual.getNewsletterId());

	}
	
	@Test
	public void mandatoryOk() {
		DatabaseRequest actual = converter.convert(SubscriptionRequest.builder()
				.consent(Boolean.FALSE)
				.dateOfBirth("1980-08-23")
				.email("abcde@ad.co.uk")
				.newsletterId(12367)
				.build());
		SubscriptionRequest expected = SubscriptionRequest.builder()
		.consent(Boolean.FALSE)
		.dateOfBirth("1980-08-23")
		.email("abcde@ad.co.uk")
		.firstName("")
		.gender("other")
		.newsletterId(12367)
		.build();
		assertEquals(expected.getConsent(), actual.getConsent());
		assertEquals(expected.getDateOfBirth(), actual.getDateOfBirth());
		assertEquals(expected.getEmail(), actual.getEmail());
		assertEquals(expected.getFirstName(), actual.getFirstName());
		assertEquals(expected.getGender(), actual.getGender());
		assertEquals(expected.getNewsletterId(), actual.getNewsletterId());

	}
	
}

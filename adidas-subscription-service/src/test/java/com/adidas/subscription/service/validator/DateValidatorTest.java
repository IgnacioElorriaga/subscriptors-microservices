package com.adidas.subscription.service.validator;

import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.adidas.subscription.service.dto.SubscriptionRequest;
import com.adidas.subscription.service.exceptions.InvalidParamException;

@RunWith(MockitoJUnitRunner.class)
public class DateValidatorTest {
	
	private RequestValidator validator = new RequestValidator();

	@Before
	public void setUp() {
		initMocks(this);
	}
	@Test
	public void validateDateOk() {
		SubscriptionRequest request = SubscriptionRequest.builder().consent(Boolean.TRUE).dateOfBirth("1980-08-15")
				.email("abc@ad.com").firstName("User of JUnit").gender("female").newsletterId(1234).build();
		SubscriptionRequest validated = validator.validate(request);
		Assert.assertEquals("1980-08-15", validated.getDateOfBirth());

	}

	@Test(expected = InvalidParamException.class)
	public void validateFail() {
		SubscriptionRequest request = SubscriptionRequest.builder().consent(Boolean.TRUE).dateOfBirth("1980-02-31")
				.email("abc@ad.com").firstName("User of JUnit").gender("female").newsletterId(1234).build();
		validator.validate(request);

	}
	@Test(expected = InvalidParamException.class)
	public void validateFailNoDate() {
		SubscriptionRequest request = SubscriptionRequest.builder().consent(Boolean.TRUE).dateOfBirth(null)
				.email("abc@ad.com").firstName("User of JUnit").gender("female").newsletterId(1234).build();
		validator.validate(request);
	}
	@Test(expected = InvalidParamException.class)
	public void validateFailEmptyDate() {
		SubscriptionRequest request = SubscriptionRequest.builder().consent(Boolean.TRUE).dateOfBirth("")
				.email("abc@ad.com").firstName("User of JUnit").gender("female").newsletterId(1234).build();
		validator.validate(request);
	}

	@Test(expected = InvalidParamException.class)
	public void validateFailDifferntFormat() {
		SubscriptionRequest request = SubscriptionRequest.builder().consent(Boolean.TRUE).dateOfBirth("19800815")
				.email("abc@ad.com").firstName("User of JUnit").gender("female").newsletterId(1234).build();
		validator.validate(request);
	}
}

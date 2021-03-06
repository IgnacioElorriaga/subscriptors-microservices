package com.adidas.subscription.service.validator;

import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.adidas.subscription.service.dto.Gender;
import com.adidas.subscription.service.dto.SubscriptionRequest;
import com.adidas.subscription.service.exceptions.InvalidParamException;

@RunWith(MockitoJUnitRunner.class)
public class EmailValidatorTest {

	@InjectMocks
	private RequestValidator validator;
	@Before
	public void setUp() {
		initMocks(this);
	}

	@Test
	public void validateDateOk() {
		SubscriptionRequest request = SubscriptionRequest.builder().consent(Boolean.TRUE).dateOfBirth("1980-08-15")
				.email("abc@ad.com").firstName("User of JUnit").gender(Gender.FEMALE.name().toLowerCase())
				.newsletterId(1234).build();
		SubscriptionRequest validated = validator.validate(request);
		Assert.assertEquals("1980-08-15", validated.getDateOfBirth());
	}
	@Test
	public void validateOkTwoDomains() {
		SubscriptionRequest request = SubscriptionRequest.builder().consent(Boolean.TRUE).dateOfBirth("1980-08-15")
				.email("abc@ad.co.uk").firstName("User of JUnit").gender(Gender.FEMALE.name().toLowerCase())
				.newsletterId(1234).build();
		SubscriptionRequest validated = validator.validate(request);
		Assert.assertEquals("abc@ad.co.uk", validated.getEmail());
	}

	@Test(expected = InvalidParamException.class)
	public void validateFail() {
		SubscriptionRequest request = SubscriptionRequest.builder().consent(Boolean.TRUE).dateOfBirth("1980-08-15")
				.email("abcad.co.uk").firstName("User of JUnit").gender(Gender.FEMALE.name().toLowerCase())
				.newsletterId(1234).build();
		validator.validate(request);
	}
	
	@Test(expected = InvalidParamException.class)
	public void validateFailNoDomain() {
		SubscriptionRequest request = SubscriptionRequest.builder().consent(Boolean.TRUE).dateOfBirth("1980-08-15")
				.email("abc@adcom").firstName("User of JUnit").gender(Gender.OTHER.name().toLowerCase())
				.newsletterId(1234).build();
		validator.validate(request);
	}
	@Test(expected = InvalidParamException.class)
	public void validateFailNoUser() {
		SubscriptionRequest request = SubscriptionRequest.builder().consent(Boolean.TRUE).dateOfBirth("1980-08-15")
				.email("@adcom").firstName("User of JUnit").gender(Gender.MALE.name().toLowerCase()).newsletterId(1234)
				.build();
		validator.validate(request);
	}

	@Test(expected = InvalidParamException.class)
	public void validateFailEmpty() {
		SubscriptionRequest request = SubscriptionRequest.builder().consent(Boolean.TRUE).dateOfBirth("1980-08-15")
				.email("").firstName("User of JUnit").gender(Gender.FEMALE.name().toLowerCase()).newsletterId(1234)
				.build();
		validator.validate(request);
	}
	@Test(expected = InvalidParamException.class)
	public void validateFailNull() {
		SubscriptionRequest request = SubscriptionRequest.builder().consent(Boolean.TRUE).dateOfBirth("1980-08-15")
				.email(null).firstName("User of JUnit").gender(Gender.FEMALE.name().toLowerCase()).newsletterId(1234)
				.build();
		validator.validate(request);
	}
}

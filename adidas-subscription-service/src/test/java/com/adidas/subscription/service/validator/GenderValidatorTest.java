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
public class GenderValidatorTest {

	@InjectMocks
	private RequestValidator validator;

	@Before
	public void setUp() {
		initMocks(this);
	}

	@Test
	public void genderOkFemale() {
		SubscriptionRequest request = SubscriptionRequest.builder().consent(Boolean.TRUE).dateOfBirth("1980-08-15")
				.email("abc@ad.com").firstName("User of JUnit").gender(Gender.FEMALE.name().toLowerCase())
				.newsletterId(1234).build();
		SubscriptionRequest validated = validator.validate(request);
		Assert.assertEquals(request.getGender(), validated.getGender());
	}

	@Test
	public void genderOkMale() {
		SubscriptionRequest request = SubscriptionRequest.builder().consent(Boolean.TRUE).dateOfBirth("1980-08-15")
				.email("abc@ad.com").firstName("User of JUnit").gender(Gender.MALE.name().toLowerCase())
				.newsletterId(1234).build();
		SubscriptionRequest validated = validator.validate(request);
		Assert.assertEquals(request.getGender(), validated.getGender());
	}

	@Test
	public void genderOkOther() {
		SubscriptionRequest request = SubscriptionRequest.builder().consent(Boolean.TRUE).dateOfBirth("1980-08-15")
				.email("abc@ad.com").firstName("User of JUnit").gender(Gender.OTHER.name().toLowerCase())
				.newsletterId(1234).build();
		SubscriptionRequest validated = validator.validate(request);
		Assert.assertEquals(request.getGender(), validated.getGender());
	}

	@Test(expected = InvalidParamException.class)
	public void genderFailNoCorrespondency() {
		SubscriptionRequest request = SubscriptionRequest.builder().consent(Boolean.TRUE).dateOfBirth("1980-08-15")
				.email("abc@ad.com").firstName("User of JUnit").gender("Brother").newsletterId(1234).build();
		validator.validate(request);

	}

	@Test
	public void genderOkNull() {
		SubscriptionRequest request = SubscriptionRequest.builder().consent(Boolean.TRUE).dateOfBirth("1980-08-15")
				.email("abc@ad.com").firstName("User of JUnit").gender(null).newsletterId(1234).build();
		SubscriptionRequest validated = validator.validate(request);
		Assert.assertEquals(request.getGender(), validated.getGender());

	}

	@Test
	public void genderFailEmpty() {
		SubscriptionRequest request = SubscriptionRequest.builder().consent(Boolean.TRUE).dateOfBirth("1980-08-15")
				.email("abc@ad.com").firstName("User of JUnit").gender("").newsletterId(1234).build();
		SubscriptionRequest validated = validator.validate(request);
		Assert.assertEquals(request.getGender(), validated.getGender());
	}
}

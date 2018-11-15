package com.adidas.subscription.service.validator;

import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import com.adidas.subscription.service.dto.EmailParam;
import com.adidas.subscription.service.exceptions.InvalidParamException;

public class EmailValidatorTest {
	@InjectMocks
	private EmailValidator validator;
	@Before
	public void setUp() {
		initMocks(this);
	}
	@Test
	public void validateOk() {
		EmailParam value = validator.validate(EmailParam.builder().email("abc.ac@abc.com").build());
		Assert.assertEquals("abc.ac@abc.com", value.getEmail());
	}
	@Test
	public void validateOkTwoDomains() {
		EmailParam value = validator.validate(EmailParam.builder().email("abc.ac@abc.co.uk").build());
		Assert.assertEquals("abc.ac@abc.co.uk", value.getEmail());
	}

	@Test(expected = InvalidParamException.class)
	public void validateFail() {
		validator.validate(EmailParam.builder().email("abc.acabc.com").build());
	}
	
	@Test(expected = InvalidParamException.class)
	public void validateFailNoDomain() {
		validator.validate(EmailParam.builder().email("abc.ac@abccom").build());
	}
	@Test(expected = InvalidParamException.class)
	public void validateFailEmpty() {
		validator.validate(EmailParam.builder().email("").build());
	}
	@Test(expected = InvalidParamException.class)
	public void validateFailNull() {
		validator.validate(EmailParam.builder().build());
	}
}

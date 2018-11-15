package com.adidas.subscription.service.validator;

import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import com.adidas.subscription.service.dto.DateParam;
import com.adidas.subscription.service.exceptions.InvalidParamException;

public class DateValidatorTest {
	@InjectMocks
	private DateValidator validator;
	@Before
	public void setUp() {
		initMocks(this);
	}
	@Test
	public void validateOk() {
		DateParam value = validator.validate(DateParam.builder().date("2018-12-12").build());
		Assert.assertEquals("2018-12-12", value.getDate());
	}

	@Test(expected = InvalidParamException.class)
	public void validateFail() {
		validator.validate(DateParam.builder().date("2017-02-31").build());
	}
	@Test(expected = InvalidParamException.class)
	public void validateFailNoDate() {
		validator.validate(DateParam.builder().date(null).build());
	}
	@Test(expected = InvalidParamException.class)
	public void validateFailEmptyDate() {
		validator.validate(DateParam.builder().date("").build());
	}
}

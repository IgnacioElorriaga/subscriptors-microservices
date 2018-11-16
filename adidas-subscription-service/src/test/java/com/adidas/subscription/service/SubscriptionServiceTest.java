package com.adidas.subscription.service;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.*;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.adidas.subscription.service.dto.DateParam;
import com.adidas.subscription.service.dto.EmailParam;
import com.adidas.subscription.service.dto.Response;
import com.adidas.subscription.service.dto.Subscription;
import com.adidas.subscription.service.dto.SubscriptionRequest;
import com.adidas.subscription.service.exceptions.InvalidParamException;
import com.adidas.subscription.service.facade.SubscriptionDatabaseFacade;
import com.adidas.subscription.service.facade.SubscriptionEmailServiceFacade;
import com.adidas.subscription.service.facade.SubscriptionEventServiceFacade;
import com.adidas.subscription.service.validator.Validator;


public class SubscriptionServiceTest {

	@InjectMocks
	private SubscriptionService service;
	
	@Mock
	private SubscriptionEmailServiceFacade facadeEmail;
	@Mock
	private SubscriptionEventServiceFacade facadeEvent;
	@Mock
	private SubscriptionDatabaseFacade facadeDatabase;
	@Mock
	private Validator<EmailParam> emailAddressValidator;
	@Mock
	private Validator<DateParam> dateValidator;
	
	@Before
	public void setUp() {
		initMocks(this);
		Subscription defaultSubscription = Subscription.builder()
				.consent(Boolean.TRUE).dateOfBirth(LocalDate.now()).email("abc@ad.com").firstName("tester")
				.newsletterId(12)
				.gender("male")
				.subscriptionId(234)
				.build();
		when(facadeEmail.processEmail(any())).thenReturn(defaultSubscription);
		doNothing().when(facadeEvent).executeEvent(any());
		when(facadeDatabase.save(any(SubscriptionRequest.class))).thenReturn(defaultSubscription);
	}
	
	@Test
	public void createSubscriptionOk() {
		
		SubscriptionRequest request = SubscriptionRequest.builder()
				.consent(Boolean.TRUE).dateOfBirth(LocalDate.now().toString()).email("abc@ad.com").firstName("tester")
				.newsletterId(12)
				.gender("male")
		.build();
		when(emailAddressValidator.validate(any())).thenReturn(EmailParam.builder().email("abc@ad.com").build());
		when(dateValidator.validate(any())).thenReturn(DateParam.builder().date("2018-12-12").build());
		
		
		Response id = service.createSubscription(request);
		Assert.assertEquals(id.getSubscriptionId(), Integer.valueOf(12));
		verify(emailAddressValidator, times(1)).validate(any());
		verify(dateValidator, times(1)).validate(any());
		verify(facadeDatabase, times(1)).save(any(SubscriptionRequest.class));
		verify(facadeEmail, times(1)).processEmail(any());
		verify(facadeEvent, times(1)).executeEvent(any());
	}
	
	@Test(expected = InvalidParamException.class)
	public void createSubscriptionFailedEmail() {
		when(emailAddressValidator.validate(any())).thenThrow(new InvalidParamException("Email is not valid"));
		SubscriptionRequest request = SubscriptionRequest.builder()
				.consent(Boolean.TRUE).dateOfBirth(LocalDate.now().toString()).email("abcad.com").firstName("tester")
				.newsletterId(12)
				.gender("male")
		.build();
		service.createSubscription(request);
		
		verify(emailAddressValidator, times(1)).validate(any());
		verify(dateValidator, times(0)).validate(any());
		verify(facadeDatabase, times(0)).save(any(SubscriptionRequest.class));
		verify(facadeEmail, times(0)).processEmail(any());
		verify(facadeEvent, times(0)).executeEvent(any());
	}
	
	@Test(expected = InvalidParamException.class)
	public void createSubscriptionFailedDate() {
		when(emailAddressValidator.validate(any())).thenReturn(EmailParam.builder().email("abc@ad.com").build());
		when(dateValidator.validate(any())).thenThrow(new InvalidParamException("Date is not valid"));
		SubscriptionRequest request = SubscriptionRequest.builder()
				.consent(Boolean.TRUE).dateOfBirth(LocalDate.now().toString()).email("abcad.com").firstName("tester")
				.newsletterId(12)
				.gender("male")
		.build();
		service.createSubscription(request);
		
		verify(emailAddressValidator, times(1)).validate(any());
		verify(dateValidator, times(1)).validate(any());
		verify(facadeDatabase, times(0)).save(any(SubscriptionRequest.class));
		verify(facadeEmail, times(0)).processEmail(any());
		verify(facadeEvent, times(0)).executeEvent(any());
	}
}

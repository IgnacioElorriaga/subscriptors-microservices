package com.adidas.subscription.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.adidas.subscription.client.model.DatabaseRequest;
import com.adidas.subscription.client.model.EmailRequest;
import com.adidas.subscription.client.model.EventRequest;
import com.adidas.subscription.service.converter.SubscriptionRequestToDatabaseRequest;
import com.adidas.subscription.service.converter.SubscriptionRequestToEmailRequest;
import com.adidas.subscription.service.converter.SubscriptionToEventRequest;
import com.adidas.subscription.service.dto.Gender;
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
	private Validator requestValidator;
	

	@Mock private SubscriptionRequestToDatabaseRequest databaseConverter;
	@Mock private SubscriptionRequestToEmailRequest emailConverter;
	@Mock private SubscriptionToEventRequest eventConverter;
	@Before
	public void setup() {
		initMocks(this);
		Subscription defaultSubscription = Subscription.builder().consent(Boolean.TRUE)
				.dateOfBirth(LocalDate.of(1980, 8, 15)).email("abc@ad.com").firstName("tester").newsletterId(12)
				.gender(Gender.MALE).subscriptionId(234).build();
		doNothing().when(facadeEmail).processEmail(any());
		doNothing().when(facadeEvent).executeEvent(any());
		when(facadeDatabase.save(any(DatabaseRequest.class))).thenReturn(defaultSubscription);
	}

	@Test
	public void createSubscriptionOk() {

		SubscriptionRequest request = SubscriptionRequest.builder().consent(Boolean.TRUE)
				.dateOfBirth(LocalDate.of(1980, 8, 15).toString()).email("abc@ad.com").firstName("tester")
				.newsletterId(12).gender(Gender.lookup(Gender.MALE)).build();
		
		when(databaseConverter.convert(any(SubscriptionRequest.class))).thenReturn(DatabaseRequest.builder().consent(Boolean.TRUE)
				.dateOfBirth(LocalDate.of(1980, 8, 15).toString()).email("abc@ad.com").firstName("tester")
				.newsletterId(12).gender(Gender.lookup(Gender.MALE)).build());
		when(emailConverter.convert(any(SubscriptionRequest.class))).thenReturn(EmailRequest.builder().email(request.getEmail()).build());
		when(eventConverter.convert(any(Subscription.class))).thenReturn(EventRequest.builder().subscriptionId(234).build());
		
		when(requestValidator.validate(any(SubscriptionRequest.class))).thenReturn(request);

		Response id = service.createSubscription(request);

		Assert.assertEquals(id.getSubscriptionId(), Integer.valueOf(234));

		verify(facadeDatabase, times(1)).save(any(DatabaseRequest.class));
		verify(facadeEmail, times(1)).processEmail(EmailRequest.builder().email("abc@ad.com").build());
		verify(facadeEvent, times(1))
				.executeEvent(EventRequest.builder().subscriptionId(id.getSubscriptionId()).build());
	}
	
	@Test
	public void createSubscriptionOkMandatory() {

		SubscriptionRequest request = SubscriptionRequest.builder()
				.consent(Boolean.TRUE)
				.dateOfBirth(LocalDate.of(1980, 8, 15).toString())
				.email("abc@ad.com")
				.newsletterId(12)
				.build();
		
		when(databaseConverter.convert(any(SubscriptionRequest.class))).thenReturn(DatabaseRequest.builder()
				.consent(Boolean.TRUE)
				.dateOfBirth(LocalDate.of(1980, 8, 15).toString())
				.email("abc@ad.com")
				.firstName("")
				.newsletterId(12)
				.gender(Gender.lookup(Gender.OTHER))
				.build());
		when(emailConverter.convert(any(SubscriptionRequest.class))).thenReturn(EmailRequest.builder().email(request.getEmail()).build());
		when(eventConverter.convert(any(Subscription.class))).thenReturn(EventRequest.builder().subscriptionId(234).build());
		
		when(requestValidator.validate(any(SubscriptionRequest.class))).thenReturn(request);

		Response id = service.createSubscription(request);

		Assert.assertEquals(id.getSubscriptionId(), Integer.valueOf(234));

		verify(facadeDatabase, times(1)).save(any(DatabaseRequest.class));
		verify(facadeEmail, times(1)).processEmail(EmailRequest.builder().email("abc@ad.com").build());
		verify(facadeEvent, times(1))
				.executeEvent(EventRequest.builder().subscriptionId(id.getSubscriptionId()).build());
	}

	@Test(expected = InvalidParamException.class)
	public void createSubscriptionFailedEmail() {
		when(requestValidator.validate(any()))
				.thenThrow(new InvalidParamException("The format of the email is not correct."));

		SubscriptionRequest request = SubscriptionRequest.builder()
				.consent(Boolean.TRUE)
				.dateOfBirth(LocalDate.now().toString())
				.email("abcad.com")
				.firstName("tester")
				.newsletterId(12)
				.gender(Gender.lookup(Gender.MALE)).build();
		service.createSubscription(request);

	}

	@Test(expected = InvalidParamException.class)
	public void createSubscriptionFailedDate() {

		when(requestValidator.validate(any()))
				.thenThrow(new InvalidParamException("The format of the date is not valid. It must be yyyy-MM-dd"));
		SubscriptionRequest request = SubscriptionRequest.builder()
				.consent(Boolean.TRUE)
				.dateOfBirth(null)
				.email("abc@ad.com")
				.firstName("tester")
				.newsletterId(12)
				.gender(Gender.lookup(Gender.MALE)).build();
		service.createSubscription(request);

	}
}

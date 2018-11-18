package com.adidas.database.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.time.LocalDate;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.adidas.database.repository.SubscriptionRepository;
import com.adidas.database.service.converter.SubscriptionRequestToSubscriptionConverter;
import com.adidas.database.service.model.Subscription;
import com.adidas.database.service.model.SubscriptionRequest;

public class DatabaseServiceTest {

	@InjectMocks
	private DatabaseService service;

	@Mock
	private SubscriptionRequestToSubscriptionConverter converter;
	
	@Mock
	private SubscriptionRepository repo;

	@Before
	public void setup() {
		initMocks(this);
	}

	@Test
	public void createOk() {
		SubscriptionRequest request = SubscriptionRequest.builder()
				.consent(Boolean.TRUE)
				.dateOfBirth(LocalDate.now())
				.email("abc@abc.com")
				.firstName("test")
				.gender("female")
				.newsletterId(123)
				.build();
		Subscription database = Subscription.builder()
				.consent(Boolean.TRUE)
				.dateOfBirth(LocalDate.now())
				.email("abc@abc.com")
				.firstName("test")
				.gender("female")
				.newsletterId(123)
				.build();
		
		when(repo.findByEmail(anyString())).thenReturn(Arrays.asList());
		
		when(repo.save(any())).thenReturn(Subscription.builder().consent(Boolean.TRUE).dateOfBirth(LocalDate.now())
				.email("abc@abc.com").firstName("test").gender("female").newsletterId(123).subscriptionId(444).build());
		
		when(converter.convert(any(SubscriptionRequest.class))).thenReturn(database);
		
		Integer id = service.create(request);

		assertEquals(Integer.valueOf(444), id);
		verify(repo, times(1)).save(any());

		verify(repo, times(1)).findByEmail(any());
	}
	
	@Test
	public void createOkMandatory() {
		SubscriptionRequest request = SubscriptionRequest.builder()
				.consent(Boolean.TRUE)
				.dateOfBirth(LocalDate.now())
				.email("abc@abc.com")
				.newsletterId(123)
				.build();
		Subscription database = Subscription.builder()
				.consent(Boolean.TRUE)
				.dateOfBirth(LocalDate.now())
				.email("abc@abc.com")
				.firstName("")
				.gender("")
				.newsletterId(123)
				.build();
		when(repo.findByEmail(anyString())).thenReturn(Arrays.asList());
		
		when(repo.save(any())).thenReturn(Subscription.builder()
				.consent(Boolean.TRUE)
				.dateOfBirth(LocalDate.now())
				.email("abc@abc.com")
				.firstName("")
				.gender("")
				.newsletterId(123)
				.subscriptionId(444)
				.build());
		
		when(converter.convert(any(SubscriptionRequest.class))).thenReturn(database);
		
		Integer id = service.create(request);

		assertEquals(Integer.valueOf(444), id);
		verify(repo, times(1)).save(any());

		verify(repo, times(1)).findByEmail(any());
	}
	

	@Test
	public void createOkExistingUser() {
		SubscriptionRequest request = SubscriptionRequest.builder()
				.consent(Boolean.TRUE)
				.dateOfBirth(LocalDate.now())
				.email("abc@abc.com")
				.firstName("test")
				.gender("female")
				.newsletterId(123)
				.build();
		
		Subscription database = Subscription.builder()
				.consent(Boolean.TRUE)
				.dateOfBirth(LocalDate.now())
				.email("abc@abc.com")
				.firstName("test")
				.gender("female")
				.newsletterId(123)
				.subscriptionId(444)
				.build();
		
		when(repo.findByEmail(anyString())).thenReturn(Arrays.asList(database));
		
		when(converter.convert(any(SubscriptionRequest.class))).thenReturn(database);
		
		Integer id = service.create(request);

		assertEquals(Integer.valueOf(444), id);
		verify(repo, times(0)).save(any());

		verify(repo, times(1)).findByEmail(any());
	}
}

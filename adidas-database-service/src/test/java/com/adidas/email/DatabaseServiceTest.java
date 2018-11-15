package com.adidas.email;

import static junit.framework.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.adidas.database.repository.SubscriptionRepository;
import com.adidas.database.service.DatabaseService;
import com.adidas.database.service.model.Subscription;
import com.adidas.database.service.model.SubscriptionRequest;

public class DatabaseServiceTest {

	@InjectMocks
	private DatabaseService service;

	@Mock
	private SubscriptionRepository repo;

	@Before
	public void setUp() {
		initMocks(this);
	}

	@Test
	public void createOk() {
		when(repo.findByEmail(anyString())).thenReturn(null);
		when(repo.save(any())).thenReturn(Subscription.builder().consent(Boolean.TRUE).dateOfBirth(LocalDate.now())
				.email("abc@abc.com").firstName("test").gender("female").newsletterId(123).subscriptionId(444).build());

		Integer id = service.create(SubscriptionRequest.builder().consent(Boolean.TRUE).dateOfBirth(LocalDate.now())
				.email("abc@abc.com").firstName("test").gender("female").newsletterId(123).build());

		assertEquals(Integer.valueOf(444), id);
		verify(repo, times(1)).save(any());

		verify(repo, times(1)).findByEmail(any());
	}

	@Test
	public void createOkExistingUser() {
		when(repo.findByEmail(anyString())).thenReturn(
				Subscription.builder().consent(Boolean.TRUE).dateOfBirth(LocalDate.now()).email("abc@abc.com")
						.firstName("test").gender("female").newsletterId(123).subscriptionId(444).build());
		when(repo.save(any())).thenReturn(Subscription.builder().consent(Boolean.TRUE).dateOfBirth(LocalDate.now())
				.email("abc@abc.com").firstName("test").gender("female").newsletterId(123).subscriptionId(444).build());

		Integer id = service.create(SubscriptionRequest.builder().consent(Boolean.TRUE).dateOfBirth(LocalDate.now())
				.email("abc@abc.com").firstName("test").gender("female").newsletterId(123).build());

		assertEquals(Integer.valueOf(444), id);
		verify(repo, times(0)).save(any());

		verify(repo, times(1)).findByEmail(any());
	}
}

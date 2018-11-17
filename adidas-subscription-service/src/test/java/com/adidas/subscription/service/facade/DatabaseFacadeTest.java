package com.adidas.subscription.service.facade;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.adidas.subscription.client.DatabaseServiceFeignClient;
import com.adidas.subscription.client.model.DatabaseRequest;
import com.adidas.subscription.client.model.DatabaseResponse;
import com.adidas.subscription.service.converter.DatabaseRequestToSubscriptionConverter;
import com.adidas.subscription.service.dto.Gender;
import com.adidas.subscription.service.dto.Subscription;
import com.adidas.subscription.service.exceptions.MicroserviceException;
import com.netflix.hystrix.exception.HystrixRuntimeException;

@RunWith(MockitoJUnitRunner.class)
public class DatabaseFacadeTest {

	@InjectMocks
	private SubscriptionDatabaseFacadeFeignClient facade;

	@Mock
	private DatabaseServiceFeignClient client;
	@Mock
	private DatabaseRequestToSubscriptionConverter converter;
	@Mock
	private HystrixRuntimeException hystrixRuntimeException;

	
	@Before
	public void setup() {
		initMocks(this);
	}
	@Test
	public void saveOk() {
		when(client.createSubscription(any(DatabaseRequest.class))).thenReturn(DatabaseResponse.builder().subscriptionId(987).build());
		Subscription responseMocked = Subscription.builder().consent(Boolean.TRUE)
				.dateOfBirth(LocalDate.of(1980, 8, 15)).email("abc@ad.com").firstName("tester").newsletterId(12)
				.gender(Gender.OTHER).build();
		
		when(converter.convert(any(DatabaseRequest.class))).thenReturn(responseMocked);
		
		Subscription saved = facade.save(DatabaseRequest.builder().consent(Boolean.TRUE)
				.dateOfBirth(LocalDate.of(1980, 8, 15).toString()).email("abc@ad.com").firstName("tester").newsletterId(12)
				.gender(Gender.lookup(Gender.OTHER)).build());
		Assert.assertEquals(responseMocked, saved);
	}
	
	@Test(expected = MicroserviceException.class)
	public void saveFail() {
		when(client.createSubscription(any(DatabaseRequest.class))).thenThrow(hystrixRuntimeException);
		
		facade.save(DatabaseRequest.builder().consent(Boolean.TRUE)
				.dateOfBirth(LocalDate.of(1980, 8, 15).toString()).email("abc@ad.com").firstName("tester").newsletterId(12)
				.gender(Gender.lookup(Gender.OTHER)).build());
	}
	
}

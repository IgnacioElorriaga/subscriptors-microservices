package com.adidas.subscription.service.facade;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.adidas.subscription.client.EventServiceFeignClient;
import com.adidas.subscription.client.model.EventRequest;
import com.adidas.subscription.client.model.EventResponse;
import com.adidas.subscription.service.exceptions.MicroserviceException;
import com.netflix.hystrix.exception.HystrixRuntimeException;

@RunWith(MockitoJUnitRunner.class)
public class EventFacadeTest {

	@InjectMocks
	private SubscriptionEventServiceFacadeFeignImpl facade;
	
	@Mock
	private EventServiceFeignClient client;
	
	@Mock
	private HystrixRuntimeException hystrixRuntimeException;

	
	@Before
	public void setup() {
		initMocks(this);
	}
	
	@Test
	public void saveOk() {
		when(client.triggerSubscription(any(EventRequest.class))).thenReturn(EventResponse.builder().answer("Ping OK").build());
		
		facade.executeEvent(EventRequest.builder().subscriptionId(748922).build());
		
	}
	
	@Test(expected = MicroserviceException.class)
	public void saveFail() {
		when(client.triggerSubscription(any(EventRequest.class))).thenThrow(hystrixRuntimeException);
	
		facade.executeEvent(EventRequest.builder().subscriptionId(748922).build());
		
	}
}

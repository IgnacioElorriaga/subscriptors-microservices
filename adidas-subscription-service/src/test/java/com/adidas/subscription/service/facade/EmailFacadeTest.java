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

import com.adidas.subscription.client.EmailServiceFeignClient;
import com.adidas.subscription.client.model.EmailRequest;
import com.adidas.subscription.service.exceptions.MicroserviceException;
import com.netflix.hystrix.exception.HystrixRuntimeException;

@RunWith(MockitoJUnitRunner.class)
public class EmailFacadeTest {
	@InjectMocks
	private SubscriptionEmailServiceFacadeFeignImpl facade;

	@Mock
	private EmailServiceFeignClient client;
	@Mock
	private HystrixRuntimeException hystrixRuntimeException;

	@Before
	public void setup() {
		initMocks(this);
	}

	@Test
	public void save() {
		when(client.checkEmail(any())).thenReturn(Boolean.TRUE);
		facade.processEmail(EmailRequest.builder().email("abc@ad.com").build());
	}

	@Test(expected = MicroserviceException.class)
	public void saveFail() {
		when(client.checkEmail(any())).thenThrow(hystrixRuntimeException);
	
		facade.processEmail(EmailRequest.builder().email("abc@ad.com").build());
	}
}

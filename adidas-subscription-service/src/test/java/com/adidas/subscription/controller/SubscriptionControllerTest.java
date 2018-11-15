package com.adidas.subscription.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.adidas.subscription.client.DatabaseServiceFeignClient;
import com.adidas.subscription.client.EmailServiceFeignClient;
import com.adidas.subscription.client.EventServiceFeignClient;
import com.adidas.subscription.service.dto.Subscription;
import com.netflix.hystrix.Hystrix;
import com.netflix.hystrix.HystrixCircuitBreaker;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandMetrics;
import com.netflix.hystrix.exception.HystrixRuntimeException;

@RunWith(SpringRunner.class)
//@ContextConfiguration(classes = { SubscriptionApplication.class })
//@WebAppConfiguration
@SpringBootTest(properties = { "eureka.client.enabled:false", "env:local", "spring.profiles.active:test", "feign.hystrix.enabled:false" })
public class SubscriptionControllerTest {
	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;
	@MockBean
	private DatabaseServiceFeignClient clientDatabase;
	@MockBean
	private EmailServiceFeignClient clientEmail;
	@MockBean
	private EventServiceFeignClient clientEvent;
	@MockBean
	private HystrixRuntimeException hystrixRuntimeException;
	
	@Before
	public void setup() throws Exception {
//		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		
	}

	@Test
	public void create() throws Exception {
		when(clientDatabase.createSubscription(any())).thenReturn(Subscription.builder().subscriptionId(23).build());

		when(clientEmail.checkEmail(any())).thenReturn(Boolean.TRUE);

		doNothing().when(clientEvent).triggerSubscription(any());
	
	        Hystrix.reset();
		mockMvc.perform(post("/subscriptions").contentType(APPLICATION_JSON)
				.content("{\"email\":\"abc@ad.com\", \n" + "\"firstName\":\"Unity\", \n" + "\"gender\":\"male\", \n"
						+ "\"dateOfBirth\":\"2018-09-01\", \n" + "\"consent\":\"true\", \n"
						+ "\"newsletterId\":\"12345\" }"))
				.andExpect(status().isOk()).andExpect(jsonPath("$").value(23));
	}
	 public static HystrixCircuitBreaker getCircuitBreaker() {
	        return HystrixCircuitBreaker.Factory.getInstance(Hystrix.getCurrentThreadExecutingCommand());
	    }

	    private static HystrixCommandKey getCommandKey() {
	        return HystrixCommandKey.Factory.asKey("save");
	    }
}

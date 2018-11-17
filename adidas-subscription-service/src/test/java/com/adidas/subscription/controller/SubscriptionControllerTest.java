package com.adidas.subscription.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.adidas.subscription.client.DatabaseServiceFeignClient;
import com.adidas.subscription.client.EmailServiceFeignClient;
import com.adidas.subscription.client.EventServiceFeignClient;
import com.adidas.subscription.client.model.DatabaseResponse;
import com.adidas.subscription.client.model.EventResponse;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(properties = { "eureka.client.enabled:false", "spring.profiles.active:test",
		"feign.hystrix.enabled:false" })
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

	@Before
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();

	}

	@Test
	public void createOk() throws Exception {
		when(clientDatabase.createSubscription(any()))
				.thenReturn(DatabaseResponse.builder().subscriptionId(23).build());

		when(clientEmail.checkEmail(any())).thenReturn(Boolean.TRUE);

		when(clientEvent.triggerSubscription(any())).thenReturn(EventResponse.builder().answer("Ping OK").build());

		mockMvc.perform(post("/subscriptions").contentType(APPLICATION_JSON)
				.content("{\"email\":\"abc@ad.com\", \n" + "\"firstName\":\"Unity\", \n" + "\"gender\":\"male\", \n"
						+ "\"dateOfBirth\":\"2018-09-01\", \n" + "\"consent\":\"true\", \n"
						+ "\"newsletterId\":\"12345\" }"))
				.andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.subscriptionId").value(23));
	}

	@Test
	public void createFailBadEmail() throws Exception {

		mockMvc.perform(post("/subscriptions").contentType(APPLICATION_JSON)
				.content("{\"email\":\"abcad.com\", \n" + "\"firstName\":\"Unity\", \n" + "\"gender\":\"male\", \n"
						+ "\"dateOfBirth\":\"2018-09-01\", \n" + "\"consent\":\"true\", \n"
						+ "\"newsletterId\":\"12345\" }"))
				.andDo(print()).andExpect(status().isBadRequest());//.andExpect(jsonPath("$.subscriptionId").value(23));
	}
	
	@Test
	public void createFailBadGender() throws Exception {

		mockMvc.perform(post("/subscriptions").contentType(APPLICATION_JSON)
				.content("{\"email\":\"ab@cad.com\", \n" + "\"firstName\":\"Unity\", \n" + "\"gender\":\"no-male\", \n"
						+ "\"dateOfBirth\":\"2018-09-01\", \n" + "\"consent\":\"true\", \n"
						+ "\"newsletterId\":\"12345\" }"))
				.andDo(print()).andExpect(status().isBadRequest());//.andExpect(jsonPath("$.subscriptionId").value(23));
	}
	
	@Test
	public void createFailBadDate() throws Exception {

		mockMvc.perform(post("/subscriptions").contentType(APPLICATION_JSON)
				.content("{\"email\":\"ab@cad.com\", \n" + "\"firstName\":\"Unity\", \n" + "\"gender\":\"male\", \n"
						+ "\"dateOfBirth\":\"1-09-01\", \n" + "\"consent\":\"true\", \n"
						+ "\"newsletterId\":\"12345\" }"))
				.andDo(print()).andExpect(status().isBadRequest());//.andExpect(jsonPath("$.subscriptionId").value(23));
	}

}

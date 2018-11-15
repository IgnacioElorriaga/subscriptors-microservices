package com.adidas.subscription.client;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.adidas.subscription.client.model.SubscriptionRequest;

@FeignClient(name = "adidas-event-service")
public interface EventServiceFeignClient {

	 @RequestMapping(value = "/events", 
			 method = POST, 
			 consumes = APPLICATION_JSON_UTF8_VALUE, 
			 produces = APPLICATION_JSON_UTF8_VALUE)
	  void triggerSubscription(@RequestBody SubscriptionRequest body);
}

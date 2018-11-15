package com.adidas.subscription.client;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.adidas.subscription.client.model.SubscriptionRequest;
import com.adidas.subscription.service.dto.Subscription;

@FeignClient(name = "adidas-database-service")
public interface DatabaseServiceFeignClient {

	@RequestMapping(value = "/database/subscriptions", 
			 method = POST, 
			 
			 consumes = APPLICATION_JSON_UTF8_VALUE, 
			 produces = APPLICATION_JSON_UTF8_VALUE)
	  @ResponseBody Subscription createSubscription(@RequestBody SubscriptionRequest body);
}

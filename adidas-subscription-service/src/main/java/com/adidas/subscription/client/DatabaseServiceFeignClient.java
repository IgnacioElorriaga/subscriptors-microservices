package com.adidas.subscription.client;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.adidas.subscription.client.model.SubscriptionRequest;
import com.adidas.subscription.service.dto.Subscription;

/**
 * Feign client to connect to the database. It will execute the indicated path,
 * looking for the microservice named as it is in the annotation
 * "adidas-database-service". Configuration is especified in the application.yml file.
 * 
 * @author nacho
 *
 */
@FeignClient(name = "adidas-database-service")
public interface DatabaseServiceFeignClient {
	
	/**
	 * Calls to that path on the specified method (POST).<br/>
	 * It uses the UTF8 in case there is any special character.
	 * 
	 * @param body with the info to be stored.
	 * @return the id generated. 
	 */
	@RequestMapping(value = "/database/subscriptions", 
			 method = POST, 
			 
			 consumes = APPLICATION_JSON_UTF8_VALUE, 
			 produces = APPLICATION_JSON_UTF8_VALUE)
	  @ResponseBody Subscription createSubscription(@RequestBody SubscriptionRequest body);
}

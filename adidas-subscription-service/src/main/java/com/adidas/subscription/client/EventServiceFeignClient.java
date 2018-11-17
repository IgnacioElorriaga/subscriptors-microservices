package com.adidas.subscription.client;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.validation.constraints.NotNull;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.adidas.subscription.client.model.EventRequest;
import com.adidas.subscription.client.model.EventResponse;

/**
 * Feign client to connect to the events. 
 * 
 * It will execute the indicated path,
 * looking for the microservice named as it is in the annotation
 * "adidas-event-service". Configuration is especified in the application.yml file.
 * 
 * @author nacho
 *
 */
@FeignClient(name = "adidas-event-service")
public interface EventServiceFeignClient {

	/**
	 * Executes that path and return the info processed in the micro.
	 * 
	 * @param body with the information to be executed
	 */
	 @RequestMapping(value = "/events", 
			 method = POST, 
			 consumes = APPLICATION_JSON_UTF8_VALUE, 
			 produces = APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	EventResponse triggerSubscription(@RequestBody @NotNull final EventRequest body);
}

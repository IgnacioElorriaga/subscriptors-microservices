package com.adidas.subscription.client;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.adidas.subscription.client.model.Email;

/**
 * Feign client to connect to the emails. It will execute the indicated path,
 * looking for the microservice named as it is in the annotation
 * "adidas-email-service". Configuration is especified in the application.yml
 * file.
 * 
 * @author nacho
 *
 */
@FeignClient(name = "adidas-email-service")
public interface EmailServiceFeignClient {

	/**
	 * Executes that path and return the info processed in the micro.
	 * 
	 * @param body
	 *            with the information to be executed
	 * @return true if it was fine the process or not.
	 */
	@RequestMapping(value = "/emails", method = POST,

			consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
	Boolean checkEmail(@RequestBody Email body);
}

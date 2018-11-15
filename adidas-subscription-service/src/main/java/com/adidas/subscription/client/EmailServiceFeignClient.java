package com.adidas.subscription.client;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.adidas.subscription.client.model.Email;

@FeignClient(name = "adidas-email-service")
public interface EmailServiceFeignClient {

	 @RequestMapping(value = "/emails", 
			 method = POST, 
			 consumes = APPLICATION_JSON_UTF8_VALUE, 
			 produces = APPLICATION_JSON_UTF8_VALUE)
	  Boolean checkEmail(@RequestBody Email body);
}

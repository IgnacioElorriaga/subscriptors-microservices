package com.adidas.subscription.service.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adidas.subscription.client.EmailServiceFeignClient;
import com.adidas.subscription.client.model.Email;
import com.adidas.subscription.client.model.EmailRequest;
import com.adidas.subscription.service.exceptions.MicroserviceException;
import com.adidas.subscription.service.exceptions.UnknownException;
import com.netflix.hystrix.exception.HystrixRuntimeException;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@AllArgsConstructor
public class SubscriptionEmailServiceFacadeFeignImpl implements SubscriptionEmailServiceFacade {

	@Autowired
	private EmailServiceFeignClient emailFeignClient;


	// @HystrixCommand(fallbackMethod = "fallbackProcessEmail",
	// commandKey="processEmail")
	@Override
	public void processEmail(EmailRequest request) {

		try {
			log.info("Processing request to email Service");
			emailFeignClient.checkEmail(Email.builder().email(request.getEmail()).build());
			
		} catch (HystrixRuntimeException e) {
			throw new MicroserviceException(e.getCause());
		} catch (Exception e) {
			throw new UnknownException(e);
		}
	}

//	public Subscription fallbackProcessEmail(DatabaseRequest request) {
//		log.error("Hystrix circuit opened for email service. Retruning default object");
//		return CommonsHystrix.fallbackDefaultSubscription();
//	}

}

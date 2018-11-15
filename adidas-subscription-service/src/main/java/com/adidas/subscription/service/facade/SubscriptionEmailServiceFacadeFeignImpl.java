package com.adidas.subscription.service.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adidas.subscription.client.EmailServiceFeignClient;
import com.adidas.subscription.client.model.Email;
import com.adidas.subscription.client.model.SubscriptionRequest;
import com.adidas.subscription.service.converter.SubscriptionRequestToSubscriptionConverter;
import com.adidas.subscription.service.dto.Subscription;
import com.adidas.subscription.service.exceptions.UnknownException;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.exception.HystrixRuntimeException;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SubscriptionEmailServiceFacadeFeignImpl implements SubscriptionEmailServiceFacade {
	@Autowired
	private SubscriptionRequestToSubscriptionConverter converter;
	@Autowired
	private EmailServiceFeignClient emailFeignClient;

	@Autowired
	public SubscriptionEmailServiceFacadeFeignImpl(SubscriptionRequestToSubscriptionConverter converter,
			EmailServiceFeignClient emailFeignClient) {
		this.converter = converter;
		this.emailFeignClient = emailFeignClient;
	}

	// @HystrixCommand(fallbackMethod = "fallbackProcessEmail",
	// commandKey="processEmail")
	@Override
	public Subscription processEmail(SubscriptionRequest request) {

		try {
			log.info("Processing request to email Service");
			emailFeignClient.checkEmail(Email.builder().email(request.getEmail()).build());

			return converter.convert(request);
		} catch (HystrixRuntimeException e) {
			throw new UnknownException(e.getCause());
		} catch (Exception e) {
			throw new UnknownException(e);
		}
	}

	public Subscription fallbackProcessEmail(SubscriptionRequest request) {
		log.error("Hystrix circuit opened for email service. Retruning default object");
		return CommonsHystrix.fallbackDefaultSubscription();
	}

}

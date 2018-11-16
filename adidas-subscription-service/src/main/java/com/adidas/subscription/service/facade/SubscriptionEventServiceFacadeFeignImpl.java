package com.adidas.subscription.service.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adidas.subscription.client.EventServiceFeignClient;
import com.adidas.subscription.service.dto.SubscriptionRequest;
import com.adidas.subscription.service.exceptions.MicroserviceException;
import com.adidas.subscription.service.exceptions.UnknownException;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.exception.HystrixRuntimeException;

import lombok.extern.slf4j.Slf4j;
@Component
@Slf4j
public class SubscriptionEventServiceFacadeFeignImpl implements SubscriptionEventServiceFacade {
	@Autowired
	private EventServiceFeignClient client;
	
	//@HystrixCommand(fallbackMethod = "fallbackExecuteEvent", commandKey = "executeEvent")
	@Override
	public void executeEvent(SubscriptionRequest request) {
		try {
			log.info("Processing request to event service");
			log.debug("Request to process {}", request);
//			client.triggerSubscription(request);
			log.info("Subscription executed");
			
		}catch(HystrixRuntimeException e) {
			throw new MicroserviceException(e);
		} catch(Exception e) {
			throw new UnknownException(e);
		}

	}

	public void fallbackExecuteEvent(SubscriptionRequest request) {
		log.error("Hystrix circuit opened for event service. Doing nothing");
	}
}

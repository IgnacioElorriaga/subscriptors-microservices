package com.adidas.subscription.service.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adidas.subscription.client.EventServiceFeignClient;
import com.adidas.subscription.client.model.EventRequest;
import com.adidas.subscription.client.model.EventResponse;
import com.adidas.subscription.service.exceptions.MicroserviceException;
import com.adidas.subscription.service.exceptions.UnknownException;
import com.netflix.hystrix.exception.HystrixRuntimeException;

import lombok.extern.slf4j.Slf4j;
@Component
@Slf4j
public class SubscriptionEventServiceFacadeFeignImpl implements SubscriptionEventServiceFacade {
	private EventServiceFeignClient client;
	
	@Autowired
	public SubscriptionEventServiceFacadeFeignImpl(EventServiceFeignClient client) {
		this.client = client;
	}
	
	//@HystrixCommand(fallbackMethod = "fallbackExecuteEvent", commandKey = "executeEvent")
	@Override
	public void executeEvent(EventRequest request) {
		try {
			log.info("Processing request to event service");
			log.debug("Request to process {}", request);
			
			EventResponse response = client.triggerSubscription("Basic YWRpZGFzLWRhdGFiYXNlLXNlcnZpY2U6MTIzNDU2",
					request);
			
			log.info("Subscription executed. Response received: {}", response.getAnswer());
			
		}catch(HystrixRuntimeException e) {
			throw new MicroserviceException(e);
		} catch(Exception e) {
			throw new UnknownException(e);
		}

	}

	public void fallbackExecuteEvent(EventRequest request) {
		log.error("Hystrix circuit opened for event service. Doing nothing");
	}
}

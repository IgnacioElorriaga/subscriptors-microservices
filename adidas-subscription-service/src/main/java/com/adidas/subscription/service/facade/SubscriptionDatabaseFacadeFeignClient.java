package com.adidas.subscription.service.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adidas.subscription.client.DatabaseServiceFeignClient;
import com.adidas.subscription.service.converter.SubscriptionRequestToSubscriptionConverter;
import com.adidas.subscription.service.dto.Subscription;
import com.adidas.subscription.service.dto.SubscriptionRequest;
import com.adidas.subscription.service.exceptions.MicroserviceException;
import com.adidas.subscription.service.exceptions.UnknownException;
import com.netflix.hystrix.exception.HystrixRuntimeException;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SubscriptionDatabaseFacadeFeignClient implements SubscriptionDatabaseFacade {
	@Autowired
	private DatabaseServiceFeignClient client;
	@Autowired
	private SubscriptionRequestToSubscriptionConverter converter;

	@Override
	public Subscription save(SubscriptionRequest obj) {
		try {
			log.info("Processing request to the database for newsletter {}", obj.getNewsletterId());
			log.debug("Object to send: {}", obj);

			Integer id = executeFeign(obj);
			Subscription subscription = converter.convert(obj);
			subscription.setSubscriptionId(id);
			log.info("Retrieved from database the id {}", id);
			return subscription;
		} catch (HystrixRuntimeException e) {
			throw new MicroserviceException(e);
		} catch (Exception e) {
			throw new UnknownException(e);
		}

	}

//	@HystrixCommand(fallbackMethod = "fallbackSave")
	private Integer executeFeign(SubscriptionRequest obj) {
		return client.createSubscription(obj).getSubscriptionId();
	}

//	public Subscription fallbackSave(SubscriptionRequest obj) {
//		log.error("Hystrix circuit opened for database service. Retruning default object");
//		return CommonsHystrix.fallbackDefaultSubscription();
//	}

}

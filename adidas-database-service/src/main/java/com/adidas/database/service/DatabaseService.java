package com.adidas.database.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adidas.database.repository.SubscriptionRepository;
import com.adidas.database.service.converter.SubscriptionRequestToSubscriptionConverter;
import com.adidas.database.service.model.Subscription;
import com.adidas.database.service.model.SubscriptionRequest;

import lombok.extern.slf4j.Slf4j;

/**
 * Service for the database. <br/>
 * It transform the Bean from the request domain to the JPA domain and then
 * executes a double operation. <br/>
 * Firstly, it checks in the database if the user already exists for that newsletter and if not, 
 * it will store the information in it. Otherwise, it returns the stored <i>subscriptionId</i> field.
 * 
 * @author nacho
 *
 */
@Component
@Slf4j
public class DatabaseService {

	private SubscriptionRepository repo;
	private final SubscriptionRequestToSubscriptionConverter converter;

	@Autowired
	public DatabaseService(SubscriptionRepository repo, final SubscriptionRequestToSubscriptionConverter converter) {
		this.repo = repo;
		this.converter = converter;
	}
	/**
	 * Main method in charge of storing the information in the database.<br/>
	 * It has to transform the object to the database format and then checks if the user is 
	 * already inside (with the email and newsletterId as values). Finally, it will store the 
	 * user in case it doesn't exist.
	 * 
	 * @param request with the values to store
	 * @return the ID generated.
	 */
	public Integer create(SubscriptionRequest request) {

		Subscription source = convert(request);

		log.debug("Requested: {}", source);
		log.info("Service is about to store data");
		Subscription saved;
		List<Subscription> search = repo.findByEmail(source.getEmail()).stream()
				.filter(p -> p.getNewsletterId().equals(source.getNewsletterId()))
				.collect(Collectors.toList());

		if (search == null || search.size() == 0) {
			saved = repo.save(source);
		} else {
			// must be one
			saved = search.get(0);
		}

		log.info("Saved {}", saved);

		return saved.getSubscriptionId();
	}

	private Subscription convert(SubscriptionRequest subscription) {
		return converter.convert(subscription);
	}

}

package com.adidas.database.service;

import java.util.Optional;

import org.hibernate.query.criteria.internal.CriteriaSubqueryImpl.SubquerySelection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adidas.database.repository.SubscriptionRepository;
import com.adidas.database.service.model.Subscription;
import com.adidas.database.service.model.SubscriptionRequest;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DatabaseService {
	
	
	private SubscriptionRepository repo;

	@Autowired
	public DatabaseService(SubscriptionRepository repo) {
		this.repo = repo;
	}

	public Integer create(SubscriptionRequest subscription) {
		
		Subscription obj = convert(subscription);

		log.debug("Requested: {}", obj);
		log.info("Service is about to store data");
		Integer saved = Optional.ofNullable(repo.findByEmail(obj.getEmail().toLowerCase()))
				.filter(o -> o.getNewsletterId().equals(obj.getNewsletterId()))
				.map(o -> o.getSubscriptionId())
		.orElseGet(() -> repo.save(obj).getSubscriptionId());

		log.info("Saved {}", saved);
	
		return saved;
	}
	
	private Subscription convert(SubscriptionRequest subscription) {
		return Subscription.builder()
				.consent(subscription.getConsent() == null ? Boolean.FALSE: subscription.getConsent())
				.dateOfBirth(subscription.getDateOfBirth())
				.email(subscription.getEmail().toLowerCase())
				.firstName(subscription.getFirstName())
				.gender(subscription.getGender())
				.newsletterId(subscription.getNewsletterId())
				.build();
	}

}

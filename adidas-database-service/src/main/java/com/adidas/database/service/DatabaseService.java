package com.adidas.database.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adidas.database.repository.SubscriptionRepository;
import com.adidas.database.service.model.Subscription;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class DatabaseService {
	@Autowired
	private SubscriptionRepository repo;
//	
	public Integer create(Subscription subscription) {
		return repo.save(subscription).getNewsletterId();
	}
}

package com.adidas.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.adidas.database.service.model.Subscription;


public interface SubscriptionRepository  extends JpaRepository<Subscription, Long> {

	public Subscription findByEmail(final String email);

}

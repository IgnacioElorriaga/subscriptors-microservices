package com.adidas.database.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adidas.database.service.model.Subscription;

public interface SubscriptionRepository  extends JpaRepository<Subscription, Long> {

	List<Subscription> findByGender(String gender);
}

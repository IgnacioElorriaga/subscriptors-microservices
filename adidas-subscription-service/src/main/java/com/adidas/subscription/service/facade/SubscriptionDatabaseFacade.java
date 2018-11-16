package com.adidas.subscription.service.facade;

import com.adidas.subscription.client.model.SubscriptionRequest;
import com.adidas.subscription.service.dto.Subscription;

public interface SubscriptionDatabaseFacade {
	/**
	 * Saves the information into the database.
	 * All the mandatory values are previously checked and will be sent in that format.
	 * 
	 * @param subscription with the information to store
	 * @return the ID generated.
	 */
	 Subscription save(SubscriptionRequest subscription);
}

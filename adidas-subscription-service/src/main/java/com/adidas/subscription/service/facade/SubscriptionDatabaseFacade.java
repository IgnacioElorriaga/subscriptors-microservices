package com.adidas.subscription.service.facade;

import javax.validation.constraints.NotNull;

import com.adidas.subscription.client.model.DatabaseRequest;
import com.adidas.subscription.service.dto.Subscription;

public interface SubscriptionDatabaseFacade {
	/**
	 * Saves the information into the database.
	 * All the mandatory values are previously checked and will be sent in that format.
	 * 
	 * @param subscription with the information to store
	 * @return the ID generated.
	 */
	Subscription save(@NotNull final DatabaseRequest subscription);
}

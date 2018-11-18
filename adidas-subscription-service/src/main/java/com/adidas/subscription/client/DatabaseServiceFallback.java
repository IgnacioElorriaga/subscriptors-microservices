package com.adidas.subscription.client;

import org.springframework.stereotype.Component;

import com.adidas.subscription.client.model.DatabaseRequest;
import com.adidas.subscription.client.model.DatabaseResponse;

@Component
public class DatabaseServiceFallback implements DatabaseServiceFeignClient{
	@Override
	public DatabaseResponse createSubscription(DatabaseRequest body) {
		return DatabaseResponse.builder().subscriptionId(0).build();
	}
}

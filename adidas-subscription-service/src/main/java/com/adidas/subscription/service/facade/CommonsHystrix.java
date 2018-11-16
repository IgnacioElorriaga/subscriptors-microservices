package com.adidas.subscription.service.facade;

import com.adidas.subscription.service.dto.Gender;
import com.adidas.subscription.service.dto.Subscription;

public class CommonsHystrix {

	public static Subscription fallbackDefaultSubscription() {
		return Subscription.builder()
				.consent(Boolean.FALSE)
				.dateOfBirth(null)
				.email("")
				.firstName("")
				.gender(Gender.OTHER)
				.newsletterId(0)
				.build();
	}
}

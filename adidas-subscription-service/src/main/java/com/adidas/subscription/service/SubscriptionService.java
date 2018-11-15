package com.adidas.subscription.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adidas.subscription.client.model.SubscriptionRequest;
import com.adidas.subscription.service.dto.DateParam;
import com.adidas.subscription.service.dto.EmailParam;
import com.adidas.subscription.service.dto.Response;
import com.adidas.subscription.service.dto.Subscription;
import com.adidas.subscription.service.facade.SubscriptionDatabaseFacade;
import com.adidas.subscription.service.facade.SubscriptionEmailServiceFacade;
import com.adidas.subscription.service.facade.SubscriptionEventServiceFacade;
import com.adidas.subscription.service.validator.Validator;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SubscriptionService {
	@Autowired
	private final SubscriptionEmailServiceFacade facadeEmail;
	@Autowired
	private final SubscriptionEventServiceFacade facadeEvent;
	@Autowired
	private final SubscriptionDatabaseFacade facadeDatabase;

	private final Validator<EmailParam> emailAddressValidator;
	private final Validator<DateParam> dateValidator;

	public Response createSubscription(SubscriptionRequest request) {
		valditeEmail(request.getEmail());

		// Avoid having it duplicated or in the camelcase
		request.setEmail(request.getEmail().toLowerCase());

		validateDateOfBirth(request.getDateOfBirth());

		Subscription subscription = facadeDatabase.save(request);
//		facadeEvent.executeEvent(request);
		facadeEmail.processEmail(request);
		return Response.builder().subscriptionId(subscription.getSubscriptionId()).build();
	}

	private void valditeEmail(String email) {
		emailAddressValidator.validate(EmailParam.builder().email(email).build());
	}

	private void validateDateOfBirth(String dateOfBirth) {
		dateValidator.validate(DateParam.builder().date(dateOfBirth).build());
	}

}
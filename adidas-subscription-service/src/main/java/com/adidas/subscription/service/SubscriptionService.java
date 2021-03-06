package com.adidas.subscription.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.adidas.subscription.client.model.DatabaseRequest;
import com.adidas.subscription.client.model.EmailRequest;
import com.adidas.subscription.client.model.EventRequest;
import com.adidas.subscription.service.converter.SubscriptionRequestToDatabaseRequest;
import com.adidas.subscription.service.converter.SubscriptionRequestToEmailRequest;
import com.adidas.subscription.service.converter.SubscriptionToEventRequest;
import com.adidas.subscription.service.dto.Response;
import com.adidas.subscription.service.dto.Subscription;
import com.adidas.subscription.service.dto.SubscriptionRequest;
import com.adidas.subscription.service.facade.SubscriptionDatabaseFacade;
import com.adidas.subscription.service.facade.SubscriptionEmailServiceFacade;
import com.adidas.subscription.service.facade.SubscriptionEventServiceFacade;
import com.adidas.subscription.service.validator.Validator;

import lombok.AllArgsConstructor;

/**
 * Service class which it is the main part of the Micro and the logic of the
 * flow(s) is/are stored. <br/>
 * It performs the validation of the format / validity of the most
 * representative fields, such as, email, gender and date. In order to do a
 * double check for the format of them. <br/>
 * As we are not able to know if the introduced name is a valid one (only if we
 * create another one which stores in a Big Data project with all of the
 * possibilities), for instance. On the other hand, we can know whether these 3
 * validate fields are valid or not.
 * 
 * @author nacho
 *
 */
@Service
@AllArgsConstructor
public class SubscriptionService {

	private final SubscriptionEmailServiceFacade facadeEmail;
	private final SubscriptionEventServiceFacade facadeEvent;
	private final SubscriptionDatabaseFacade facadeDatabase;
	private final Validator<SubscriptionRequest> requestValidator;
	private final SubscriptionRequestToDatabaseRequest converter;
	private final SubscriptionRequestToEmailRequest emailConverter;
	private final SubscriptionToEventRequest eventConverter;
	

	/**
	 * Executes the whole logic of the flow. It validates for the invalid data,
	 * which is invalid format either the email or the date. Then, it calls the
	 * facade (separating the logic between the model/service and the final
	 * implementation) for each one.
	 * 
	 * @param request where is stored the input data to be processed.
	 * @return the id of the new Subscription.
	 */
	@Cacheable("subscriptions")
	public Response createSubscription(final SubscriptionRequest request) {
		validateParameters(request);

		// Avoid having it duplicated or in the camelcase
		request.setEmail(request.getEmail().toLowerCase());
		
		Subscription subscription = executeDatabase(request);
		
		executeEmail(request);
		
		executeEvent(subscription);
		
		return Response.builder()
				.subscriptionId(subscription.getSubscriptionId())
				.build();
	}

	private void validateParameters(final SubscriptionRequest request) {
		requestValidator.validate(request);
	}

	private DatabaseRequest convertToDatabaseRequest(final SubscriptionRequest request) {
		return converter.convert(request);
	}

	private EmailRequest convertToEmailRequest(final SubscriptionRequest request) {
		return emailConverter.convert(request);
	}

	private EventRequest convertToEventRequest(final Subscription request) {
		return eventConverter.convert(request);
	}

	private void executeEvent(Subscription subscription) {
		EventRequest eventRequest = convertToEventRequest(subscription);
		facadeEvent.executeEvent(eventRequest);
	}

	private void executeEmail(SubscriptionRequest subscription) {
		EmailRequest emailRequest = convertToEmailRequest(subscription);
		facadeEmail.processEmail(emailRequest);

	}

	private Subscription executeDatabase(SubscriptionRequest request) {
		DatabaseRequest databaseRequest = convertToDatabaseRequest(request);
		return facadeDatabase.save(databaseRequest);
	}
}

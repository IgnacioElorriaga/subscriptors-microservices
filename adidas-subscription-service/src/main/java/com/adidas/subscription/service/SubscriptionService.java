package com.adidas.subscription.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adidas.subscription.client.model.SubscriptionRequest;
import com.adidas.subscription.service.dto.DateParam;
import com.adidas.subscription.service.dto.EmailParam;
import com.adidas.subscription.service.dto.Gender;
import com.adidas.subscription.service.dto.Response;
import com.adidas.subscription.service.dto.Subscription;
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
	@Autowired
	private final SubscriptionEmailServiceFacade facadeEmail;
	@Autowired
	private final SubscriptionEventServiceFacade facadeEvent;
	@Autowired
	private final SubscriptionDatabaseFacade facadeDatabase;

	private final Validator<EmailParam> emailAddressValidator;
	private final Validator<DateParam> dateValidator;

	/**
	 * Executes the whole logic of the flow. It validates for the invalid data,
	 * which is invalid format either the email or the date. Then, it calls the
	 * facade (separating the logic between the model/service and the final
	 * implementation) for each one.
	 * 
	 * @param request
	 *            where is stored the input data to be processed.
	 * @return the id of the new Subscription.
	 */
	public Response createSubscription(final SubscriptionRequest request) {
		valditeEmail(request.getEmail());

		// Avoid having it duplicated or in the camelcase
		request.setEmail(request.getEmail().toLowerCase());

		validateDateOfBirth(request.getDateOfBirth());
		validateGender(request.getGender());

		Subscription subscription = facadeDatabase.save(request);
		facadeEvent.executeEvent(request);
		facadeEmail.processEmail(request);
		return Response.builder().subscriptionId(subscription.getSubscriptionId()).build();
	}

	private void valditeEmail(final String email) {
		emailAddressValidator.validate(EmailParam.builder().email(email).build());
	}

	private void validateDateOfBirth(final String dateOfBirth) {
		dateValidator.validate(DateParam.builder().date(dateOfBirth).build());
	}

	private void validateGender(final String gender) {
		Gender.obtainValue(gender);
	}

}

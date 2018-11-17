package com.adidas.subscription.controller;

import java.net.HttpURLConnection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.adidas.subscription.service.SubscriptionService;
import com.adidas.subscription.service.dto.Response;
import com.adidas.subscription.service.dto.SubscriptionRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Controller layer for the microservice. Defines an endpoint for the
 * application. In that case "/subscriptions" where the consumers will send the
 * info via POST (we are creating a new subscription).
 * 
 * For the next iterations / Sprints / ... the actions:
 * <li>GET (read of one subscription by email or whatever),</li>
 * <li>PATCH to update some data of it,</li>
 * <li>DELETE to unsubscribe</li> could be delivered. In this layer we only need
 * to call to the Model / Service layer where the logic will be stored,
 * following the rule of slim controllers - fat services.
 * 
 * The validation for the input data is done through the annotations
 * {@linkplain Validated} at class level and {@linkplain Valid} on the DTO and
 * inside the DTO with the specific annotation.
 * 
 * @author nacho
 *
 */
@RestController
@Validated
@Api(value = "Subcriptior", description = "Creates a new Subscription for the user.")
public class SubscriptionController {

	private SubscriptionService service;

	@Autowired
	public SubscriptionController(final SubscriptionService service) {
		this.service = service;
	}

	/**
	 * Creates a new subscription with the information provided.
	 * 
	 * @param request
	 *            with the values to be stored.
	 * @return the Subscription ID generated.
	 */
	@CrossOrigin
	@RequestMapping(path="/subscriptions", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value= "Allows the user to subscribe to the newsletter", response = Response.class)
	@ApiResponses({
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns the created ID for that subscription"),
			@ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Invalid parameters"),
			@ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Unexpected error"), })
	public @ApiParam("Response generated") Response 
	createSubscription(
			@RequestBody 
			@Valid 
			@ApiParam(value = "Body of the request") 
					final SubscriptionRequest request) {
		return service.createSubscription(request);

	}

}

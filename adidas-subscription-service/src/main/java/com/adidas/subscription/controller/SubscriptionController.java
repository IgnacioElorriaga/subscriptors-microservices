package com.adidas.subscription.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.net.HttpURLConnection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.adidas.subscription.service.SubscriptionService;
import com.adidas.subscription.service.dto.Response;
import com.adidas.subscription.service.dto.SubscriptionRequest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;

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
@AllArgsConstructor
@Validated
public class SubscriptionController {
	@Autowired
	private SubscriptionService service;

	@GetMapping
	public String echo() {
		return "Ey echo!";
	}

	@CrossOrigin
	@ApiOperation(produces = APPLICATION_JSON_VALUE, value = "Allows the user to subscribe to the newsletter", notes = "A new subscription will be created")
	@ApiResponses({
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns the created ID for that subscription"),
			@ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Invalid parameters"),
			@ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Unexpected error"), })
	@PostMapping("/subscriptions")
	public Response createSubscription(@RequestBody @Valid SubscriptionRequest request) {
		return service.createSubscription(request);

	}

}

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

import com.adidas.subscription.client.model.SubscriptionRequest;
import com.adidas.subscription.service.SubscriptionService;
import com.adidas.subscription.service.dto.Response;
import com.adidas.subscription.service.dto.Subscription;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@Validated
public class SubscriptionController {
	@Autowired
	private SubscriptionService service;

	@GetMapping
	public String get() {
		return "Hola";
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

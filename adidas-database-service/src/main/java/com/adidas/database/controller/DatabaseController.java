package com.adidas.database.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.adidas.database.service.DatabaseService;
import com.adidas.database.service.model.Response;
import com.adidas.database.service.model.SubscriptionRequest;

@RestController
public class DatabaseController {

	private DatabaseService service;

	@Autowired
	public DatabaseController(DatabaseService service) {
		this.service = service;
	}

	@PostMapping("/database/subscriptions")
	@CrossOrigin
	public Response create(@RequestBody SubscriptionRequest body) {

		return Response.builder().subscriptionId(service.create(body)).build();
	}

}

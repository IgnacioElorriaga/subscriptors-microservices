package com.adidas.database.controller;

import java.net.HttpURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.adidas.database.service.DatabaseService;
import com.adidas.database.service.model.Response;
import com.adidas.database.service.model.SubscriptionRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "Database Service", description = "Stores the information received.")
public class DatabaseController {

	private DatabaseService service;

	@Autowired
	public DatabaseController(DatabaseService service) {
		this.service = service;
	}

	@PostMapping("/database/subscriptions")
	@ApiOperation(value= "Saves new subscription information in the database.", response = Response.class)
	@ApiResponses({
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns the created ID for that subscription"),
			@ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Unexpected error"), })
	public
	@ResponseBody
	@ApiParam("Response generated") 
	Response create(
			@RequestBody 
			@ApiParam("subscription information object") 
			SubscriptionRequest body) {
		return Response.builder().subscriptionId(service.create(body)).build();
	}

}

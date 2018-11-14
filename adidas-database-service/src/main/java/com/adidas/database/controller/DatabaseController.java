package com.adidas.database.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.net.HttpURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adidas.database.service.DatabaseService;
import com.adidas.database.service.model.Subscription;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/subscriptions",produces = APPLICATION_JSON_VALUE)
public class DatabaseController {

	
	private final DatabaseService service;
	
	@Autowired
	public DatabaseController(DatabaseService service) {
		this.service = service;
	}
	
	@PostMapping
	@CrossOrigin
    @ApiOperation(produces = APPLICATION_JSON_VALUE, 
    value = "", 
    notes = "")
	@ApiResponses({
	      @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns the created ID for that subscription"),
	      @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Invalid parameters"),
	      @ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Unexpected error"),})
	public Integer create(@RequestBody Subscription body) {
		return service.create(body);
	}
}

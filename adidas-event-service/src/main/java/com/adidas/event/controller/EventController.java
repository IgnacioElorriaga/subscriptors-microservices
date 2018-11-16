package com.adidas.event.controller;

import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;
import static java.net.HttpURLConnection.HTTP_INTERNAL_ERROR;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adidas.event.service.model.Event;
import com.adidas.event.service.model.EventResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/events",produces = APPLICATION_JSON_VALUE)
@Api(value = "Black box which does something")
public class EventController {
	
	
	
	@PostMapping
	@CrossOrigin
    @ApiOperation(produces = APPLICATION_JSON_VALUE, 
			value = "triggers an event for the rest of consumers")
	@ApiResponses({
	      @ApiResponse(code = HTTP_OK, message = "Returns the created ID for that subscription"),
	      @ApiResponse(code = HTTP_BAD_REQUEST, message = "Invalid parameters"),
	      @ApiResponse(code = HTTP_INTERNAL_ERROR, message = "Unexpected error"),})
	public EventResponse checkEmail(@RequestBody Event body) {
		return EventResponse.builder().answer("PING OK").build();
	}
}

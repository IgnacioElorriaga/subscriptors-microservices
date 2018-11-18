package com.adidas.event.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.net.HttpURLConnection;

import javax.validation.constraints.NotNull;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.adidas.event.service.model.Event;
import com.adidas.event.service.model.EventResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/events",produces = APPLICATION_JSON_VALUE)
@Api(value = "Events Service", description = "Do something with the subscription received.")
public class EventController {
	
	@PostMapping
	@ApiOperation(value= "Triggers a new event for the information received.", response = EventResponse.class)
	@ApiResponses({
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns the message from the JMS Servers")})
	public 
	@ResponseBody
	@ApiParam("Response generated") 
	EventResponse 
	checkEmail(
			@RequestBody 
			@NotNull 
			@ApiParam("Event to be processed.") 
			final Event body) {
		return EventResponse.builder().answer("PING OK").build();
	}
}

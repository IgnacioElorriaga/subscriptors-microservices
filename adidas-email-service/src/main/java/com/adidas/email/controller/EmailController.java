package com.adidas.email.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.net.HttpURLConnection;

import javax.validation.constraints.NotNull;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adidas.email.service.model.Email;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/emails",produces = APPLICATION_JSON_VALUE)
@Api(value = "Email Service", description = "Black box! Do something with the email received.")
public class EmailController {

	@PostMapping
	@ApiOperation(value= "Execute some logic with the email received.", response = Boolean.class)
	@ApiResponses({
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "True when the operation was fine"),
			@ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Unexpected error"), })
	public Boolean processEmail(
			@RequestBody 
			@NotNull 
			@ApiParam("Email information to process") 
			final Email body) {
		return Boolean.TRUE;
	}
}

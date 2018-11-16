package com.adidas.email.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.net.HttpURLConnection;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adidas.email.service.model.Email;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/emails",produces = APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class EmailController {

	@PostMapping
	@CrossOrigin
    @ApiOperation(produces = APPLICATION_JSON_VALUE, 
    value = "", 
    notes = "")
	@ApiResponses({
	      @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns the created ID for that subscription"),
	      @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Invalid parameters"),
	      @ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Unexpected error"),})
	public Boolean checkEmail(@RequestBody Email body) {
		return Boolean.TRUE;
	}
}

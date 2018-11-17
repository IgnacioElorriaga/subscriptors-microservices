package com.adidas.email.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import javax.validation.constraints.NotNull;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adidas.email.service.model.Email;

@RestController
@RequestMapping(path = "/emails",produces = APPLICATION_JSON_VALUE)
public class EmailController {

	@PostMapping
	@CrossOrigin
	public Boolean checkEmail(@RequestBody @NotNull final Email body) {
		return Boolean.TRUE;
	}
}

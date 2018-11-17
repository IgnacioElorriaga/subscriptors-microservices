package com.adidas.event.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.nio.file.attribute.UserPrincipal;
import java.security.Principal;

import javax.validation.constraints.NotNull;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adidas.event.service.model.Event;
import com.adidas.event.service.model.EventResponse;

@RestController
@RequestMapping(path = "/events",produces = APPLICATION_JSON_VALUE)
public class EventController {
	
	@PostMapping
	@CrossOrigin
	public EventResponse checkEmail(@RequestBody @NotNull final Event body, final Principal principal) {
		UserPrincipal up = (UserPrincipal) principal;
		System.out.println(up);
		return EventResponse.builder().answer("PING OK").build();
	}
}

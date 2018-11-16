package com.adidas.event.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adidas.event.Songs;
import com.adidas.event.service.model.Event;
import com.adidas.event.service.model.EventResponse;

@Component
public class EventService {

	private Songs mood;
	@Autowired
	public EventService(final Songs mood) {
		this.mood = mood;
	}
	
	public EventResponse process(final Event event) {
		return EventResponse.builder()
				.answer(mood.getNames().get((int) Math.random() * mood.getNames().size() - 1))
				.build();
	}
}

package com.adidas.subscription.service.converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.constraints.NotNull;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.adidas.subscription.service.dto.Subscription;
import com.adidas.subscription.service.dto.SubscriptionRequest;

@Component
public class SubscriptionRequestToSubscriptionConverter implements Converter<SubscriptionRequest, Subscription> {

	@Override
	public Subscription convert(@NotNull SubscriptionRequest source) {

		return Subscription.builder()
				.consent(source.getConsent())
				.dateOfBirth(obtainDate(source.getDateOfBirth()))
				.email(source.getEmail())
				.firstName(source.getFirstName())
				.gender(source.getGender())
				.build();
	}

	private LocalDate obtainDate(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return LocalDate.parse(date, formatter);
	}

}

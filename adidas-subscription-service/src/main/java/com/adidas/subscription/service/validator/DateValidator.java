package com.adidas.subscription.service.validator;

import static java.util.Optional.ofNullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;

import com.adidas.subscription.service.dto.DateParam;
import com.adidas.subscription.service.exceptions.InvalidParamException;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class DateValidator implements Validator<DateParam> {

	@Override
	public DateParam validate(DateParam source) {
		
		ofNullable(source).orElseThrow(() -> new InvalidParamException("no date to parse"));
		ofNullable(source.getDate()).orElseThrow(() -> new InvalidParamException("no date to parse"));

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(false);
		try {
			sdf.parse(source.getDate());
		} catch (ParseException e) {
			throw new InvalidParamException(e.getMessage());
		}
		return source;
	}

}

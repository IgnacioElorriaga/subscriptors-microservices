package com.adidas.subscription.service.validator;

import static java.util.Optional.ofNullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adidas.subscription.DateProperties;
import com.adidas.subscription.service.dto.DateParam;
import com.adidas.subscription.service.exceptions.InvalidParamException;

import lombok.AllArgsConstructor;

/**
 * Validates the format of the date.<br/>
 * For the flow, it doesn't matter it is already validated with the NotNull
 * because it could be also empty and in that case would be valid.<br/>
 * In case the email is not present or empty, it will raise an exception.<br/>
 * Then it is checked that the input date is a valid date (avoiding to have
 * something like 29-02-2017 or 31-04-2018). For that cases an
 * {@linkplain InvalidParamException} will be thrown
 * 
 * @author nacho
 *
 */
@Component
@AllArgsConstructor
public class DateValidator implements Validator<DateParam> {

	@Autowired
	private DateProperties props;
	
	private static final String DATE_EXCEPTION_MSG = "The format of the date is not valid. It must be %s";
	private static final String NO_DATE_EXCEPTION_MSG = "The date field is not populated. Please populate it in the format %s";
	@Override
	public DateParam validate(DateParam source) {
		
		ofNullable(source).orElseThrow(() -> new InvalidParamException(NO_DATE_EXCEPTION_MSG));
		ofNullable(source.getDate()).orElseThrow(() -> new InvalidParamException(NO_DATE_EXCEPTION_MSG));
		
		if(source.getDate().length() != 10) {
			throw new InvalidParamException(String.format(DATE_EXCEPTION_MSG, props.getFormat()));
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat(props.getFormat());
		sdf.setLenient(false);
		try {
			sdf.parse(source.getDate());
		} catch (ParseException e) {
			throw new InvalidParamException(e.getMessage());
		}
		return source;
	}

}

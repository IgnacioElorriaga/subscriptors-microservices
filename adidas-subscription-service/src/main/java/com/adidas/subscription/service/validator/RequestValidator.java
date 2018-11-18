package com.adidas.subscription.service.validator;

import static java.util.Optional.ofNullable;
import static org.apache.commons.lang.StringUtils.isNotBlank;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.adidas.subscription.service.dto.Gender;
import com.adidas.subscription.service.dto.SubscriptionRequest;
import com.adidas.subscription.service.exceptions.InvalidParamException;

@Component
public class RequestValidator implements Validator<SubscriptionRequest> {

	private static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile(".+@.+\\..+", Pattern.CASE_INSENSITIVE);
	private static final String DATE_EXCEPTION_MSG = "The format of the date is not valid. It must be %s";
	private static final String NO_DATE_EXCEPTION_MSG = "The date field is not populated. Please populate it in the format %s";
	private static final String FORMAT_DATE = "yyyy-MM-dd";

	public boolean isValid(String email) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
		return matcher.find();
	}

	public void validateEmail(final String email) {
		if (StringUtils.isEmpty(email)) {
			throw new InvalidParamException("The email is not present. It is required");
		}
	
		if (email.contains(" ") || !isValid(email)) {
			throw new InvalidParamException("The format of the email is not correct.");
		}

	}

	private void validateGender(String gender) {

		if (isNotBlank(gender)) {
			gender = gender.toLowerCase();
			Gender.obtainValue(gender);
		}
	}

	private void validateDate(String date) {

		ofNullable(date).orElseThrow(() -> new InvalidParamException(NO_DATE_EXCEPTION_MSG));

		if (date.length() != 10) {
			throw new InvalidParamException(String.format(DATE_EXCEPTION_MSG, FORMAT_DATE));
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE);
		sdf.setLenient(false);
		try {
			sdf.parse(date);
			
		} catch (ParseException e) {
			throw new InvalidParamException(e.getMessage());
		}
		//I want to keep it. This case of use should be reviewed with the final client / manager.
		
//		if(LocalDate.now().isBefore(LocalDate.parse(date))) {
//			throw new InvalidParamException("Furute Date Of birth is not possible");
//		}
		
	}

	@Override
	public SubscriptionRequest validate(SubscriptionRequest source) {
		validateEmail(source.getEmail());
		validateDate(source.getDateOfBirth());
		validateGender(source.getGender());
		source.setEmail(source.getEmail().toLowerCase());

		return source;
	}

}

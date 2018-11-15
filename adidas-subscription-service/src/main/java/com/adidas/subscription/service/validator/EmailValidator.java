package com.adidas.subscription.service.validator;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.adidas.subscription.service.dto.EmailParam;
import com.adidas.subscription.service.exceptions.InvalidParamException;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class EmailValidator implements Validator<EmailParam> {

	private final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile(".+@.+\\..+", Pattern.CASE_INSENSITIVE);

	public boolean isValid(String email) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
		return matcher.find();
	}

	@Override
	public EmailParam validate(EmailParam source) {
		if (StringUtils.isEmpty(source.getEmail())) {
			throw new InvalidParamException("The email is not present. It is required");
		}
		if (!isValid(source.getEmail())) {
			throw new InvalidParamException("The format of the email is not correct.");
		}
		
		return source;
	}

}

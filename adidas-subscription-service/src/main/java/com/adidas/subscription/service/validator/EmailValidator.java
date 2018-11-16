package com.adidas.subscription.service.validator;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.adidas.subscription.service.dto.EmailParam;
import com.adidas.subscription.service.exceptions.InvalidParamException;

import lombok.AllArgsConstructor;

/**
 * Validates the format of the input email.<br/>
 * For the flow, it doesn't matter it is already validated with the NotNull
 * because it could be also empty and in that case would be valid.<br/>
 * In case the email is not present or empty, it will raise an exception.<br/>
 * Same case will be when the REGEX fails, it was the easiest to valid
 * 
 * "at least one character plus @ symbol plus at least one character plus dot
 * plus at least one char"
 * 
 * @author nacho
 *
 */
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

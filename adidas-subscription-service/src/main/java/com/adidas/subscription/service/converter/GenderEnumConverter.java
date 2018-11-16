package com.adidas.subscription.service.converter;

import javax.validation.constraints.NotNull;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.adidas.subscription.service.dto.Gender;
import com.adidas.subscription.service.exceptions.InvalidParamException;

/**
 * Converter used for the Gender parameter. It will parse from String to enum
 * type, performing an internal validation previously. In case the value is a
 * no-related value.
 * 
 * @author nacho
 *
 */
@Component
public class GenderEnumConverter implements Converter<String, Gender> {

	/**
	 * Executes the obtainValue inside the Gender enum. It validates and if it
	 * is fine, read the value. Otherwise, an {@linkplain InvalidParamException}
	 * will be risen.
	 */
	@Override
	public Gender convert(@NotNull final String source) {

		return Gender.obtainValue(source);
	}

}

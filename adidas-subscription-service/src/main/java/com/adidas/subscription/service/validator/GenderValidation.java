package com.adidas.subscription.service.validator;

import org.apache.commons.lang3.StringUtils;

import com.adidas.subscription.service.dto.Gender;
import com.adidas.subscription.service.dto.GenderParam;

public class GenderValidation implements Validator<GenderParam> {

	@Override
	public GenderParam validate(GenderParam source) {

		if (StringUtils.isNotBlank(source.getGender())) {
			Gender.obtainValue(source.getGender());
		}
		return GenderParam.builder().gender(source.getGender().toLowerCase()).build();
	}

}

package com.adidas.subscription.service.exceptions;

import javax.validation.constraints.NotNull;

public class InvalidParamException extends RuntimeException {

	public InvalidParamException(@NotNull String parameter) {
		super(String.format("The parameter %s is not valid. Please check it", parameter));
	}
}

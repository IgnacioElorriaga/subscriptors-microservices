package com.adidas.subscription.service.exceptions;

import javax.validation.constraints.NotNull;

public class InvalidParamException extends RuntimeException {

	public InvalidParamException(@NotNull final String message) {
		super(message);
	}

}

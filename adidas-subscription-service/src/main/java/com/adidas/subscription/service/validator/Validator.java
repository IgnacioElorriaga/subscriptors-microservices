package com.adidas.subscription.service.validator;

public interface Validator<T> {
	T validate(T source);
}

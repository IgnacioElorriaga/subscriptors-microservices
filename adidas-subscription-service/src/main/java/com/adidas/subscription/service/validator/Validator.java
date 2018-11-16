package com.adidas.subscription.service.validator;

/**
 * Own interface to validate whatever type of parameters.
 * 
 * @author nacho
 *
 * @param <T> which could be any kind of Object.
 */
public interface Validator<T> {
	T validate(T source);
}

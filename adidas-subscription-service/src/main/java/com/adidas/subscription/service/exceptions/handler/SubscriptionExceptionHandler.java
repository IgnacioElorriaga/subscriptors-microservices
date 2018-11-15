package com.adidas.subscription.service.exceptions.handler;

import java.util.Optional;

import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import com.adidas.subscription.service.exceptions.InvalidParamException;
import com.adidas.subscription.service.exceptions.MicroserviceException;
import com.adidas.subscription.service.exceptions.UnknownException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
@RequestMapping(produces = "application/vnd.error+json")
public class SubscriptionExceptionHandler {
	
	@ExceptionHandler(InvalidParamException.class)
	  public ResponseEntity<VndErrors> handleInvalidParamException(InvalidParamException e,
	    WebRequest request) {
		log.error("Error while trying to process an input parameter", e);
		return error(e, HttpStatus.BAD_REQUEST, "");
	  }
	@ExceptionHandler(MicroserviceException.class)
	  public ResponseEntity<VndErrors> handleMicroserviceException(MicroserviceException e,
	    WebRequest request) {
		log.error("Error while trying to process the request in the Feign Client", e);
		return error(e, HttpStatus.INTERNAL_SERVER_ERROR, "");
	  }
	@ExceptionHandler(UnknownException.class)
	  public ResponseEntity<VndErrors> handleUnknownException(final UnknownException e,
	    WebRequest request) {
		log.error("Unexpected error", e);
		return error(e, HttpStatus.INTERNAL_SERVER_ERROR, "");
		
	  }
	
	 private ResponseEntity<VndErrors> error(
		      final Exception exception, final HttpStatus httpStatus, final String logRef) {
		    final String message =
		        Optional.of(exception.getMessage()).orElse(exception.getClass().getSimpleName());
		    return new ResponseEntity<>(new VndErrors(logRef, message), httpStatus);
		  }
}

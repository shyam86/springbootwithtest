package com.techno2know.friends.controller;

import javax.validation.ValidationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.techno2know.friends.utils.ErrorMessage;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST )
	@ExceptionHandler(ValidationException.class)
	ErrorMessage exceptionHandler(ValidationException e){
		return new ErrorMessage("400", e.getMessage());
	}
	
	
}

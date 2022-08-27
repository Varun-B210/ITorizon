package com.itsupporttracker.exception;

import java.net.http.HttpRequest;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.itsupporttracker.entity.Message;

@RestControllerAdvice
public class ExceptionHandlerClass {

	@ExceptionHandler(UserNotFoundException.class)
	public Map<String, String> handleException(UserNotFoundException ex) {

		Map<String, String> error = new HashMap<>();
		error.put("error message", ex.getMessage());
		return error;
	}

	@ExceptionHandler(EmailExistsException.class)
	public Map<String, String> EmailException(EmailExistsException exp) {

		Map<String, String> error = new HashMap<>();
		error.put("error message", exp.getMessage());
		return error;
	}

	@ExceptionHandler(NotValidException.class)
	public ResponseEntity<Message> handleStringException(NotValidException exception, WebRequest request) {
		Message message = new Message(exception.getMessage());
		return new ResponseEntity<Message>(message, HttpStatus.NOT_FOUND);
	}

}

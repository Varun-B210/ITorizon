package com.itsupporttracker.exception;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.itsupporttracker.entity.Message;

/**
 * Handling custom exceptions
 */
@RestControllerAdvice
public class ExceptionHandlerClass {

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Message> notFoundException(NotFoundException exception) {
		Message error = new Message(exception.getMessage());
		return new ResponseEntity<Message>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NotValidException.class)
	public ResponseEntity<Message> contentException(NotValidException exception) {
		Message error = new Message(exception.getMessage());
		return new ResponseEntity<Message>(error, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(InvalidFormatException.class)
	public ResponseEntity<Message> invalidFormatException(InvalidFormatException exception) {
		Message error = new Message("Enter Input with Valid Format");
		return new ResponseEntity<Message>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NumberFormatException.class)
	public ResponseEntity<Message> numberFormatException(NumberFormatException exception) {
		Message error = new Message("Enter valid input");
		return new ResponseEntity<Message>(error, HttpStatus.BAD_REQUEST);

	}
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<Message> noElementException(NoSuchElementException exception) {
		Message error = new Message("Enter valid input");
		return new ResponseEntity<Message>(error, HttpStatus.BAD_REQUEST);
}
}

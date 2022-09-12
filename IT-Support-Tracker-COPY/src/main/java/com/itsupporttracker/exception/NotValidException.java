package com.itsupporttracker.exception;

public class NotValidException extends Exception{

	public NotValidException() {
		super("Enter valid details");
	}
	
	public NotValidException(String message) {
		super(message);
	}

	
}

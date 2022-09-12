package com.itsupporttracker.exception;

public class NotFoundException extends Exception{

	public NotFoundException(String message) {
		super(message);
	}
	
	public NotFoundException() {
		super("Ticket not found");
	}
	
	

}

package com.itsupporttracker.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.itsupporttracker.Viewticket;
import com.itsupporttracker.entity.Message;
import com.itsupporttracker.entity.Ticket;
import com.itsupporttracker.exception.NotValidException;
import com.itsupporttracker.exception.UserNotFoundException;

public interface UserService {
	
	String createTicket(Ticket Ticket, int userId) throws UserNotFoundException;
	String addComment(String message, int ticketId, int userId) throws UserNotFoundException;
	List<Viewticket> viewAllTickets();
	Viewticket viewByID(String ticketId) throws UserNotFoundException, NotValidException;
	ResponseEntity<Message> getComment();

}

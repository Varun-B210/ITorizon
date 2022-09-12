package com.itsupporttracker.service;

import java.util.List;

import com.itsupporttracker.entity.Comment;
import com.itsupporttracker.entity.Ticket;
import com.itsupporttracker.entity.Viewticket;
import com.itsupporttracker.exception.NotValidException;
import com.itsupporttracker.exception.NotFoundException;

public interface UserService {
	
	/**
	 * Defining various methods to be implemented
	 */

	String createTicket(Ticket Ticket, int userId) throws NotFoundException, 	NotValidException;

	String addComment(Comment comment, int ticketId, int userId) throws NotFoundException, 	NotValidException;

	List<Viewticket> findAllByUserId(int userId) throws NotFoundException;

	Viewticket findByTicketId(int ticketId, int userId) throws NotFoundException;

}

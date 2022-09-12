package com.itsupporttracker.controller;

import java.util.List;			
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.itsupporttracker.entity.Comment;
import com.itsupporttracker.entity.Ticket;
import com.itsupporttracker.entity.Viewticket;
import com.itsupporttracker.exception.NotValidException;
import com.itsupporttracker.exception.NotFoundException;
import com.itsupporttracker.serviceImpl.UserServiceImpl;

@RestController
public class UserController {

	@Autowired
	private UserServiceImpl userServiceImpl;

	
	@PostMapping("user/createTicket")
	public String createTicket(@RequestBody Ticket ticket, @RequestParam int userId)
			throws NotFoundException, NotValidException {

		return userServiceImpl.createTicket(ticket, userId);
	}

	
	@GetMapping("user/viewAllTicketsByUserID")
	public List<Viewticket> viewAllTickets(int userId) throws NotFoundException,
			NotValidException {

				return userServiceImpl.findAllByUserId(userId);
	}

	
	@PostMapping("user/addComment")
	public String addComment(@RequestBody Comment comment, @RequestParam int ticketId, 	@RequestParam int userId)
			throws NotFoundException, NotValidException {

		return userServiceImpl.addComment(comment, ticketId, userId);
	}

	
	@GetMapping("user/viewByTicketID")
	Viewticket viewById(@RequestParam int ticketId, @RequestParam int userId)
			throws NotFoundException, NotValidException {

		return userServiceImpl.findByTicketId(ticketId, userId);

	}
}

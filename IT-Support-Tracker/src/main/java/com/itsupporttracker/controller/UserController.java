package com.itsupporttracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itsupporttracker.ViewTicketDAO;
import com.itsupporttracker.Viewticket;
import com.itsupporttracker.entity.Ticket;
import com.itsupporttracker.exception.NotValidException;
import com.itsupporttracker.exception.UserNotFoundException;
import com.itsupporttracker.repository.TicketRepository;
import com.itsupporttracker.serviceImpl.UserServiceImpl;

@RestController
public class UserController {
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	@Autowired
	private TicketRepository ticketRepository;
	
	@PostMapping("user/createTicket")
	public String createTicket(@RequestBody Ticket ticket, @RequestParam int userId) throws UserNotFoundException {
		
		return userServiceImpl.createTicket(ticket, userId);
	}
	
	@GetMapping("user/viewTickets")
	public List<Viewticket> viewAllTickets(){
		return userServiceImpl.viewAllTickets();
	}
	
	@PostMapping("user/addComment")
	public String addComment(String message, int ticketId, int userId) throws UserNotFoundException, NotValidException {
		try {
			
		int TicketID = ticketRepository.findByTicketId(ticketId).getTicketId();
		int UserID = ticketRepository.findByReportedId(userId).getReportedId();

		if(Integer.valueOf(ticketId).equals(Integer.valueOf(TicketID))
			|| Integer.valueOf(userId).equals(Integer.valueOf(UserID)))
			return userServiceImpl.addComment(message, ticketId, userId);
			
		else
			throw new UserNotFoundException("Not found");
		}catch(Exception e) {
			return e.getMessage();
		}
		
	}
	
	@GetMapping("user/viewByID")
	Viewticket viewById(@RequestParam String ticketId) throws UserNotFoundException, NotValidException{
		return userServiceImpl.viewByID(ticketId);
	}
}

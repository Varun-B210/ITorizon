package com.itsupporttracker.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.itsupporttracker.entity.Comment;
import com.itsupporttracker.entity.User;
import com.itsupporttracker.entity.Viewticket;
import com.itsupporttracker.exception.NotFoundException;
import com.itsupporttracker.exception.NotValidException;
import com.itsupporttracker.serviceImpl.AdminServiceImpl;

@RestController
public class AdminController {

	@Autowired
	private AdminServiceImpl adminServiceImpl;
	

	@PostMapping("/admin/addUser")
	public String addUser(@RequestBody User user) throws NotFoundException, NotValidException {
	
		return adminServiceImpl.addUser(user);
	}

	
	@GetMapping("/admin/viewAllUsers")
	public List<User> viewAllUsers() {
		
		return adminServiceImpl.viewAllUsers();
	}

	
	@GetMapping("/admin/viewUser")
	public User viewUser(@RequestParam String userId, @RequestParam String emailId)
			throws NotFoundException {
		
			if (emailId.isEmpty())
				return adminServiceImpl.viewUserId(Integer.valueOf(userId));
			else if (userId.isEmpty())
				return adminServiceImpl.viewUserEmail(emailId);
			else
				return adminServiceImpl.viewUser(Integer.valueOf(userId), emailId);

	}

	
	@PutMapping("/admin/updateUser")
	public String updateUser(@RequestBody User user) throws NotFoundException, 	NotValidException	{ 
				
		return adminServiceImpl.updateUser(user);
	}

	
	@DeleteMapping("/admin/deleteUser")
	public String deleteUser(@RequestParam int userId) throws NotFoundException{
		
		return adminServiceImpl.deleteUser(userId);
	}

	
	@PutMapping("/admin/setAssignee")
	public String setAssignee(@RequestParam int ticketId, @RequestParam int userId, 
			@RequestParam int adminId) throws NotFoundException {
		
		return adminServiceImpl.setAssignee(ticketId, userId, adminId);
	}

	
	@PutMapping("/admin/setStatus")
	public String changeStatus(@RequestParam int ticketId, @RequestParam int statusId,
			@RequestParam int adminId) throws NotFoundException, NotValidException {
		
		return adminServiceImpl.changeStatus(ticketId, statusId, adminId);
	}

	
	@PutMapping("/admin/setPriority")
	public String changePriority(@RequestParam int ticketId, @RequestParam int priorityId,
			@RequestParam int adminId) throws NotFoundException, NotValidException {
		
		return adminServiceImpl.changePriority(ticketId, priorityId, adminId);
	}

	
	@PostMapping("/admin/addComment")
	public String addComment(@RequestBody Comment comment, @RequestParam int ticketId, 	@RequestParam int userId)
			throws NotFoundException, NotValidException {
			
		return adminServiceImpl.addComment(comment, ticketId, userId);
			

	}

	
	@DeleteMapping("/admin/deleteTicket")
	public String deleteTicket(@RequestParam int ticketId, @RequestParam int userId)
			throws NotFoundException, NotValidException {

		return adminServiceImpl.deleteTicket(ticketId, userId);
	}

	
	@GetMapping("/admin/viewAllTickets")
	public List<Viewticket> viewAllTickets() {
		
		return adminServiceImpl.viewAllTickets();
	}

	
	@GetMapping("/admin/viewByTicketID")
	Viewticket viewById(@RequestParam int ticketId) throws NotFoundException {
		
		return adminServiceImpl.viewById(ticketId);
	}
}
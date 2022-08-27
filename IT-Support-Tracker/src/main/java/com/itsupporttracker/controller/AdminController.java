package com.itsupporttracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.RequestParamMethodArgumentResolver;
import org.springframework.web.servlet.tags.Param;

import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.itsupporttracker.Viewticket;
import com.itsupporttracker.entity.User;
import com.itsupporttracker.exception.EmailExistsException;
import com.itsupporttracker.exception.NotValidException;
import com.itsupporttracker.exception.UserNotFoundException;
import com.itsupporttracker.serviceImpl.AdminServiceImpl;

@RestController
public class AdminController {
	
	@Autowired
	private AdminServiceImpl adminServiceImpl;
	
	@PostMapping("/admin/addUser")
	public String addUser(@RequestBody User user) throws EmailExistsException, UserNotFoundException, NotValidException {
		return adminServiceImpl.addUser(user);
	}
	
	@GetMapping("/admin/viewAllUsers")
	public List<User> viewAllUsers(){
		return adminServiceImpl.viewAllUsers();
	}
	
	@GetMapping("/admin/viewUser")
	public User viewUser(@RequestParam String userId, @RequestParam String emailId) throws UserNotFoundException{
		if(emailId.isEmpty())
			return adminServiceImpl.viewUserId(Integer.valueOf(userId));
		else if(userId.isEmpty())
			return adminServiceImpl.viewUserEmail(emailId);
		else 
			return adminServiceImpl.viewUser(Integer.valueOf(userId), emailId);
		
	}
	
	@PutMapping("/admin/updateUser")
	public String updateUser(@RequestBody User user) throws UserNotFoundException {
		return adminServiceImpl.updateUser(user);
	}
	
	@DeleteMapping("/admin/deleteUser")
	public String deleteUser(@RequestParam String userId) throws UserNotFoundException {
		return adminServiceImpl.deleteUser(userId);
	}
	
	@PutMapping("/admin/setAssignee")
	public String setAssignee(@RequestParam int ticketId, @RequestParam int userId, @RequestParam int adminId, @RequestParam int assigneeId) throws UserNotFoundException {
		return adminServiceImpl.setAssignee(ticketId, userId, adminId, assigneeId);
	}
	
	@PutMapping("/admin/setStatus")
	public String changeStatus(@RequestParam int ticketId, @RequestParam int statusId, @RequestParam int adminId) throws UserNotFoundException {
		return adminServiceImpl.changeStatus(ticketId, statusId, adminId);
	}
	
	@PutMapping("/admin/setPriority")
	public String changePriority(@RequestParam int ticketId, @RequestParam int priorityId, @RequestParam int adminId) throws UserNotFoundException {
		return adminServiceImpl.changePriority(ticketId, priorityId, adminId);
	}
	
	@PostMapping("/admin/addComment")
	 public String addComment(@RequestBody String message, @RequestParam int ticketId, @RequestParam int userId) throws UserNotFoundException {
		return adminServiceImpl.addComment(message, ticketId, userId);
	}
	
	@DeleteMapping("/admin/deleteTicket")
	public String deleteTicket(@RequestParam int ticketId, @RequestParam int userId) throws UserNotFoundException, NotValidException {
		return adminServiceImpl.deleteTicket(ticketId, userId);
	}
	
	@GetMapping("/admin/viewAllTickets")
	public List<Viewticket> viewAllTickets(){
		return adminServiceImpl.viewAllTickets();
	}
	
	@GetMapping("/admin/viewByID")
	Viewticket viewById(@RequestParam String ticketId) throws UserNotFoundException, NotValidException {
		return adminServiceImpl.viewById(ticketId);
	}
}
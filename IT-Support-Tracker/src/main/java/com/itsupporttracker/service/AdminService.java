package com.itsupporttracker.service;

import java.util.List;

import com.itsupporttracker.Viewticket;
import com.itsupporttracker.entity.User;
import com.itsupporttracker.exception.EmailExistsException;
import com.itsupporttracker.exception.NotValidException;
import com.itsupporttracker.exception.UserNotFoundException;

public interface AdminService {
	
	String addUser(User user) throws EmailExistsException, UserNotFoundException, NotValidException;
	List<User> viewAllUsers();
	User viewUserId(int userId) throws UserNotFoundException;
	User viewUserEmail(String emailId) throws UserNotFoundException;
	User viewUser(int userId, String emailId) throws UserNotFoundException;
	String updateUser(User user) throws UserNotFoundException;
	String deleteUser(String userId) throws UserNotFoundException;
	String setAssignee(int ticketId, int userId, int adminId, int assigneeId) throws UserNotFoundException;
	String changeStatus(int ticketId, int statusId, int adminId) throws UserNotFoundException;
	String changePriority(int ticketId, int priorityId, int adminId) throws UserNotFoundException;
	String addComment(String message, int ticketId, int userId) throws UserNotFoundException;
	String deleteTicket(int ticketId, int userId) throws UserNotFoundException, NotValidException;
	List<Viewticket> viewAllTickets();
	Viewticket viewById(String ticketId) throws UserNotFoundException, NotValidException;
}

package com.itsupporttracker.service;

import java.util.List;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.itsupporttracker.entity.Comment;
import com.itsupporttracker.entity.User;
import com.itsupporttracker.entity.Viewticket;
import com.itsupporttracker.exception.NotValidException;
import com.itsupporttracker.exception.NotFoundException;

public interface AdminService {
	/**
	 * Defining various methods to be implemented
	 */

	String addUser(User user) throws NotFoundException, NotValidException;

	List<User> viewAllUsers();
		
	User viewUserId(int userId) throws NotFoundException;

	User viewUserEmail(String emailId) throws NotFoundException;

	User viewUser(int userId, String emailId) throws NotFoundException;

	String updateUser(User user) throws NotFoundException;

	String deleteUser(int userId) throws NotFoundException, 
	InvalidFormatException;

	String setAssignee(int ticketId, int userId, int adminId)
			throws NotFoundException, NotValidException;

	String changeStatus(int ticketId, int statusId, int adminId) throws 	NotFoundException, 	NotValidException;

	String changePriority(int ticketId, int priorityId, int adminId)
			throws NotFoundException, NotValidException;

	String addComment(Comment comment, int ticketId, int userId) throws NotFoundException, 	NotValidException;

	String deleteTicket(int ticketId, int userId) throws NotFoundException, NotValidException;

	List<Viewticket> viewAllTickets();

	Viewticket viewById(int ticketId) throws NotFoundException, NotValidException;
}

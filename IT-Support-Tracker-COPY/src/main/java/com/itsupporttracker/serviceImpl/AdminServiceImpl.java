package com.itsupporttracker.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.itsupporttracker.entity.Admin_team;
import com.itsupporttracker.entity.Comment;
import com.itsupporttracker.entity.Priority;
import com.itsupporttracker.entity.Status;
import com.itsupporttracker.entity.Ticket;
import com.itsupporttracker.entity.User;
import com.itsupporttracker.entity.Viewticket;
import com.itsupporttracker.exception.NotValidException;
import com.itsupporttracker.exception.NotFoundException;
import com.itsupporttracker.repository.AdminRepository;
import com.itsupporttracker.repository.CommentRepository;
import com.itsupporttracker.repository.PriorityRepository;
import com.itsupporttracker.repository.StatusRepository;
import com.itsupporttracker.repository.TicketRepository;
import com.itsupporttracker.repository.UserRepository;
import com.itsupporttracker.repository.ViewticketRepository;
import com.itsupporttracker.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	/**
	 * Injecting all the dependencies
	 * 
	 */
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TicketRepository ticketRepository;

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private StatusRepository statusRepository;

	@Autowired
	private PriorityRepository priorityRepository;

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private ViewticketRepository viewticketRepository;

	/**
	 * Adding a new user to the User Table
	 */

	@Override
	public String addUser(User user) throws NotFoundException, NotValidException {

		String regex = "[a-zA-Z0-9-.]+@[a-zA-Z0-9]+.[a-z]{2,3}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(user.getEmailId());
		boolean isEmailValid = matcher.matches();
		
		if (isEmailValid) {

			User existingUser = userRepository.findByEmailId(user.getEmailId());
			if (existingUser != null && existingUser.getEmailId().contains(user.getEmailId()))
				throw new NotValidException("Email already exits! Enter a unique EmailID");
			else {

				user.setCreateDateTime(LocalDateTime.now());
				user.setLastModifiedDateTime(LocalDateTime.now());
				userRepository.save(user);
				return "User added successfully";
			}
		} else
			throw new NotValidException("Enter valid Email format");

	}

	/**
	 * List of all the users present in the database
	 */

	@Override
	public List<User> viewAllUsers() {
		return userRepository.findAll();
	}

	/**
	 * Fetching the user based on the userID
	 */

	@Override
	public User viewUserId(int userId) throws NotFoundException {

		User userById = userRepository.findById(userId)
				.orElseThrow(() -> new NotFoundException("User " + userId + " not found"));

		return userById;
	}

	/**
	 * Fetching the user based on Email ID
	 */

	@Override
	public User viewUserEmail(String emailId) throws NotFoundException {

		User userEmail = userRepository.findByEmailId(emailId);

		if (userEmail != null)
			return userEmail;
		else
			throw new NotFoundException("User " + emailId + " not found");
	}

	/**
	 * Fetching the user based on both userID and emailID
	 */

	@Override
	public User viewUser(int userId, String emailId) throws NotFoundException {

		User user = userRepository.findByUserIdAndEmailId(userId, emailId);

		if (user != null)
			return user;
		else
			throw new NotFoundException("User not found!!");
	}

	/**
	 * updating the user name by passing userID
	 * 
	 * @throws NotFoundException
	 * 
	 * @throws InvalidFormatException
	 */
	@Override
	public String updateUser(User user) throws NotFoundException {

		int userID = (user.getUserId());
		User findUser = userRepository.findByUserId(userID);
		if (findUser != null) {
			findUser.setName(user.getName());
			findUser.setLastModifiedDateTime(LocalDateTime.now());
			userRepository.save(findUser);
			return "User updated successfully";
		} else
			throw new NotFoundException("User not found");
	}

	/**
	 * deleting the user by passing userID
	 */
	@Override
	public String deleteUser(int userId) throws NotFoundException {

		User user = userRepository.findByUserId(userId);
		if (user != null) {
			userRepository.deleteById(userId);
			return "UserID " + userId + " deleted successfully";
		} else
			throw new NotFoundException("User not found!!");
	}

	/**
	 * Assigning another user to the ticket
	 */
	@Override
	public String setAssignee(int ticketId, int userId, int adminId) throws NotFoundException {

		Ticket findTicket = ticketRepository.findByTicketId((ticketId));
		Viewticket viewticket = viewticketRepository.findByTicketId(ticketId);
		adminRepository.findById(adminId).orElseThrow(() -> new NotFoundException("ID Not found"));
		User findUser = userRepository.findByUserId(userId);

		if (findTicket != null && findUser != null ) {
			findTicket.setAssigneeId(userId);
			viewticket.setAssignee(findUser.getName());
			findTicket.setLastModifiedDateTime(LocalDateTime.now());
			ticketRepository.save(findTicket);
			return "Assignee added successfully";
		} else
			throw new NotFoundException("Enter valid details");
	}

	/**
	 * Updating the status of the ticket
	 */
	@Override
	public String changeStatus(int ticketId, int statusId, int adminId) throws NotFoundException, NotValidException {

		Ticket findTicket = ticketRepository.findByTicketId((ticketId));

		if (findTicket.getStatusId() == 4)
			throw new NotValidException("Cannot change the status !!");

		int statusID = findTicket.getStatusId();
		String status = statusRepository.findById(statusID).get().getStatus();
		Status findByStatusId = statusRepository.findById((statusId)).orElse(null);
		Admin_team findByAdminId = adminRepository.findById((adminId)).orElse(null);

		if (findTicket != null && findByAdminId != null && findByStatusId != null) {
			findTicket.setStatusId((statusId));
			findTicket.setLastModifiedDateTime(LocalDateTime.now());
			ticketRepository.save(findTicket);
			return "Status changed from <" + status + "> to <" + findByStatusId.getStatus() + "> successfully";

		} else
			throw new NotFoundException();
	}

	/**
	 * Updating the priority of a ticket
	 * 
	 * @throws NotValidException
	 */
	@Override
	public String changePriority(int ticketId, int priorityId, int adminId)
			throws NotFoundException, NotValidException {

		Ticket findTicket = ticketRepository.findByTicketId((ticketId));

		Priority findByPriorityId = priorityRepository.findById((priorityId))
				.orElseThrow(() -> new NotFoundException("priorityID not found"));

		Admin_team findByAdminId = adminRepository.findById((adminId))
				.orElseThrow(() -> new NotFoundException("adminID not found"));

		if (findTicket.getStatusId() == 1) {
			if (findTicket != null && findByAdminId != null) {
				findTicket.setPriorityId((priorityId));
				findTicket.setLastModifiedDateTime(LocalDateTime.now());
				ticketRepository.save(findTicket);
				return "Priority changed from <Low> to <" + findByPriorityId.getPriority() + "> successfully";
			} else
				throw new NotFoundException();
		} else
			throw new NotValidException("Cannot change the priority");

	}

	/**
	 * Adding a comment into the comment table
	 */
	@Override
	public String addComment(Comment comment, int ticketId, int userId) throws NotValidException, NotFoundException {
		Comment checkComment = commentRepository.findByTicketIdAndUserId(ticketId, userId);

		if (checkComment != null) {
			throw new NotValidException("Comment already exists");
		} else {

			Ticket Ticket = ticketRepository.findByTicketId(ticketId);
			User user = userRepository.findByUserId(userId);

			if (Ticket != null && user != null) {

				comment.setTicketId((ticketId));
				comment.setUserId((userId));
				comment.setMessage(comment.getMessage());
				commentRepository.save(comment);
				return "Comment added successfully";
			} else
				throw new NotFoundException();
		}

	}

	/**
	 * deleting the ticket based on ticketID and userID
	 */
	@Override
	public String deleteTicket(int ticketId, int userId) throws NotFoundException, NotValidException {

		Ticket findTicket = ticketRepository.findByTicketIdAndReportedId((ticketId), (userId));
		if (findTicket != null) {
			LocalDateTime older = LocalDateTime.now().minusDays(7);
			if (findTicket.getStatusId() == 4 && findTicket.getLastModifiedDateTime().isBefore(older)) {
				ticketRepository.deleteById((ticketId));
				return "Ticket " + ticketId + " deleted successfully";
			} else
				throw new NotValidException("Cannot delete ticket");

		} else
			throw new NotFoundException();
	}

	/**
	 * Fetching the list of all the tickets in the database
	 */
	@Override
	public List<Viewticket> viewAllTickets() {
		return viewticketRepository.findAll();
	}

	/**
	 * Filtering the list of tickets based on ticketID
	 */
	@Override
	public Viewticket viewById(int ticketId) throws NotFoundException {

		Ticket ticket = ticketRepository.findByTicketId((ticketId));
		if (ticket != null)
			return viewticketRepository.findByTicketId(ticketId);
		else
			throw new NotFoundException();
	}

}

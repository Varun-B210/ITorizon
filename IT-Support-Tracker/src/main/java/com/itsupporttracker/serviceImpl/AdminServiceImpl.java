package com.itsupporttracker.serviceImpl;

import java.time.LocalDateTime;	
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.itsupporttracker.ViewTicketDAO;
import com.itsupporttracker.Viewticket;
import com.itsupporttracker.entity.Admin_team;
import com.itsupporttracker.entity.Assignee;
import com.itsupporttracker.entity.Comment;
import com.itsupporttracker.entity.Priority;
import com.itsupporttracker.entity.Status;
import com.itsupporttracker.entity.Ticket;
import com.itsupporttracker.entity.User;
import com.itsupporttracker.exception.EmailExistsException;
import com.itsupporttracker.exception.NotValidException;
import com.itsupporttracker.exception.UserNotFoundException;
import com.itsupporttracker.repository.AdminRepository;
import com.itsupporttracker.repository.AssigneeRepository;
import com.itsupporttracker.repository.CommentRepository;
import com.itsupporttracker.repository.PriorityRepository;
import com.itsupporttracker.repository.StatusRepository;
import com.itsupporttracker.repository.TicketRepository;
import com.itsupporttracker.repository.UserRepository;
import com.itsupporttracker.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TicketRepository ticketRepository;
	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private AssigneeRepository assigneeRepository;
	@Autowired
	private StatusRepository statusRepository;
	@Autowired
	private PriorityRepository priorityRepository;
	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	ViewTicketDAO viewTicketDAO;
	
	@Override
	public String addUser(User user) throws EmailExistsException, UserNotFoundException, NotValidException {
		
		String regex = "[\\w-]{1,20}@\\w{2,20}\\.\\w{2,3}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(user.getEmailId());
		boolean isEmailValid = matcher.matches();
		if (isEmailValid) {

			User existingUser = userRepository.findByEmailId(user.getEmailId());
			if (existingUser != null && existingUser.getEmailId().contains(user.getEmailId()))
				throw new EmailExistsException("Email already exits! Enter a unique EmailID");
			else {
				
				user.setCreateDateTime(LocalDateTime.now());
				user.setLastModifiedDateTime(LocalDateTime.now());
				userRepository.save(user);
				return "User added successfully";
			}
		} else
			throw new NotValidException("Not Valid");

	}

	@Override
	public List<User> viewAllUsers() {

		return userRepository.findAll();
	}

	@Override
	public User viewUserId(int userId) throws UserNotFoundException {
		User userById = userRepository.findById(userId).orElse(null);
		if (userById != null)
			return userById;
		else
			throw new UserNotFoundException("User " + userId + " not found");
	}

	@Override
	public User viewUserEmail(String emailId) throws UserNotFoundException {
		User userEmail = userRepository.findByEmailId(emailId);
		if (userEmail != null)
			return userEmail;
		else
			throw new UserNotFoundException("User " + emailId + " not found");
	}

	@Override
	public User viewUser(int userId, String emailId) throws UserNotFoundException {
		
		User user = userRepository.findByUserIdAndEmailId(userId, emailId);
		if (user != null)
			return user;
		else
			throw new UserNotFoundException("User not found!!");
	}

	@Override
	public String updateUser(User user) throws UserNotFoundException {
		
			User userID = userRepository.findByUserId(user.getUserId());
		if (userID != null ) {
			User updateuser = userRepository.findById(user.getUserId()).orElse(null);
			updateuser.setName(user.getName());
			updateuser.setLastModifiedDateTime(LocalDateTime.now());
			userRepository.save(updateuser);
			return "User updated successfully";
			
		} else
			throw new UserNotFoundException("User not found!!");
		
	}

	@Override
	public String deleteUser(String userId) throws UserNotFoundException {
		try {
		int user = userRepository.findByUserId(Integer.valueOf(userId)).getUserId();
		if (Integer.valueOf(userId) == user) {
			userRepository.deleteById(Integer.valueOf(userId));
			return "UserID " + userId + " deleted successfully";
		} else
			throw new UserNotFoundException("User not found!!");
		}catch(Exception e) {
			throw new UserNotFoundException("User not found");
		}
	}
	
	@Override
	public String setAssignee(int ticketId, int userId, int adminId, int assigneeId) throws UserNotFoundException {

		Ticket findTicket = ticketRepository.findByTicketIdAndReportedId(ticketId, userId);
		Admin_team findByAdminId = adminRepository.findById(adminId).orElse(null);
		Assignee findByAssigneeId = assigneeRepository.findById(assigneeId).orElse(null);

		if (findTicket != null && findByAdminId != null && findByAssigneeId != null) {
			findTicket.setAssigneeId(assigneeId);
			findTicket.setLastModifiedDateTime(LocalDateTime.now());
			ticketRepository.save(findTicket);
			return "Assignee added successfully";
		} else
			throw new UserNotFoundException("Ticket not found");
	}

	@Override
	public String changeStatus(int ticketId, int statusId, int adminId) throws UserNotFoundException {

		Ticket findTicket = ticketRepository.findByTicketId(ticketId);
		Status findByStatusId = statusRepository.findById(statusId).orElse(null);
		Admin_team findByAdminId = adminRepository.findById(adminId).orElse(null);

		if (findTicket != null && findByAdminId != null && findByStatusId != null) {
			findTicket.setStatusId(statusId);
			findTicket.setLastModifiedDateTime(LocalDateTime.now());
			ticketRepository.save(findTicket);
			return "Status changed to " + findByStatusId.getStatus() + " successfully";
		} else
			throw new UserNotFoundException("Ticket not found");
	}

	@Override
	public String changePriority(int ticketId, int priorityId, int adminId) throws UserNotFoundException {

		Ticket findTicket = ticketRepository.findByTicketId(ticketId);
		Priority findByPriorityId = priorityRepository.findById(priorityId).orElse(null);
		Admin_team findByAdminId = adminRepository.findById(adminId).orElse(null);

		if (findTicket != null && findByAdminId != null && findByPriorityId != null) {
			findTicket.setPriorityId(priorityId);
			findTicket.setLastModifiedDateTime(LocalDateTime.now());
			ticketRepository.save(findTicket);
			return "Priority changed to " + findByPriorityId.getPriority() + " successfully";
		} else
			throw new UserNotFoundException("Ticket not found");
	}

	@Override
	public String addComment(String message, int ticketId, int userId) throws UserNotFoundException {
		try {
		Comment comment = new Comment();
		Ticket byuserId = ticketRepository.findByTicketIdAndReportedId((ticketId),(userId));
		if( byuserId ==null) {
			throw new UserNotFoundException("contents not found");
			
		} else {
			comment.setTicketId((ticketId));
			comment.setUserId((userId));
			comment.setMessage(message);
			commentRepository.save(comment);
			return "Comment added successfully";
		}
			
		}catch(Exception e) {
			throw new UserNotFoundException("Enter valid details");
		}
	}

	@Override
	public String deleteTicket(int ticketId, int userId) throws UserNotFoundException, NotValidException {
		try {
		Ticket findTicket = ticketRepository.findByTicketIdAndReportedId(Integer.valueOf(ticketId), Integer.valueOf(userId));
		if (findTicket != null) {
			LocalDateTime older = LocalDateTime.now().minusDays(7);
			if (findTicket.getStatusId() == 4 && findTicket.getLastModifiedDateTime().isBefore(older)) {
				ticketRepository.deleteById((ticketId));
				return "Ticket " + ticketId + " deleted successfully";
			} else
				throw new NotValidException("Cannot delete ticket");

		} else
			throw new UserNotFoundException("Ticket not found");
		}catch(Exception e) {
			throw new NotValidException("Not valid");
		}
	}

	@Override
	public List<Viewticket> viewAllTickets() {

		return viewTicketDAO.viewAllTickets();
	}

	@Override
	public Viewticket viewById(String ticketId) throws UserNotFoundException, NotValidException {
		return viewTicketDAO.findByID(ticketId);

	}

}

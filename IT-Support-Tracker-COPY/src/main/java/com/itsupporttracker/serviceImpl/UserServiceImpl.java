package com.itsupporttracker.serviceImpl;

import java.time.LocalDateTime;	
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.itsupporttracker.entity.Comment;
import com.itsupporttracker.entity.Sub_category;
import com.itsupporttracker.entity.Ticket;
import com.itsupporttracker.entity.User;
import com.itsupporttracker.entity.Viewticket;
import com.itsupporttracker.exception.NotValidException;
import com.itsupporttracker.exception.NotFoundException;
import com.itsupporttracker.repository.CategoryRepository;
import com.itsupporttracker.repository.CommentRepository;
import com.itsupporttracker.repository.PriorityRepository;
import com.itsupporttracker.repository.StatusRepository;
import com.itsupporttracker.repository.SubCategoryRepository;
import com.itsupporttracker.repository.TicketRepository;
import com.itsupporttracker.repository.UserRepository;
import com.itsupporttracker.repository.ViewticketRepository;
import com.itsupporttracker.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	/**
	 * Adding dependencies to the class object
	 */
	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SubCategoryRepository subCategoryRepository;

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private PriorityRepository priorityRepository;

	@Autowired
	private StatusRepository statusRepository;

	@Autowired
	private ViewticketRepository viewticketRepository;

	/**
	 * User can create a ticket by passing the required details
	 */
	@Override
	public String createTicket(Ticket ticket, int userId) throws NotFoundException, 
	NotValidException {
			
			ticket.setStatusId(1);
			
			ticket.setReportedId(userId);
			
			//ticket.setAssigneeId("Not Assigned");
			
			ticket.setCreateDateTime(LocalDateTime.now());
			
			ticket.setLastModifiedDateTime(LocalDateTime.now());

			Sub_category find = subCategoryRepository.findByCategoryIdAndSubCategoryId
			((ticket.getCategoryId()),(ticket.getSubCategoryId()));
			User checkUser = userRepository.findByUserId(userId);
			priorityRepository.findById(ticket.getPriorityId()).orElseThrow(()->
			new NotFoundException("priorityID not found"));
			if (checkUser != null && find != null) {
				
				ticketRepository.save(ticket);

				Ticket user = ticketRepository.findByTicketIdAndReportedId(ticket.getTicketId(), userId);
				
				/**
				 * Adding associated data to a new table to fetch later when called
				 */
				
				int categoryID = user.getCategoryId();
				int subCategoryID = user.getSubCategoryId();
				int statusID = user.getStatusId();
				int priorityID = user.getPriorityId();

				Viewticket viewticket = new Viewticket();
				
				viewticket.setTicketId(user.getTicketId());
				
				viewticket.setUserId(userId);
				
				viewticket.setCategoryDesc(categoryRepository.findById(categoryID).get().getCategoryDesc());
				
				viewticket.setSubCategoryDesc(subCategoryRepository.findById(subCategoryID).get().getSubCategoryDesc());
				
				viewticket.setSubject(user.getSubject());
				
				viewticket.setPriority(priorityRepository.findById(priorityID).get().getPriority());
				
				viewticket.setStatus(statusRepository.findById(statusID).get().getStatus());
				
				viewticket.setAssignee("Not Assigned");
				
				viewticket.setLink("http://localhost:8080/user/viewByTicketID?ticketId=" + ticket.getTicketId()
						+ "&userId=" + userId);
				
				viewticketRepository.save(viewticket);

				return "Ticket added successfully"
						+ "\nView Ticket -> http://localhost:8080/user/viewByTicketID?ticketId=" + ticket.getTicketId()
						+ "&userId=" + userId;
			} else {
				throw new NotFoundException("Enter valid inputs");
			}
	}

	
	/**
	 * Any User can add comments to any ticket
	 */
	@Override
	public String addComment(Comment comment, int ticketId, int userId) throws 	
	NotFoundException, NotValidException {
		
			Ticket ticket = ticketRepository.findByTicketId(ticketId);
			User user = userRepository.findByUserId(userId);

			if (ticket != null && user != null) {
				comment.setTicketId(ticketId);
				comment.setUserId(userId);
				comment.setMessage(comment.getMessage());
				commentRepository.save(comment);
				return "Comment added successfully";
			}
				else
					throw new NotFoundException("Not found");
	}
	
	/**
	 * Fetching the list of tickets based on userID
	 */
	@Override
	public List<Viewticket> findAllByUserId(int userId) throws NotFoundException {
		
			if (viewticketRepository.findAllByUserId(userId).isEmpty())
				throw new NotFoundException("Ticket not found");
			else
				return viewticketRepository.findAllByUserId(userId);

	}

	/**
	 * Fetching a particular ticket from the list of tickets
	 */
	@Override
	public Viewticket findByTicketId(int ticketId, int userId) throws NotFoundException {
		
			Viewticket ticket = viewticketRepository.findByTicketIdAndUserId(ticketId, userId);
			if (ticket != null)
				return ticket;
			else
				throw new NotFoundException("Ticket not found");
		}
	}


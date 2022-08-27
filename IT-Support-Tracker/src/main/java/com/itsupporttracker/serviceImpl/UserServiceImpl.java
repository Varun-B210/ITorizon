package com.itsupporttracker.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.itsupporttracker.ViewTicketDAO;
import com.itsupporttracker.Viewticket;
import com.itsupporttracker.entity.Comment;
import com.itsupporttracker.entity.Message;
import com.itsupporttracker.entity.Sub_category;
import com.itsupporttracker.entity.Ticket;
import com.itsupporttracker.exception.NotValidException;
import com.itsupporttracker.exception.UserNotFoundException;
import com.itsupporttracker.repository.CommentRepository;
import com.itsupporttracker.repository.SubCategoryRepository;
import com.itsupporttracker.repository.TicketRepository;
import com.itsupporttracker.repository.UserRepository;
import com.itsupporttracker.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private TicketRepository ticketRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private SubCategoryRepository subCategoryRepository;
	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	ViewTicketDAO viewTicketDAO;

	

	@Override
	public String createTicket(Ticket ticket, int userId) throws UserNotFoundException {
		ticket.setStatusId(1);
		ticket.setReportedId(userId);
		ticket.setAssigneeId(1);
		ticket.setCreateDateTime(LocalDateTime.now());
		ticket.setLastModifiedDateTime(LocalDateTime.now());
		Sub_category find = subCategoryRepository.findByCategoryIdAndSubCategoryId(ticket.getCategoryId(),
				ticket.getSubCategoryId());
		if (userId == userRepository.findByUserId(userId).getUserId() && find != null) {
			
			ticketRepository.save(ticket);
			return "Ticket added successfully";
		}
		else {
			throw new UserNotFoundException("Enter valid inputs");
		}
	}

	@Override
	public String addComment(String message, int ticketId, int userId) throws UserNotFoundException {
		
		Ticket byuserId = ticketRepository.findByTicketIdAndReportedId((ticketId),(userId));
				if (byuserId != null ) {
					Comment comment = new Comment();
					comment.setTicketId(ticketId);
					comment.setUserId(userId);
					comment.setMessage(message);
			commentRepository.save(comment);
			return "Comment added successfully";
		} else
			throw new UserNotFoundException("contents not found");
	}

	@Override
	public List<Viewticket> viewAllTickets() {
		return viewTicketDAO.viewAllTickets();
	}

	@Override
	public Viewticket viewByID(String ticketId) throws UserNotFoundException, NotValidException {
		return viewTicketDAO.findByID(ticketId);
	}

	@Override
	public ResponseEntity<Message> getComment() {
		Message message = new Message("Not Valid");
		return ResponseEntity.badRequest().body(message);
	}

}
package com.itsupporttracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;	
import com.itsupporttracker.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{
	/**
	 * Different methods to fetch data from database
	 */
	Comment findByTicketId(int ticketId);
	Comment findByTicketIdAndUserId(int ticketId, int userId);
	List<Comment> findAllByTicketId(int ticketId);
}

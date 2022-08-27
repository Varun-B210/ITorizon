package com.itsupporttracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;	
import com.itsupporttracker.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{
	
	Comment findByTicketId(int ticketId);
}

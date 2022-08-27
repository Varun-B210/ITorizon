package com.itsupporttracker.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itsupporttracker.entity.Assignee;
import com.itsupporttracker.entity.Category;
import com.itsupporttracker.entity.Priority;
import com.itsupporttracker.entity.Status;
import com.itsupporttracker.entity.Sub_category;
import com.itsupporttracker.entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer>{
	
	Ticket findByReportedId(int reportedId);
	Ticket findByTicketId(int ticketId);
	Ticket findByTicketIdAndReportedId(int ticketId, int userId);
	Ticket findByCategoryId(int categoryId);
	Sub_category findBySubCategoryId(int subCategoryId);
	Status findByStatusId(int statusId);
	Ticket findByPriorityId(int priorityId);
	Assignee findByAssigneeId(int assigneeId);
}

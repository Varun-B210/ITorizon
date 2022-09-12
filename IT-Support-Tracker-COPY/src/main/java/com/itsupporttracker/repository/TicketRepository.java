package com.itsupporttracker.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.itsupporttracker.entity.Status;
import com.itsupporttracker.entity.Sub_category;
import com.itsupporttracker.entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
	/**
	 * Added different methods to fetch data based on the 
	 * required parameters
	 */

	Ticket findByReportedId(int reportedId);

	Ticket findByTicketId(int ticketId);

	List<Ticket> findAllByReportedId(int ticketId);

	Ticket findByTicketIdAndReportedId(int ticketId, int userId);

	Ticket findByCategoryId(int categoryId);

	Sub_category findBySubCategoryId(int subCategoryId);

	Status findByStatusId(int statusId);

	Ticket findByPriorityId(int priorityId);
}

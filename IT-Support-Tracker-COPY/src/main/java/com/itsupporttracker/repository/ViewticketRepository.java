package com.itsupporttracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.itsupporttracker.entity.Viewticket;

public interface ViewticketRepository extends JpaRepository<Viewticket, Integer> {

	/**
	 * Added different methods to fetch data based on the 
	 * required parameters
	 */
	List<Viewticket> findAllByUserId(int userId);
	
	Viewticket findByTicketIdAndUserId(int ticketId, int userId);

	Viewticket findByTicketId(int ticketId);

}

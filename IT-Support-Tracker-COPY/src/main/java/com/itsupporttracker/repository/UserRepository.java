package com.itsupporttracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.itsupporttracker.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	/**
	 * Added different methods to fetch data based on the 
	 * required parameters
	 */
	User findByEmailId(String emailId);
	
	User findByUserIdAndEmailId(int userId, String emailId);
	
	User findByUserId(int userId);
	
	

}

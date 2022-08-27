package com.itsupporttracker.repository;

import java.util.List;	

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.itsupporttracker.Viewticket;
import com.itsupporttracker.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByEmailId(String emailId);
	User findByUserIdAndEmailId(int userId, String emailId);
	User findByUserId(int userId);
	
	

}

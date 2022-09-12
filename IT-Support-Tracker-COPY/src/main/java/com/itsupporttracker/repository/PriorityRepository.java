package com.itsupporttracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itsupporttracker.entity.Priority;

public interface PriorityRepository extends JpaRepository<Priority, Integer>{

}

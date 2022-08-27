package com.itsupporttracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itsupporttracker.entity.Assignee;

public interface AssigneeRepository extends JpaRepository<Assignee, Integer>{

}

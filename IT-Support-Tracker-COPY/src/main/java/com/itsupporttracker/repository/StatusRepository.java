package com.itsupporttracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itsupporttracker.entity.Status;

public interface StatusRepository extends JpaRepository<Status, Integer>{

}

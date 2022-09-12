package com.itsupporttracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itsupporttracker.entity.Admin_team;

public interface AdminRepository extends JpaRepository<Admin_team, Integer> {

}

package com.itsupporttracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itsupporttracker.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

}

package com.itsupporttracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itsupporttracker.entity.Sub_category;

public interface SubCategoryRepository extends JpaRepository<Sub_category, Integer>{
	
	Sub_category findByCategoryIdAndSubCategoryId(int categoryId, int subCategoryId);
}

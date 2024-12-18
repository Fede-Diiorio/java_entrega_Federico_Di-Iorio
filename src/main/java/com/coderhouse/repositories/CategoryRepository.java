package com.coderhouse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderhouse.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
	Category findByName(String name);
}

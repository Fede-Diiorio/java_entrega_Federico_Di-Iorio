package com.coderhouse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderhouse.models.ProductCategory;

public interface CategoryRepository extends JpaRepository<ProductCategory, Long>{

}

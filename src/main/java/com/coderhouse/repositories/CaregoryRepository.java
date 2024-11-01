package com.coderhouse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderhouse.models.ProductCategory;

public interface CaregoryRepository extends JpaRepository<ProductCategory, Long>{

}

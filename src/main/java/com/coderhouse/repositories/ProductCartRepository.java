package com.coderhouse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderhouse.models.ProductCart;

public interface ProductCartRepository extends JpaRepository<ProductCart, Long>{

}

package com.coderhouse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderhouse.models.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

}

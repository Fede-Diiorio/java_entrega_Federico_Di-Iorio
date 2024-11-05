package com.coderhouse.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderhouse.models.Cart;
import com.coderhouse.models.Product;
import com.coderhouse.models.ProductCart;

public interface ProductCartRepository extends JpaRepository<ProductCart, Long>{

	Optional<ProductCart> findByCartAndProduct(Cart cart, Product product);
}

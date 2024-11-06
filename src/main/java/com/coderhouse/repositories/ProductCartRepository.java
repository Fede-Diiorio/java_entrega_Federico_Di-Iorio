package com.coderhouse.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.coderhouse.models.Cart;
import com.coderhouse.models.Product;
import com.coderhouse.models.ProductCart;

import jakarta.transaction.Transactional;

public interface ProductCartRepository extends JpaRepository<ProductCart, Long> {

	Optional<ProductCart> findByCartAndProduct(Cart cart, Product product);

	List<ProductCart> findByCartId(Long cart);

	@Transactional
	@Modifying
	@Query("DELETE FROM ProductCart pc WHERE pc.cart.id = :cartId")
	void deleteByCartId(long cartId);
}

package com.coderhouse.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.models.Cart;
import com.coderhouse.repositories.CartRepository;

@Service
public class CartService {

	@Autowired
	private CartRepository cartRepository;

	public List<Cart> getAllCarts() {
		return cartRepository.findAll();
	}

	public Cart findById(Long id) {
		return cartRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Carrito no encontrado"));
	}

	public Cart saveCart(Cart cart) {
		return cartRepository.save(cart);
	}
}

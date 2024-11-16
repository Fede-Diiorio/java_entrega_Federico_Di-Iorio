package com.coderhouse.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.models.Cart;
import com.coderhouse.repositories.CartRepository;

import jakarta.transaction.Transactional;

@Service
public class CartService {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private ProductCartService productCartService;

	public List<Cart> getAll() {
		return cartRepository.findAll();
	}

	public Cart getById(Long id) {
		return cartRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Carrito no encontrado"));
	}

	@Transactional
	public Cart save(Cart object) {
		return cartRepository.save(object);
	}

	@Transactional
	public Cart clearCart(Long id) {
		Cart cart = getById(id);

		productCartService.deleteProductCartByCartId(id);

		return cartRepository.save(cart);
	}

}

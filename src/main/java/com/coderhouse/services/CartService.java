package com.coderhouse.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.dtos.CartResDTO;
import com.coderhouse.mapper.CartMapper;
import com.coderhouse.models.Cart;
import com.coderhouse.repositories.CartRepository;

import jakarta.transaction.Transactional;

@Service
public class CartService {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private CartMapper cartMapper;

	public List<CartResDTO> getAll() {
		return cartRepository.findAll().stream().map(cartMapper::toDTO).toList();
	}

	public CartResDTO getById(Long id) {
		Cart cart = getCartById(id);
		return cartMapper.toDTO(cart);
	}

	public Cart getCartById(Long id) {
		return cartRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Carrito no encontrado"));
	}

	@Transactional
	public Cart save(Cart object) {
		return cartRepository.save(object);
	}

}

package com.coderhouse.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.dtos.CartResDTO;
import com.coderhouse.dtos.ProductOnCartDTO;
import com.coderhouse.mapper.CartMapper;
import com.coderhouse.models.Cart;
import com.coderhouse.repositories.CartRepository;

import jakarta.transaction.Transactional;

@Service
public class CartService {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private ProductCartService productCartService;
	
	@Autowired
	private CartMapper cartMapper;

	public List<CartResDTO> getAll() {
	    return cartRepository.findAll().stream()
	            .map(cartMapper::toDTO)
	            .toList(); 
	}

	public CartResDTO getById(Long id) {
		Cart cart = cartRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Carrito no encontrado"));
		return cartMapper.toDTO(cart);
	}

	@Transactional
	public Cart save(Cart object) {
		return cartRepository.save(object);
	}

	@Transactional
	public Cart clearCart(Long id) {
		Cart cart = cartRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Carrito no encontrado"));;

		productCartService.deleteProductCartByCartId(id);

		return cartRepository.save(cart);
	}


}

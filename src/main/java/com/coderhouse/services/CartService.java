package com.coderhouse.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.dtos.CartDTO;
import com.coderhouse.dtos.ProductOnCartDTO;
import com.coderhouse.models.Cart;
import com.coderhouse.repositories.CartRepository;

import jakarta.transaction.Transactional;

@Service
public class CartService {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private ProductCartService productCartService;

	public List<CartDTO> getAll() {
	    return cartRepository.findAll().stream()
	            .map(this::mapToCartDTO)
	            .toList(); 
	}

	public CartDTO getById(Long id) {
		Cart cart = cartRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Carrito no encontrado"));
		return mapToCartDTO(cart);
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

	private CartDTO mapToCartDTO(Cart cart) {
	    CartDTO cartDTO = new CartDTO();
	    cartDTO.setId(cart.getId());
	    cartDTO.setProducts(
	        cart.getProducts().stream()
	            .map(productCart -> {
	                ProductOnCartDTO productDTO = new ProductOnCartDTO();
	                productDTO.setId(productCart.getProduct().getId());
	                productDTO.setName(productCart.getProduct().getName());
	                productDTO.setUnitPrice(productCart.getProduct().getPrice());
	                productDTO.setQuantity(productCart.getQuantity());
	                productDTO.setTotalPrice(productCart.getQuantity() * productCart.getProduct().getPrice());
	                return productDTO;
	            })
	            .toList()
	    );
	    return cartDTO;
	}

}

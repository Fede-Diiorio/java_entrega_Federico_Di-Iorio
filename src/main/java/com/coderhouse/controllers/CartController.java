package com.coderhouse.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderhouse.models.Cart;
import com.coderhouse.services.CartService;

@RestController
@RequestMapping("/api/carts")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@GetMapping
	public ResponseEntity<List<Cart>> getAllCarts() {
		try {
			List<Cart> carts = cartService.getAllCarts();
			return ResponseEntity.ok(carts);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cart> findById(@PathVariable long id) {
		try {
			Cart cart = cartService.findById(id);
			return ResponseEntity.ok(cart);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PostMapping
	public ResponseEntity<Cart> saveCart(@RequestBody Cart cart) {
		try {
			Cart createdCart = cartService.saveCart(cart);
			return ResponseEntity.ok(createdCart);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}

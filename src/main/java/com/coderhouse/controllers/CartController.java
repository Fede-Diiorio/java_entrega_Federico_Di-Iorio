package com.coderhouse.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderhouse.dto.QuantityDTO;
import com.coderhouse.models.Cart;
import com.coderhouse.models.ProductCart;
import com.coderhouse.models.Ticket;
import com.coderhouse.services.CartService;
import com.coderhouse.services.ProductCartService;
import com.coderhouse.services.TicketService;

@RestController
@RequestMapping("/api/carts")
public class CartController {

	@Autowired
	private CartService cartService;

	@Autowired
	private ProductCartService productCartService;

	@Autowired
	private TicketService ticketService;

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

	@PostMapping("/{cartId}/product/{productId}")
	public ResponseEntity<ProductCart> addProductToCart(@PathVariable long cartId, @PathVariable long productId,
			@RequestBody QuantityDTO quantityDTO) {
		try {
			int quantity = quantityDTO.getQuantity();
			ProductCart productCart = productCartService.addProductToCart(cartId, productId, quantity);
			return ResponseEntity.ok(productCart);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DeleteMapping("/{cartId}/product/{productId}")
	public ResponseEntity<Void> deleteProductInCart(@PathVariable long cartId, @PathVariable long productId) {
		try {
			productCartService.deleteProductInCart(cartId, productId);
			return ResponseEntity.noContent().build();
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PostMapping("/{cartId}/ticket")
	public ResponseEntity<Ticket> createTicket(@PathVariable long cartId) {
		try {
			Ticket ticket = ticketService.saveTicket(cartId);
			return ResponseEntity.ok(ticket);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DeleteMapping("/{cartId}")
	public ResponseEntity<Cart> clearCart(@PathVariable Long cartId) {
		try {
			cartService.clearCart(cartId);
			Cart cart = cartService.findById(cartId);
			return ResponseEntity.ok(cart);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}

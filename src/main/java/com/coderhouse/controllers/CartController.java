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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/carts")
@Tag(name = "Carts", description = "Operaciones relacionadas con carritos")
public class CartController {

	@Autowired
	private CartService cartService;

	@Autowired
	private ProductCartService productCartService;

	@Autowired
	private TicketService ticketService;

	@Operation(summary = "Mostrar todos los carritos")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Carritos encontrados con exito", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Cart.class)) }),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
	})
	@GetMapping
	public ResponseEntity<List<Cart>> getAllCarts() {
		try {
			List<Cart> carts = cartService.getAllCarts();

			return ResponseEntity.ok(carts);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@Operation(summary = "Econtrar carrito por ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Carrito encontrado con éxito", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Cart.class)) }),
			@ApiResponse(responseCode = "404", description = "Carrito no encontrado por ID inválido", content = @Content),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content) })
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

	@Operation(summary = "Agrega un producto al carrito")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Producto agregado con éxito", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Cart.class)) }),
			@ApiResponse(responseCode = "404", description = "Producto o carrito no encontrado según su ID", content = @Content),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content) })
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

	@Operation(summary = "Elimina un producto del carrito")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Producto eliminado con éxito", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Cart.class)) }),
			@ApiResponse(responseCode = "404", description = "Producto o carrito no encontrado según su ID", content = @Content),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content) })
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

	@Operation(summary = "Genera un nuevo ticket y vacia el carrito")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Ticket creado de forma correcta", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Ticket.class)) }),
			@ApiResponse(responseCode = "404", description = "Carrito no encontrado según su ID o carrito vacio", content = @Content),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content) })
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

	@Operation(summary = "Vaciar el carrito por ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Carrito vaciado exitosamente", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Cart.class)) }),
			@ApiResponse(responseCode = "404", description = "Carrito no encontrado según su ID o carrito vacio", content = @Content),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content) })
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

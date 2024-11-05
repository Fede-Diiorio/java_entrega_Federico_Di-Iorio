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

import com.coderhouse.dto.QuantityDTO;
import com.coderhouse.models.ProductCart;
import com.coderhouse.services.ProductCartService;

@RestController
@RequestMapping("/api/products-carts")
public class ProductCartController {

	@Autowired
	private ProductCartService productCartService;

	@GetMapping
	public ResponseEntity<List<ProductCart>> getAllInvoiceDetails() {
		try {
			List<ProductCart> details = productCartService.getAllProductCart();
			return ResponseEntity.ok(details);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductCart> findById(@PathVariable long id) {
		try {
			ProductCart details = productCartService.findById(id);
			return ResponseEntity.ok(details);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@PostMapping("/{cid}/product/{pid}")
	public ResponseEntity<ProductCart> addProductToCart(@PathVariable long cid, @PathVariable long pid,
	        @RequestBody QuantityDTO quantityDTO) {
	    try {
	        int quantity = quantityDTO.getQuantity();
	        ProductCart productCart = productCartService.addProductToCart(cid, pid, quantity);
	        return ResponseEntity.ok(productCart);
	    } catch (IllegalArgumentException e) {
	        return ResponseEntity.notFound().build();
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	}


}

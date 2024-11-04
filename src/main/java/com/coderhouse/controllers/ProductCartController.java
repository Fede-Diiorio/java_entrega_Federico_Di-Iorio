package com.coderhouse.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderhouse.dto.ProductCartDTO;
import com.coderhouse.models.ProductCart;
import com.coderhouse.services.ProductCartService;

@RestController
@RequestMapping("/api/invoice-details")
public class ProductCartController {

	@Autowired
	private ProductCartService invoiceDetailsService;

	@GetMapping
	public ResponseEntity<List<ProductCart>> getAllInvoiceDetails() {
		try {
			List<ProductCart> details = invoiceDetailsService.getAllInvoicesDetails();
			return ResponseEntity.ok(details);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductCart> findById(@PathVariable long id) {
		try {
			ProductCart details = invoiceDetailsService.findById(id);
			return ResponseEntity.ok(details);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@PostMapping
	public ResponseEntity<ProductCart> saveInvoiceDetails(@RequestBody ProductCartDTO details) {
		try {
			ProductCart invoiceDetails = invoiceDetailsService.saveInvoicesDetails(details);
			return ResponseEntity.ok(invoiceDetails);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProductCart> updateInvoiceDetails(@PathVariable Long id,
			@RequestBody ProductCart details) {
		try {
			ProductCart invoiceDetails = invoiceDetailsService.updateInvoicesDetails(id, details);
			return ResponseEntity.ok(invoiceDetails);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}

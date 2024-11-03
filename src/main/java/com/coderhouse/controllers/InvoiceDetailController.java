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

import com.coderhouse.dto.InvoiceDetailDTO;
import com.coderhouse.models.InvoiceDetail;
import com.coderhouse.services.InvoiceDetailsService;

@RestController
@RequestMapping("/api/invoice-details")
public class InvoiceDetailController {

	@Autowired
	private InvoiceDetailsService invoiceDetailsService;

	@GetMapping
	public ResponseEntity<List<InvoiceDetail>> getAllInvoiceDetails() {
		try {
			List<InvoiceDetail> details = invoiceDetailsService.getAllInvoicesDetails();
			return ResponseEntity.ok(details);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@GetMapping("/{id}")
	public ResponseEntity<InvoiceDetail> findById(@PathVariable long id) {
		try {
			InvoiceDetail details = invoiceDetailsService.findById(id);
			return ResponseEntity.ok(details);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@PostMapping
	public ResponseEntity<InvoiceDetail> saveInvoiceDetails(@RequestBody InvoiceDetailDTO details) {
		try {
			InvoiceDetail invoiceDetails = invoiceDetailsService.saveInvoicesDetails(details);
			return ResponseEntity.ok(invoiceDetails);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}

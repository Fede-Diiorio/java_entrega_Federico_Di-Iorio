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

import com.coderhouse.dto.InvoiceDTO;
import com.coderhouse.models.Invoice;
import com.coderhouse.services.InvoiceService;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

	@Autowired
	private InvoiceService invoiceService;

	@GetMapping
	public ResponseEntity<List<Invoice>> getAllInvoices() {
		try {
			List<Invoice> invoices = invoiceService.getAllInvoices();
			return ResponseEntity.ok(invoices);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Invoice> getInvoiceById(@PathVariable long id) {
		try {
			Invoice invoice = invoiceService.findById(id);
			return ResponseEntity.ok(invoice);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PostMapping
	public ResponseEntity<Invoice> createInvoice(@RequestBody InvoiceDTO invoice) {
		try {
			Invoice createdInvoice = invoiceService.createInvoice(invoice);
			return ResponseEntity.ok(createdInvoice);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}

//package com.coderhouse.controllers;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.coderhouse.dto.TicketDTO;
//import com.coderhouse.models.Ticket;
//import com.coderhouse.services.TicketService;
//import com.coderhouse.services.TicketServices;
//
//@RestController
//@RequestMapping("/api/invoices")
//public class TicketController {
//
//	@Autowired
//	private TicketService invoiceService;
//
//	@GetMapping
//	public ResponseEntity<List<Ticket>> getAllInvoices() {
//		try {
//			List<Ticket> invoices = invoiceService.getAllInvoices();
//			return ResponseEntity.ok(invoices);
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//		}
//	}
//
//	@GetMapping("/{id}")
//	public ResponseEntity<Ticket> getInvoiceById(@PathVariable long id) {
//		try {
//			Ticket invoice = invoiceService.findById(id);
//			return ResponseEntity.ok(invoice);
//		} catch (IllegalArgumentException e) {
//			return ResponseEntity.notFound().build();
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//		}
//	}
//
//	@PostMapping
//	public ResponseEntity<Ticket> createInvoice(@RequestBody TicketDTO invoice) {
//		try {
//			Ticket createdInvoice = invoiceService.createInvoice(invoice);
//			return ResponseEntity.ok(createdInvoice);
//		} catch (IllegalArgumentException e) {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//		}
//	}
//}

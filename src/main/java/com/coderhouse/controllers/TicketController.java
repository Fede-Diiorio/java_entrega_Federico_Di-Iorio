package com.coderhouse.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderhouse.models.Ticket;
import com.coderhouse.services.TicketService;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

	@Autowired
	private TicketService ticketService;

	@GetMapping
	public ResponseEntity<List<Ticket>> getAllTickets() {
		try {
			List<Ticket> invoices = ticketService.getAllTickets();
			return ResponseEntity.ok(invoices);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/client/{id}")
	public ResponseEntity<List<Ticket>> getAllTicketsByClient(@PathVariable Long id) {
		try {
			List<Ticket> invoices = ticketService.getAllTicketsByClient(id);
			return ResponseEntity.ok(invoices);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	
}

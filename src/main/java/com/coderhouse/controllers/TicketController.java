package com.coderhouse.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderhouse.dtos.TicketDTO;
import com.coderhouse.models.Ticket;
import com.coderhouse.services.TicketService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/tickets")
@Tag(name = "Tickets", description = "Operaciones relacionadas con tickets")
public class TicketController {

	@Autowired
	private TicketService ticketService;
	
	@Autowired

	@Operation(summary = "Mostrar todos los tickets")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Tickets cargados con éxito", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Ticket.class)) }),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content) })
	@GetMapping
	public ResponseEntity<List<TicketDTO>> getAllTickets() {
		try {
			List<TicketDTO> invoices = ticketService.getAllTickets();
			return ResponseEntity.ok(invoices);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@Operation(summary = "Mostrar tickets por ID de cliente")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Tickets cargados con éxito", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Ticket.class)) }),
			@ApiResponse(responseCode = "404", description = "Tiecket no encontrado según el ID del cliente", content = @Content),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content) })
	@GetMapping("/client/{id}")
	public ResponseEntity<List<TicketDTO>> getAllTicketsByClient(@PathVariable Long id) {
		try {
			List<TicketDTO> invoices = ticketService.getAllTicketsByClient(id);
			return ResponseEntity.ok(invoices);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@Operation(summary = "Mostrar los productos del tickets por ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Detalles del ticket cargados con éxito", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Ticket.class)) }),
			@ApiResponse(responseCode = "404", description = "\"Detalles del ticket no encontrado según el ID del cliente", content = @Content),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content) })
	@GetMapping("/{id}")
	public ResponseEntity<TicketDTO> getTicketById(@PathVariable Long id) {
		try {
			TicketDTO invoice = ticketService.getTicketById(id);
			return ResponseEntity.ok(invoice);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
}

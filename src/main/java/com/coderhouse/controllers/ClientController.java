package com.coderhouse.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderhouse.dtos.ClientDTO;
import com.coderhouse.models.Client;
import com.coderhouse.services.ClientService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/clients")
@Tag(name = "Clients", description = "Operaciones relacionadas con clientes")
public class ClientController {

	@Autowired
	private ClientService clientService;

	@Operation(summary = "Mostrar todos los clientes")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Clientes encontrados con éxito", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ClientDTO.class)) }),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content) })
	@GetMapping
	public ResponseEntity<List<ClientDTO>> getAllClients() {
		try {
			List<ClientDTO> clients = clientService.getAll();
			return ResponseEntity.ok(clients);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@Operation(summary = "Mostrar cliente por ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Cliente encontrado con éxito", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ClientDTO.class)) }),
			@ApiResponse(responseCode = "404", description = "Cliente no encontrado según su ID", content = @Content),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content) })
	@GetMapping("/{id}")
	public ResponseEntity<ClientDTO> getClientById(@PathVariable long id) {
		try {
			ClientDTO client = clientService.getById(id);
			return ResponseEntity.ok(client);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@Operation(summary = "Crear un nuevo cliente")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Cliente creado con éxito", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ClientDTO.class)) }),
			@ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content) })
	@PostMapping
	public ResponseEntity<ClientDTO> saveClient(@RequestBody Client client) {
		try {
			ClientDTO createdClient = clientService.save(client);
			return ResponseEntity.ok(createdClient);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@Operation(summary = "Actualizar cliente por ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Cliente actualizado con éxito", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ClientDTO.class)) }),
			@ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content),
			@ApiResponse(responseCode = "404", description = "Cliente no encontrado según su ID", content = @Content),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content) })
	@PutMapping("/{id}")
	public ResponseEntity<ClientDTO> updateClient(@PathVariable Long id, @RequestBody Client client) {
		try {
			ClientDTO updatedClient = clientService.update(id, client);
			return ResponseEntity.ok(updatedClient);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@Operation(summary = "Elimina cliente por ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Cliente eliminado con éxito", content = @Content),
			@ApiResponse(responseCode = "404", description = "Cliente no encontrado según su ID", content = @Content),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content) })
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
		try {
			clientService.delete(id);
			return ResponseEntity.noContent().build();
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}

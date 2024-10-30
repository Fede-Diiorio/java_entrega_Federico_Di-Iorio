package com.coderhouse.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderhouse.models.Client;
import com.coderhouse.repositories.ClientRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

	@Autowired
	private ClientRepository clientRepository;

	@GetMapping
	public List<Client> getAllClients() {
		return clientRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Client> getClientById(@PathVariable Long id) {
		if (clientRepository.existsById(id)) {
			Client client = clientRepository.findById(id).get();
			return ResponseEntity.ok(client);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public Client createAlumno(@RequestBody Client client) {
		return clientRepository.save(client);
	}

}

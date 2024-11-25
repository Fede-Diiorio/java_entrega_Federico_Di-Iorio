package com.coderhouse.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.dtos.ClientDTO;
import com.coderhouse.interfaces.DAOInterface;
import com.coderhouse.models.Cart;
import com.coderhouse.models.Client;
import com.coderhouse.repositories.CartRepository;
import com.coderhouse.repositories.ClientRepository;

import jakarta.transaction.Transactional;

@Service
public class ClientService implements DAOInterface<Client, ClientDTO> {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private CartRepository cartRepository;

	public Client getClientById(Long id) {
		return clientRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("El cliente no existe"));
	}

	@Override
	public List<ClientDTO> getAll() {
		return clientRepository.findAll().stream().map(this::convertToDTO).toList();
	}

	@Override
	public ClientDTO getById(Long id) {
		Client client = getClientById(id);
		return convertToDTO(client);
	}

	@Override
	@Transactional
	public ClientDTO save(Client object) {
		Cart cart = new Cart();
		Cart savedCart = cartRepository.save(cart);
		object.setCart(savedCart);

		Client savedClient = clientRepository.save(object);
		return convertToDTO(savedClient);
	}

	@Override
	@Transactional
	public ClientDTO update(Long id, Client object) throws Exception {
		Client client = getClientById(id);

		client.setName(object.getName());
		client.setLastname(object.getLastname());

		if (object.getDocnumber() != null && !object.getDocnumber().isEmpty()) {
			client.setDocnumber(object.getDocnumber());
		}

		Client updatedClient = clientRepository.save(client);
		return convertToDTO(updatedClient);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		if (!clientRepository.existsById(id)) {
			throw new IllegalArgumentException("Cliente no encontrado");
		}
		clientRepository.deleteById(id);

	}

	private ClientDTO convertToDTO(Client client) {
		return new ClientDTO(client.getId(), client.getName(), client.getLastname(), client.getDocnumber(),
				client.getCart().getId());
	}

}

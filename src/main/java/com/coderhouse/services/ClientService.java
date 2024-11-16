package com.coderhouse.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.interfaces.DAOInterface;
import com.coderhouse.models.Cart;
import com.coderhouse.models.Client;
import com.coderhouse.repositories.CartRepository;
import com.coderhouse.repositories.ClientRepository;

import jakarta.transaction.Transactional;

@Service
public class ClientService implements DAOInterface<Client>{
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private CartRepository cartRepository;
	
	@Override
	public List<Client> getAll() {
		return clientRepository.findAll();
	}

	@Override
	public Client getById(Long id) {
		return clientRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("El cliente no existe"));
	}

	@Override
	@Transactional
	public Client save(Client object) {
		Cart cart = new Cart();
		Cart savedCart = cartRepository.save(cart);
		object.setCart(savedCart);
		
		return clientRepository.save(object);
	}

	@Override
	@Transactional
	public Client update(Long id, Client object) throws Exception {
		Client client = getById(id);
		
		client.setName(object.getName());
		client.setLastname(object.getLastname());
		
		if(object.getDocnumber() != null && !object.getDocnumber().isEmpty()) {
			client.setDocnumber(object.getDocnumber());
		}
		
		return clientRepository.save(client);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		if(!clientRepository.existsById(id)) {
			throw new IllegalArgumentException("Cliente no encontrado");
		}
		clientRepository.deleteById(id);
		
	}

}

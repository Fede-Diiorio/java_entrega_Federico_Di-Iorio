package com.coderhouse.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.dtos.ClientReqDTO;
import com.coderhouse.dtos.ClientResDTO;
import com.coderhouse.interfaces.DAOInterface;
import com.coderhouse.mapper.ClientMapper;
import com.coderhouse.models.Cart;
import com.coderhouse.models.Client;
import com.coderhouse.repositories.CartRepository;
import com.coderhouse.repositories.ClientRepository;

import jakarta.transaction.Transactional;

@Service
public class ClientService implements DAOInterface<ClientReqDTO, ClientResDTO> {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private ClientMapper clientMapper;

	public Client getClientById(Long id) {
		return clientRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("El cliente no existe"));
	}

	@Override
	public List<ClientResDTO> getAll() {
		return clientRepository.findAll().stream().map(clientMapper::toDTO).toList();
	}

	@Override
	public ClientResDTO getById(Long id) {
		Client client = getClientById(id);
		return clientMapper.toDTO(client);
	}

	@Override
	@Transactional
	public ClientResDTO save(ClientReqDTO object) {
		Cart cart = new Cart();

		Cart savedCart = cartRepository.save(cart);

		Client client = clientMapper.toEntity(object, null);

		client.setCart(savedCart);

		clientRepository.save(client);

		return clientMapper.toDTO(client);
	}

	@Override
	@Transactional
	public ClientResDTO update(Long id, ClientReqDTO object) throws Exception {
		Client client = getClientById(id);

		clientRepository.save(clientMapper.toEntity(object, client));

		return clientMapper.toDTO(client);

	}

	@Override
	@Transactional
	public void delete(Long id) {
		if (!clientRepository.existsById(id)) {
			throw new IllegalArgumentException("Cliente no encontrado");
		}
		clientRepository.deleteById(id);

	}

}

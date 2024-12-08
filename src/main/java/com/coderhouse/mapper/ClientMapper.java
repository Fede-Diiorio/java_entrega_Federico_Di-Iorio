package com.coderhouse.mapper;

import com.coderhouse.dtos.ClientResDTO;

import org.springframework.stereotype.Component;

import com.coderhouse.dtos.ClientReqDTO;
import com.coderhouse.models.Client;

@Component
public class ClientMapper {

	public ClientResDTO toDTO(Client client) {
		return new ClientResDTO(client.getId(), client.getName(), client.getLastname(), client.getDocnumber(), client.getCart().getId());
	}
	
	public Client toEntity(ClientReqDTO clientDTO, Client existingClient) {
		Client client = existingClient != null ? existingClient : new Client();
		
		if(clientDTO.getClientDocnumber() != null && !clientDTO.getClientDocnumber().isEmpty()) {
			client.setDocnumber(clientDTO.getClientDocnumber());
		}
		
		if(clientDTO.getClientLastname() != null && !clientDTO.getClientLastname().isEmpty()) {
			client.setLastname(clientDTO.getClientLastname());
		}
		
		if(clientDTO.getClientName() != null && !clientDTO.getClientName().isEmpty()) {
			client.setName(clientDTO.getClientName());
		}
		
		return client;
	}
}

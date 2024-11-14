package com.coderhouse.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.models.TicketProduct;
import com.coderhouse.repositories.TicketProductRepository;

import jakarta.transaction.Transactional;

@Service
public class TicketProductService {

	@Autowired
	private TicketProductRepository ticketProductRepository;

	public List<TicketProduct> getAllItems() {
		return ticketProductRepository.findAll();
	}

	public List<TicketProduct> getAllItemsByTicketId(Long ticketId) {
		return ticketProductRepository.findByTicketId(ticketId);
	}

	@Transactional
	public TicketProduct saveTicketDetails(TicketProduct ticketProduct) {
		return ticketProductRepository.save(ticketProduct);
	}
}

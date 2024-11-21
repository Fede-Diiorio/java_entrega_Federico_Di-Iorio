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

	public List<TicketProduct> getAllByTicketId(Long ticketId) {
		return ticketProductRepository.findProductsByTicketId(ticketId);
	}

	@Transactional
	public TicketProduct save(TicketProduct ticketProduct) {
		return ticketProductRepository.save(ticketProduct);
	}
}

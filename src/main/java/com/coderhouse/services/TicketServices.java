package com.coderhouse.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.dto.TicketDTO;
import com.coderhouse.models.Client;
import com.coderhouse.models.Ticket;
import com.coderhouse.repositories.ClientRepository;
import com.coderhouse.repositories.TicketRepository;

import jakarta.transaction.Transactional;

@Service
public class TicketServices {

	@Autowired
	private TicketRepository invoiceRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	public List<Ticket> getAllInvoices() {
		return invoiceRepository.findAll();
	}
	
	public Ticket findById(Long id) {
		return invoiceRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Factura no encontrada"));
	}
	
	@Transactional
	public Ticket createInvoice(TicketDTO invoiceDetails) {
		
		Client client = clientRepository.findById(invoiceDetails.getClient_id())
				.orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));
		
		Ticket invoice = new Ticket();
		invoice.setClient(client);
		invoice.setTotal(invoiceDetails.getTotal());
		
		return invoiceRepository.save(invoice);
	}
 }

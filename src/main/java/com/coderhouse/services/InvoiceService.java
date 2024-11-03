package com.coderhouse.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.dto.InvoiceDTO;
import com.coderhouse.models.Client;
import com.coderhouse.models.Invoice;
import com.coderhouse.repositories.ClientRepository;
import com.coderhouse.repositories.InvoiceRepository;

import jakarta.transaction.Transactional;

@Service
public class InvoiceService {

	@Autowired
	private InvoiceRepository invoiceRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	public List<Invoice> getAllInvoices() {
		return invoiceRepository.findAll();
	}
	
	public Invoice findById(Long id) {
		return invoiceRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Factura no encontrada"));
	}
	
	@Transactional
	public Invoice createInvoice(InvoiceDTO invoiceDetails) {
		
		Client client = clientRepository.findById(invoiceDetails.getClient_id())
				.orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));
		
		Invoice invoice = new Invoice();
		invoice.setClient(client);
		invoice.setCreated_at(invoiceDetails.getCreated_ad());
		invoice.setTotal(invoiceDetails.getTotal());
		
		return invoiceRepository.save(invoice);
	}
 }

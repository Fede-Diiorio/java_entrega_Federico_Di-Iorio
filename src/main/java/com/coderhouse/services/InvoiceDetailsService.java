package com.coderhouse.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.dto.InvoiceDTO;
import com.coderhouse.dto.InvoiceDetailDTO;
import com.coderhouse.models.Invoice;
import com.coderhouse.models.InvoiceDetail;
import com.coderhouse.models.Product;
import com.coderhouse.repositories.InvoiceDetailsRepository;
import com.coderhouse.repositories.InvoiceRepository;
import com.coderhouse.repositories.ProductRepository;

@Service
public class InvoiceDetailsService {

	@Autowired
	private InvoiceDetailsRepository invoiceDetailsRepository;
	
	@Autowired
	private InvoiceRepository invoiceRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	public List<InvoiceDetail> getAllInvoicesDetails() {
		return invoiceDetailsRepository.findAll();
	}
	
	public InvoiceDetail findById(Long id) {
		return invoiceDetailsRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("El detalle no existe"));
	}
	
	public InvoiceDetail saveInvoicesDetails(InvoiceDetailDTO invoiceDetailDTO) {
		Invoice invoice = invoiceRepository.findById(invoiceDetailDTO.getInvoice_id())
				.orElseThrow(() -> new IllegalArgumentException("Factura no encontrada"));
		
		Product product = productRepository.findById(invoiceDetailDTO.getProduct_id())
				.orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
		
		InvoiceDetail invoiceDetail = new InvoiceDetail();
		
		invoiceDetail.setAmoun(invoiceDetailDTO.getAmoun());
		invoiceDetail.setPrice(product.getPrice());
	}
	
}

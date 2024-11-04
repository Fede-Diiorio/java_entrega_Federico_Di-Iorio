package com.coderhouse.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.dto.InvoiceDetailDTO;
import com.coderhouse.models.Invoice;
import com.coderhouse.models.InvoiceDetail;
import com.coderhouse.models.Product;
import com.coderhouse.repositories.InvoiceDetailsRepository;
import com.coderhouse.repositories.InvoiceRepository;
import com.coderhouse.repositories.ProductRepository;

import jakarta.transaction.Transactional;

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

	@Transactional
	public InvoiceDetail saveInvoicesDetails(InvoiceDetailDTO invoiceDetailDTO) {
		Invoice invoice = invoiceRepository.findById(invoiceDetailDTO.getInvoice_id())
				.orElseThrow(() -> new IllegalArgumentException("Factura no encontrada"));

		Product product = productRepository.findById(invoiceDetailDTO.getProduct_id())
				.orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));

		InvoiceDetail invoiceDetail = new InvoiceDetail();

		invoiceDetail.setQuantity(invoiceDetailDTO.getQuantity());
		invoiceDetail.setPrice(product.getPrice());
		invoiceDetail.setProduct(product);
		invoiceDetail.setInvoice(invoice);

		return invoiceDetailsRepository.save(invoiceDetail);
	}

	@Transactional
	public InvoiceDetail updateInvoicesDetails(Long id, InvoiceDetail details) {
		InvoiceDetail invoiceDetail = invoiceDetailsRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Detalle de factura no encontrado"));
		
		invoiceDetail.setQuantity(details.getQuantity());
		
		return invoiceDetailsRepository.save(invoiceDetail);
	}

}

package com.coderhouse.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.dto.ProductCartDTO;
import com.coderhouse.models.Ticket;
import com.coderhouse.models.ProductCart;
import com.coderhouse.models.Product;
import com.coderhouse.repositories.ProductCartRepository;
import com.coderhouse.repositories.TicketRepository;
import com.coderhouse.repositories.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductCartService {

	@Autowired
	private ProductCartRepository invoiceDetailsRepository;

	@Autowired
	private TicketRepository invoiceRepository;

	@Autowired
	private ProductRepository productRepository;

	public List<ProductCart> getAllInvoicesDetails() {
		return invoiceDetailsRepository.findAll();
	}

	public ProductCart findById(Long id) {
		return invoiceDetailsRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("El detalle no existe"));
	}

	@Transactional
	public ProductCart saveInvoicesDetails(ProductCartDTO invoiceDetailDTO) {
		Ticket invoice = invoiceRepository.findById(invoiceDetailDTO.getInvoice_id())
				.orElseThrow(() -> new IllegalArgumentException("Factura no encontrada"));

		Product product = productRepository.findById(invoiceDetailDTO.getProduct_id())
				.orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));

		ProductCart invoiceDetail = new ProductCart();

		invoiceDetail.setQuantity(invoiceDetailDTO.getQuantity());
		invoiceDetail.setPrice(product.getPrice());
		invoiceDetail.setProduct(product);
		invoiceDetail.setTicket(invoice);

		return invoiceDetailsRepository.save(invoiceDetail);
	}

	@Transactional
	public ProductCart updateInvoicesDetails(Long id, ProductCart details) {
		ProductCart invoiceDetail = invoiceDetailsRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Detalle de factura no encontrado"));
		
		invoiceDetail.setQuantity(details.getQuantity());
		
		return invoiceDetailsRepository.save(invoiceDetail);
	}

}

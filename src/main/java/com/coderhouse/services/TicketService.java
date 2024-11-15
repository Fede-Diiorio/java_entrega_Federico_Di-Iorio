package com.coderhouse.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.models.Client;
import com.coderhouse.models.Product;
import com.coderhouse.models.ProductCart;
import com.coderhouse.models.Ticket;
import com.coderhouse.models.TicketProduct;
import com.coderhouse.repositories.ClientRepository;
import com.coderhouse.repositories.TicketRepository;

import jakarta.transaction.Transactional;

@Service
public class TicketService {

	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private ProductCartService productCartService;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private TicketProductService ticketProductServise;
	
	@Autowired
	private DateService dateService;
	
	public List<Ticket> getAllTickets() {
		return ticketRepository.findAll();
	}
	
	public List<Ticket> getAllTicketsByClient(Long id) {
		return ticketRepository.findByClientId(id);
	}
	
	@Transactional
	public Ticket saveTicket(Long cartId) {
	    List<ProductCart> products = productCartService.getAllProductsFromCart(cartId);
	    
	    Client client = clientRepository.findByCartId(cartId);
	    
	    if (client == null || client.getId() == 0) {
	        throw new IllegalArgumentException("Invalid client: " + cartId);
	    }
	    
	    double adder = 0;

	    Ticket ticket = new Ticket();
	    ticket.setClient(client);
	    
	    LocalDateTime currentDateTime = dateService.getCurrentDateTime();
        if (currentDateTime == null) {
            throw new IllegalStateException("No se pudo obtener la fecha actual desde el servicio externo.");
        }

        ticket.setCreatedAt(currentDateTime);
	    
	    for(ProductCart product : products) {
	        product.getProduct().setStock(product.getProduct().getStock() - product.getQuantity());
	        adder += product.getPrice();
	    }
	    
	    if(adder == 0) {
	        throw new IllegalArgumentException("Su carrito se encuentra vacío. No hay nada para facturar");
	    }

	    ticket.setTotal(adder);
	    
	    ticket = ticketRepository.save(ticket);

	    for(ProductCart product : products) {
	        Product findedProduct = productService.findById(product.getProduct().getId());
	        
	        TicketProduct ticketProduct = new TicketProduct();
	        ticketProduct.setTicket(ticket); 
	        ticketProduct.setProduct(findedProduct);
	        ticketProduct.setProductName(findedProduct.getName());
	        ticketProduct.setQuantity(product.getQuantity());
	        ticketProduct.setSubtotal(product.getPrice());
	        ticketProduct.setUnitPrice(findedProduct.getPrice());

	        ticketProductServise.saveTicketDetails(ticketProduct);
	    }
	    
	    productCartService.deleteProductCartByCartId(cartId);
	    
	    return ticket;
	}

	
}

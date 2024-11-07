package com.coderhouse.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.models.Client;
import com.coderhouse.models.ProductCart;
import com.coderhouse.models.Ticket;
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
	    
	    for(ProductCart product : products) {
	    	product.getProduct().setStock(product.getProduct().getStock() - product.getQuantity());
	        adder += product.getPrice();
	    }
	    
	    if(adder == 0) {
	    	throw new IllegalArgumentException("Su carrito se encuentra vacío. No hay nada para facturar");
	    }
	    
	    Ticket ticket = new Ticket();
	    ticket.setTotal(adder);
	    ticket.setClient(client);
	    
	    productCartService.deleteProductCartByCartId(cartId);
	    
	    return ticketRepository.save(ticket);
	}
	
}

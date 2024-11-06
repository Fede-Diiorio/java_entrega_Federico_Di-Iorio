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
	
	@Transactional
	public Ticket saveTicket(Long cid) {
	    List<ProductCart> products = productCartService.getAllProductsFromCart(cid);
	    
	    Client client = clientRepository.findByCartId(cid);
	    
	    if (client == null || client.getId() == 0) {
	        throw new IllegalArgumentException("Invalid client: " + cid);
	    }
	    
	    double adder = 0;
	    
	    for(ProductCart product : products) {
	        adder += product.getPrice();
	    }
	    
	    Ticket ticket = new Ticket();
	    ticket.setTotal(adder);
	    ticket.setClient(client);
	    
	    productCartService.deleteProductCartByCartId(cid);
	    
	    return ticketRepository.save(ticket);
	}
	
}

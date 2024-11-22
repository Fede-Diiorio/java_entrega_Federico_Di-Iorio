package com.coderhouse.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.dtos.TicketDTO;
import com.coderhouse.dtos.TicketProductDTO;
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
	public TicketDTO saveTicket(Long cartId) {
	    List<ProductCart> products = productCartService.getAllProductsFromCart(cartId);
	    
	    TicketDTO ticketDTO = new TicketDTO();
	    
	    Client client = clientRepository.findByCartId(cartId);
	    
	    if (client == null || client.getId() == 0) {
	        throw new IllegalArgumentException("Invalid client: " + cartId);
	    }
	    
	    double adder = 0;

	    Ticket ticket = new Ticket();
	    
	    ticket.setClient(client);
	    ticketDTO.setClientId(client.getId());
	    
	    LocalDateTime currentDateTime = dateService.getCurrentDateTime();
        if (currentDateTime == null) {
            throw new IllegalStateException("No se pudo obtener la fecha actual desde el servicio externo.");
        }

        ticket.setCreatedAt(currentDateTime);
        ticketDTO.setCreatedAt(currentDateTime);
        
        List<TicketProductDTO> productsList = new ArrayList<TicketProductDTO>();
	    
	    for(ProductCart product : products) {
	        product.getProduct().setStock(product.getProduct().getStock() - product.getQuantity());
	        adder += product.getPrice();
	        
	        TicketProductDTO ticketProductDTO = new TicketProductDTO();
	        ticketProductDTO.setProductName(product.getProduct().getName());
	        ticketProductDTO.setProductCode(product.getProduct().getCode());
	        ticketProductDTO.setProductId(product.getProduct().getId());
	        ticketProductDTO.setProductPrice(product.getPrice());
	        ticketProductDTO.setQuantity(product.getQuantity());
	        ticketProductDTO.setSubtotal((product.getPrice() * product.getQuantity()));
	        
	        productsList.add(ticketProductDTO);
	    }
	    
	    ticketDTO.setProducts(productsList);
	    
	    if(adder == 0) {
	        throw new IllegalArgumentException("Su carrito se encuentra vacío. No hay nada para facturar");
	    }

	    ticket.setTotal(adder);
	    ticketDTO.setTotal(adder);
	    
	    ticket = ticketRepository.save(ticket);
	    
	    ticketDTO.setCode(ticket.getCode());

	    for(ProductCart product : products) {
	        Product findedProduct = productService.getById(product.getProduct().getId());
	        
	        TicketProduct ticketProduct = new TicketProduct();
	        ticketProduct.setTicket(ticket); 
	        ticketProduct.setProduct(findedProduct);
	        ticketProduct.setProductName(findedProduct.getName());
	        ticketProduct.setQuantity(product.getQuantity());
	        ticketProduct.setSubtotal(product.getPrice());
	        ticketProduct.setUnitPrice(findedProduct.getPrice());

	        ticketProductServise.save(ticketProduct);
	    }
	    
	    productCartService.deleteProductCartByCartId(cartId);
	    
	    return ticketDTO;
	}
}

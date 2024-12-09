package com.coderhouse.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.dtos.ProductReqDTO;
import com.coderhouse.dtos.ProductOnCartDTO;
import com.coderhouse.dtos.ProductResDTO;
import com.coderhouse.dtos.TicketDTO;
import com.coderhouse.mapper.ProductMapper;
import com.coderhouse.mapper.TicketMapper;
import com.coderhouse.models.Client;
import com.coderhouse.models.Product;
import com.coderhouse.models.Ticket;
import com.coderhouse.models.TicketProduct;
import com.coderhouse.repositories.TicketRepository;

import jakarta.transaction.Transactional;

@Service
public class TicketService {

	@Autowired
	private ClientService clientService;

	@Autowired
	private DateService dateService;

	@Autowired
	private ProductCartService productCartService;

	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductMapper productMapper;

	@Autowired
	private TicketProductService ticketProductService;
	
	@Autowired
	private TicketMapper ticketMapper;

	@Autowired
	private TicketRepository ticketRepository;
	
	public List<TicketDTO> getAllTickets() {
	    List<Ticket> tickets = ticketRepository.findAll();

	    return tickets.stream()
	        .map(ticket -> {
	            List<TicketProduct> ticketProducts = ticketProductService.getAllByTicketId(ticket.getId());
	            
	            return ticketMapper.toDTO(ticket, ticketProducts);
	        })
	        .toList();
	}


	public List<TicketDTO> getAllTicketsByClient(Long clientId) {
	    List<Ticket> tickets = ticketRepository.findByClientId(clientId);

	    return tickets.stream()
		        .map(ticket -> {
		            List<TicketProduct> ticketProducts = ticketProductService.getAllByTicketId(ticket.getId());
		            
		            return ticketMapper.toDTO(ticket, ticketProducts);
		        })
		        .toList();
	}

	public TicketDTO getTicketById(Long ticketId) {
	    Ticket ticket = ticketRepository.findById(ticketId)
	            .orElseThrow(() -> new IllegalArgumentException("Ticket con ID " + ticketId + " no encontrado."));

	    List<TicketProduct> ticketProducts = ticketProductService.getAllByTicketId(ticketId);

	    return ticketMapper.toDTO(ticket, ticketProducts);
	}

	
	@Transactional
	public TicketDTO saveTicket(Long cartId) {
	    Client client = clientService.getClientByCartId(cartId);

	    List<ProductOnCartDTO> products = productCartService.getAllProductsFromCart(cartId);
	    if (products.isEmpty()) {
	        throw new IllegalArgumentException("Su carrito está vacío. No hay nada para facturar.");
	    }

	    Ticket ticket = createTicket(client, products);

	    updateProductStock(products);

	    saveTicketProducts(ticket, products);

	    productCartService.deleteProductCartByCartId(cartId);

	    return ticketMapper.ToTicketDTO(ticket, products);
	}

	private Ticket createTicket(Client client, List<ProductOnCartDTO> products) {
	    Ticket ticket = new Ticket();
	    ticket.setClient(client);
	    ticket.setCreatedAt(dateService.getCurrentDateTime());

	    double total = products.stream()
	            .mapToDouble(product -> product.getUnitPrice() * product.getQuantity())
	            .sum();

	    ticket.setTotal(total);
	    return ticketRepository.save(ticket);
	}
	
	private void updateProductStock(List<ProductOnCartDTO> products) {
	    for (ProductOnCartDTO productCart : products) {
	    	
	        ProductResDTO product = productService.getById(productCart.getProductId());
	        
	        ProductReqDTO productDTO = productMapper.toReqDTO(product);
	        
	        productDTO.setProductStock(product.getProductStock() - productCart.getQuantity());

	        productService.update(productDTO.getProductId(), productDTO);
	    }
	}

	private void saveTicketProducts(Ticket ticket, List<ProductOnCartDTO> products) {
	    for (ProductOnCartDTO product : products) {
	        ProductResDTO dbProduct = productService.getById(product.getProductId());
	        Product newProduct = productService.getProductById(product.getProductId());

	        TicketProduct ticketProduct = new TicketProduct();
	        ticketProduct.setTicket(ticket);
	        ticketProduct.setProduct(newProduct);
	        ticketProduct.setProductName(dbProduct.getProductName());
	        ticketProduct.setQuantity(product.getQuantity());
	        ticketProduct.setSubtotal(product.getUnitPrice() * product.getQuantity());
	        ticketProduct.setUnitPrice(product.getUnitPrice());

	        ticketProductService.save(ticketProduct);
	    }
	}

}

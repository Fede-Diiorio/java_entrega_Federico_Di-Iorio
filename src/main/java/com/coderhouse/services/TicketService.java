package com.coderhouse.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.dtos.ProductReqDTO;
import com.coderhouse.dtos.ProductOnCartDTO;
import com.coderhouse.dtos.ProductResDTO;
import com.coderhouse.dtos.TicketDTO;
import com.coderhouse.dtos.TicketProductDTO;
import com.coderhouse.mapper.TicketMapper;
import com.coderhouse.models.Category;
import com.coderhouse.models.Client;
import com.coderhouse.models.Product;
import com.coderhouse.models.Ticket;
import com.coderhouse.models.TicketProduct;
import com.coderhouse.repositories.TicketRepository;

import jakarta.transaction.Transactional;

@Service
public class TicketService {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ClientService clientService;

	@Autowired
	private DateService dateService;

	@Autowired
	private ProductCartService productCartService;

	@Autowired
	private ProductService productService;

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

	    return convertToTicketDTO(ticket, products);
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
	        ProductResDTO product = productService.getById(productCart.getId());
	        
	        Category category = categoryService.getByName(product.getCategoryName());
	        
	        ProductReqDTO productDTO = new ProductReqDTO();
	        productDTO.setProductId(product.getProductId());
	        productDTO.setProductName(product.getProductName());
	        productDTO.setProductImage(product.getProductImage());
	        productDTO.setProductDescription(product.getProductDescription());
	        productDTO.setProductStock(product.getProductStock() - productCart.getQuantity());
	        productDTO.setProductPrice(product.getProductPrice());
	        if (product.getCategoryName() != null) {
	            productDTO.setCategoryId(category.getId());
	        }

	        productService.update(productDTO.getProductId(), productDTO);
	    }
	}

	private void saveTicketProducts(Ticket ticket, List<ProductOnCartDTO> products) {
	    for (ProductOnCartDTO product : products) {
	        ProductResDTO dbProduct = productService.getById(product.getId());
	        Product newProduct = productService.getProductById(product.getId());

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

	private TicketDTO convertToTicketDTO(Ticket ticket, List<ProductOnCartDTO> products) {
	    TicketDTO ticketDTO = new TicketDTO();
	    ticketDTO.setId(ticket.getId());
	    ticketDTO.setClientId(ticket.getClient().getId());
	    ticketDTO.setCreatedAt(ticket.getCreatedAt());
	    ticketDTO.setCode(ticket.getCode());
	    ticketDTO.setTotal(ticket.getTotal());

	    List<TicketProductDTO> productDTOs = products.stream().map(product -> {
	        TicketProductDTO dto = new TicketProductDTO();
	        dto.setProductId(product.getId());
	        dto.setProductName(product.getName());
	        dto.setQuantity(product.getQuantity());
	        dto.setProductPrice(product.getUnitPrice());
	        dto.setSubtotal(product.getUnitPrice() * product.getQuantity());
	        return dto;
	    }).collect(Collectors.toList());

	    ticketDTO.setProducts(productDTOs);
	    return ticketDTO;
	}

}

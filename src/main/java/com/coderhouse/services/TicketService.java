package com.coderhouse.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.dtos.ProductDTO;
import com.coderhouse.dtos.ProductOnCartDTO;
import com.coderhouse.dtos.ProductResponseDTO;
import com.coderhouse.dtos.TicketDTO;
import com.coderhouse.dtos.TicketProductDTO;
import com.coderhouse.models.Category;
import com.coderhouse.models.Client;
import com.coderhouse.models.Product;
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
	private TicketProductService ticketProductService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private DateService dateService;
	
	public List<TicketDTO> getAllTickets() {
	    List<Ticket> tickets = ticketRepository.findAll();

	    List<TicketDTO> ticketsDTO = tickets.stream().map(ticket -> {
	        List<TicketProduct> ticketProducts = ticketProductService.getAllByTicketId(ticket.getId());

	        TicketDTO dto = new TicketDTO();
	        dto.setClientId(ticket.getClient().getId());
	        dto.setCode(ticket.getCode());
	        dto.setCreatedAt(ticket.getCreatedAt());
	        dto.setTotal(ticket.getTotal());

	        List<TicketProductDTO> productDTOs = ticketProducts.stream()
	            .map(product -> {
	                TicketProductDTO productDTO = new TicketProductDTO();
	                productDTO.setProductName(product.getProductName());
	                productDTO.setProductId(product.getProduct().getId());
	                productDTO.setProductPrice(product.getUnitPrice());
	                productDTO.setQuantity(product.getQuantity());
	                productDTO.setSubtotal(product.getSubtotal());
	                return productDTO;
	            })
	            .toList();

	        dto.setProducts(productDTOs);

	        return dto;
	    }).toList();

	    return ticketsDTO;
	}

	public List<TicketDTO> getAllTicketsByClient(Long clientId) {
	    List<Ticket> tickets = ticketRepository.findByClientId(clientId);

	    List<TicketDTO> ticketsDTO = tickets.stream().map(ticket -> {
	        List<TicketProduct> ticketProducts = ticketProductService.getAllByTicketId(ticket.getId());

	        TicketDTO dto = new TicketDTO();
	        dto.setClientId(ticket.getClient().getId());
	        dto.setCode(ticket.getCode());
	        dto.setCreatedAt(ticket.getCreatedAt());
	        dto.setTotal(ticket.getTotal());
	        
	        List<TicketProductDTO> productDTOs = ticketProducts.stream()
	            .map(product -> {
	                TicketProductDTO productDTO = new TicketProductDTO();
	                productDTO.setProductName(product.getProductName());
	                productDTO.setProductId(product.getProduct().getId());
	                productDTO.setProductPrice(product.getUnitPrice());
	                productDTO.setQuantity(product.getQuantity());
	                productDTO.setSubtotal(product.getSubtotal());
	                return productDTO;
	            })
	            .toList();

	        dto.setProducts(productDTOs);

	        return dto;
	    }).toList();

	    return ticketsDTO;
	}

	public TicketDTO getTicketById(Long ticketId) {
	    Ticket ticket = ticketRepository.findById(ticketId)
	            .orElseThrow(() -> new IllegalArgumentException("Ticket con ID " + ticketId + " no encontrado."));

	    List<TicketProduct> ticketProducts = ticketProductService.getAllByTicketId(ticketId);

	    TicketDTO dto = new TicketDTO();
	    dto.setClientId(ticket.getClient().getId());
	    dto.setCode(ticket.getCode());
	    dto.setCreatedAt(ticket.getCreatedAt());
	    dto.setTotal(ticket.getTotal());

	    List<TicketProductDTO> productDTOs = ticketProducts.stream()
	        .map(product -> {
	            TicketProductDTO productDTO = new TicketProductDTO();
	            productDTO.setProductName(product.getProductName());
	            productDTO.setProductId(product.getProduct().getId());
	            productDTO.setProductPrice(product.getUnitPrice());
	            productDTO.setQuantity(product.getQuantity());
	            productDTO.setSubtotal(product.getSubtotal());
	            return productDTO;
	        })
	        .toList();

	    dto.setProducts(productDTOs);

	    return dto;
	}

	
	@Transactional
	public TicketDTO saveTicket(Long cartId) {
	    Client client = validateClient(cartId);

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

	
	private Client validateClient(Long cartId) {
	    Client client = clientRepository.findByCartId(cartId);
	    if (client == null || client.getId() == 0) {
	        throw new IllegalArgumentException("Cliente inválido para el carrito: " + cartId);
	    }
	    return client;
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
	        ProductResponseDTO product = productService.getById(productCart.getId());
	        
	        Category category = categoryService.getByName(product.getCategory());
	        
	        ProductDTO productDTO = new ProductDTO();
	        productDTO.setId(product.getId());
	        productDTO.setName(product.getName());
	        productDTO.setImage(product.getImage());
	        productDTO.setDescription(product.getDescription());
	        productDTO.setStock(product.getStock() - productCart.getQuantity());
	        productDTO.setPrice(product.getPrice());
	        if (product.getCategory() != null) {
	            productDTO.setCategory(category.getId());
	        }

	        productService.update(productDTO.getId(), productDTO);
	    }
	}

	private void saveTicketProducts(Ticket ticket, List<ProductOnCartDTO> products) {
	    for (ProductOnCartDTO product : products) {
	        ProductResponseDTO dbProduct = productService.getById(product.getId());
	        Product newProduct = productService.getProductById(product.getId());

	        TicketProduct ticketProduct = new TicketProduct();
	        ticketProduct.setTicket(ticket);
	        ticketProduct.setProduct(newProduct);
	        ticketProduct.setProductName(dbProduct.getName());
	        ticketProduct.setQuantity(product.getQuantity());
	        ticketProduct.setSubtotal(product.getUnitPrice() * product.getQuantity());
	        ticketProduct.setUnitPrice(product.getUnitPrice());

	        ticketProductService.save(ticketProduct);
	    }
	}

	private TicketDTO convertToTicketDTO(Ticket ticket, List<ProductOnCartDTO> products) {
	    TicketDTO ticketDTO = new TicketDTO();
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

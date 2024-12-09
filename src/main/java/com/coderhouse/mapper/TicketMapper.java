package com.coderhouse.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.coderhouse.dtos.TicketDTO;
import com.coderhouse.dtos.TicketProductDTO;
import com.coderhouse.models.Ticket;
import com.coderhouse.models.TicketProduct;

@Component
public class TicketMapper {

    public TicketDTO toDTO(Ticket ticket, List<TicketProduct> ticketProducts) {
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setId(ticket.getId());
        ticketDTO.setClientId(ticket.getClient().getId());
        ticketDTO.setCode(ticket.getCode());
        ticketDTO.setCreatedAt(ticket.getCreatedAt());
        ticketDTO.setTotal(ticket.getTotal());
        
        List<TicketProductDTO> productDTOs = ticketProducts.stream()
            .map(this::toDTO) 
            .collect(Collectors.toList());
        
        ticketDTO.setProducts(productDTOs);
        
        return ticketDTO;
    }

    public TicketProductDTO toDTO(TicketProduct ticketProduct) {
        return new TicketProductDTO(
            ticketProduct.getProductName(),
            ticketProduct.getProduct().getId(),
            ticketProduct.getUnitPrice(),
            ticketProduct.getQuantity(),
            ticketProduct.getSubtotal()
        );
    }
}

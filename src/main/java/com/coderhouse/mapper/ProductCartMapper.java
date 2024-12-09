package com.coderhouse.mapper;

import org.springframework.stereotype.Component;

import com.coderhouse.dtos.ProductOnCartDTO;
import com.coderhouse.models.ProductCart;

@Component
public class ProductCartMapper {

	public ProductOnCartDTO toDTO(ProductCart productCart) {
	    ProductOnCartDTO dto = new ProductOnCartDTO();
	    dto.setId(productCart.getProduct().getId());
	    dto.setName(productCart.getProduct().getName());
	    dto.setUnitPrice(productCart.getProduct().getPrice());
	    dto.setQuantity(productCart.getQuantity());
	    dto.setTotalPrice(productCart.getQuantity() * productCart.getProduct().getPrice());
	    return dto;
	}
}

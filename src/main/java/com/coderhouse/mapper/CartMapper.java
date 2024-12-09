package com.coderhouse.mapper;

import org.springframework.stereotype.Component;

import com.coderhouse.dtos.CartResDTO;
import com.coderhouse.dtos.ProductOnCartDTO;
import com.coderhouse.models.Cart;

@Component
public class CartMapper {

	public CartResDTO toDTO(Cart cart) {
		 CartResDTO cartDTO = new CartResDTO();
		    cartDTO.setId(cart.getId());
		    cartDTO.setProducts(
		        cart.getProducts().stream()
		            .map(productCart -> {
		                ProductOnCartDTO productDTO = new ProductOnCartDTO();
		                productDTO.setProductId(productCart.getProduct().getId());
		                productDTO.setProductName(productCart.getProduct().getName());
		                productDTO.setUnitPrice(productCart.getProduct().getPrice());
		                productDTO.setQuantity(productCart.getQuantity());
		                productDTO.setTotalPrice(productCart.getQuantity() * productCart.getProduct().getPrice());
		                return productDTO;
		            })
		            .toList()
		    );
		    return cartDTO;
	}
}

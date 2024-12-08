package com.coderhouse.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.models.ProductCart;
import com.coderhouse.dtos.CartResDTO;
import com.coderhouse.dtos.ProductOnCartDTO;
import com.coderhouse.models.Cart;
import com.coderhouse.models.Product;
import com.coderhouse.repositories.CartRepository;
import com.coderhouse.repositories.ProductCartRepository;
import com.coderhouse.repositories.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductCartService {

	@Autowired
	private ProductCartRepository productCartRepository;

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private ProductRepository productRepository;

	public List<ProductOnCartDTO> getAllProductsFromCart(Long cartId) {
	    List<ProductCart> productCarts = productCartRepository.findByCartId(cartId);
	    return productCarts.stream()
	            .map(this::mapToProductOnCartDTO)
	            .toList();
	}

	@Transactional
	public CartResDTO addProductToCart(Long cartId, Long productId, int quantity) {

	    if (quantity <= 0) {
	        throw new IllegalArgumentException("Tiene que ingresar una cantidad superior a cero.");
	    }

	    Cart cart = cartRepository.findById(cartId)
	            .orElseThrow(() -> new IllegalArgumentException("Carrito no encontrado"));

	    Product product = productRepository.findById(productId)
	            .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));

	    Optional<ProductCart> existingProductCart = productCartRepository.findByCartAndProduct(cart, product);

	    if (existingProductCart.isPresent()) {
	        ProductCart productCart = existingProductCart.get();
	        productCart.setQuantity(quantity);
	        productCartRepository.save(productCart);
	    } else {
	        ProductCart newProductCart = new ProductCart();
	        newProductCart.setQuantity(quantity);
	        newProductCart.setCart(cart);
	        newProductCart.setProduct(product);
	        productCartRepository.save(newProductCart);
	    }

	    List<ProductOnCartDTO> productDTOs = getAllProductsFromCart(cartId);

	    CartResDTO cartDTO = new CartResDTO();
	    cartDTO.setId(cartId);
	    cartDTO.setProducts(productDTOs);

	    return cartDTO;
	}


	public void deleteProductFromCart(Long cartId, Long productId) {
		Cart cart = cartRepository.findById(cartId)
				.orElseThrow(() -> new IllegalArgumentException("Carrito no encontrado"));

		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));

		Optional<ProductCart> existingProductCart = productCartRepository.findByCartAndProduct(cart, product);

		if (existingProductCart.isPresent()) {
			ProductCart productCart = existingProductCart.get();
			productCartRepository.deleteById(productCart.getId());
		} else {
			throw new IllegalArgumentException("Producto no encontrado en el carrito");
		}
	}

	public void deleteProductCartByCartId(long cartId) {
		productCartRepository.deleteByCartId(cartId);
	}
	
	private ProductOnCartDTO mapToProductOnCartDTO(ProductCart productCart) {
	    ProductOnCartDTO dto = new ProductOnCartDTO();
	    dto.setId(productCart.getProduct().getId());
	    dto.setName(productCart.getProduct().getName());
	    dto.setUnitPrice(productCart.getProduct().getPrice());
	    dto.setQuantity(productCart.getQuantity());
	    dto.setTotalPrice(productCart.getQuantity() * productCart.getProduct().getPrice());
	    return dto;
	}


}

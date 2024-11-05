package com.coderhouse.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.models.ProductCart;
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

	public List<ProductCart> getAllProductCart() {
		return productCartRepository.findAll();
	}

	public ProductCart findById(Long id) {
		return productCartRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("El detalle no existe"));
	}

	@Transactional
	public ProductCart addProductToCart(Long cid, Long pid, int quantity) {
		
		System.out.println("ID Carrito: " + cid);
	    System.out.println("ID Producto: " + pid);
		
		Cart cart = cartRepository.findById(cid)
				.orElseThrow(() -> new IllegalArgumentException("Carrito no encontrado"));

		Product product = productRepository.findById(pid)
				.orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));

		ProductCart productCart = new ProductCart();
		productCart.setCart(cart);
		productCart.setProduct(product);
		productCart.setQuantity(quantity);

		return productCartRepository.save(productCart);

	}

}

package com.coderhouse.services;

import java.util.List;
import java.util.Optional;

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

	public List<ProductCart> getAllProductsFromCart(Long cart) {
		return productCartRepository.findByCartId(cart);
	}

	@Transactional
	public ProductCart addProductToCart(Long cartId, Long productId, int quantity) {

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
			return productCartRepository.save(productCart);
		} else {
			ProductCart newProductCart = new ProductCart();
			newProductCart.setQuantity(quantity);
			newProductCart.setCart(cart);
			newProductCart.setProduct(product);
			return productCartRepository.save(newProductCart);
		}

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

}

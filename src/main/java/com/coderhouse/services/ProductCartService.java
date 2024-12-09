package com.coderhouse.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.models.ProductCart;
import com.coderhouse.dtos.CartResDTO;
import com.coderhouse.dtos.ProductOnCartDTO;
import com.coderhouse.mapper.CartMapper;
import com.coderhouse.mapper.ProductCartMapper;
import com.coderhouse.models.Cart;
import com.coderhouse.models.Product;
import com.coderhouse.repositories.ProductCartRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductCartService {

	@Autowired
	private ProductCartRepository productCartRepository;

	@Autowired
	private ProductService productService;

	@Autowired
	private CartService cartService;

	@Autowired
	private CartMapper cartMapper;

	@Autowired
	private ProductCartMapper productCartMapper;
	
	public List<ProductOnCartDTO> getAllProductsFromCart(Long cartId) {
		List<ProductCart> productCarts = productCartRepository.findByCartId(cartId);
		return productCarts.stream().map(productCartMapper::toDTO).toList();
	}

	@Transactional
	public CartResDTO addProductToCart(Long cartId, Long productId, int quantity) {

		if (quantity <= 0) {
			throw new IllegalArgumentException("Tiene que ingresar una cantidad superior a cero.");
		}

		Cart cart = cartService.getCartById(cartId);

		Product product = productService.getProductById(productId);

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

		return cartMapper.toDTO(cart);
	}

	public void deleteProductFromCart(Long cartId, Long productId) {
		Cart cart = cartService.getCartById(cartId);

		Product product = productService.getProductById(productId);

		Optional<ProductCart> existingProductCart = productCartRepository.findByCartAndProduct(cart, product);

		if (existingProductCart.isPresent()) {
			ProductCart productCart = existingProductCart.get();
			productCartRepository.deleteById(productCart.getId());
		} else {
			throw new IllegalArgumentException("Producto no encontrado en el carrito");
		}
	}

	@Transactional
	public void deleteProductCartByCartId(long cartId) {
		productCartRepository.deleteByCartId(cartId);
	}
	
	public CartResDTO clearCart(Long cartId) {
		cartService.getCartById(cartId);
		productCartRepository.deleteByCartId(cartId);
	    return cartService.getById(cartId);
	}


}

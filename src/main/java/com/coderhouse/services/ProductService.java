package com.coderhouse.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.models.Product;
import com.coderhouse.repositories.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}
	
	public Product findById(Long id) {
	    return productRepository.findById(id)
	            .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado."));
	}
	
	@Transactional
	public Product saveAlumno(Product product) {
		return productRepository.save(product);
	}
	
	@Transactional
	public Product updateProduct(Long id, Product productDetails) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Producto no encontrado."));
		
		product.setPrice(productDetails.getPrice());
		product.setStock(productDetails.getStock());
		
		if(productDetails.getName() != null && !productDetails.getName().isEmpty()) {
			product.setName(productDetails.getName());
		}
		
		if(productDetails.getDescription() != null && !productDetails.getDescription().isEmpty()) {
			product.setDescription(productDetails.getDescription());
		}
		
		if(productDetails.getImage() != null && !productDetails.getImage().isEmpty()) {
			product.setImage(productDetails.getImage());
		}
		
		if(productDetails.getCode() != null && !productDetails.getCode().isEmpty()) {
			product.setCode(productDetails.getCode());
		}
		
		if(productDetails.getCategory() != null ) {
			product.setCategory(productDetails.getCategory());
		}
		
		return productRepository.save(product);
		
	}
	
	public void deleteProduct(Long id) {
		if(!productRepository.existsById(id)) {
			throw new IllegalArgumentException("Producto no encontrado.");
		}
		productRepository.deleteById(id);
	}

}

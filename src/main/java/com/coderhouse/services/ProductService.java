package com.coderhouse.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.dto.ProductDTO;
import com.coderhouse.models.Product;
import com.coderhouse.models.Category;
import com.coderhouse.repositories.CategoryRepository;
import com.coderhouse.repositories.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	public List<Product> getAll() {
		return productRepository.findAll();
	}

	public Product getById(Long id) {
		return productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Producto con ID " + id + " no encontrado."));
	}

	@Transactional
	public Product save(ProductDTO productDTO) {
		Category category = categoryRepository.findById(productDTO.getCategory())
				.orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada"));

		Product product = new Product();
		product.setName(productDTO.getName());
		product.setImage(productDTO.getImage());
		product.setDescription(productDTO.getDescription());
		product.setStock(productDTO.getStock());
		product.setPrice(productDTO.getPrice());
		product.setCategory(category);

		return productRepository.save(product);
	}

	@Transactional
	public Product update(Long id, ProductDTO productDTO) {
		Product product = getById(id);

		if (productDTO.getPrice() != null) {
			product.setPrice(productDTO.getPrice());
		}

		if (productDTO.getStock() != null) {
			product.setStock(productDTO.getStock());
		}

		if (productDTO.getName() != null && !productDTO.getName().isEmpty()) {
			product.setName(productDTO.getName());
		}

		if (productDTO.getDescription() != null && !productDTO.getDescription().isEmpty()) {
			product.setDescription(productDTO.getDescription());
		}

		if (productDTO.getImage() != null && !productDTO.getImage().isEmpty()) {
			product.setImage(productDTO.getImage());
		}

		if (productDTO.getCategory() != null) {
			Category category = categoryRepository.findById(productDTO.getCategory())
					.orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada"));
			product.setCategory(category);
		}

		return productRepository.save(product);

	}

	@Transactional
	public void delete(Long id) {
		if (!productRepository.existsById(id)) {
			throw new IllegalArgumentException("Producto no encontrado.");
		}
		productRepository.deleteById(id);
	}

	@Transactional
	public Product assignCategoryToProduct(Long productId, Long categoryId) {

		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new IllegalArgumentException("Producto no encontrado."));

		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada"));

		if (product.getCategory() != null && product.getCategory().getId() == categoryId.longValue()) {
			throw new IllegalArgumentException("La categoría ya está asignada al producto.");
		}

		product.setCategory(category);
		return productRepository.save(product);
	}

}

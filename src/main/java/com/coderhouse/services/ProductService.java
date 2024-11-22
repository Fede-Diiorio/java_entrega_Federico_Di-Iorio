package com.coderhouse.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.dtos.ProductDTO;
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
		return productRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Producto con ID " + id + " no encontrado."));
	}

	@Transactional
	public Product save(ProductDTO productDTO) {
		validateMandatoryFields(productDTO);
		return productRepository.save(convertToProduct(productDTO, new Product()));
	}

	@Transactional
	public Product update(Long id, ProductDTO productDTO) {
		Product product = getById(id);

		validatePriceAndStock(productDTO);

		return productRepository.save(convertToProduct(productDTO, product));
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

	private Product convertToProduct(ProductDTO productDTO, Product product) {
		if (productDTO.getName() != null && !productDTO.getName().isEmpty()) {
			product.setName(productDTO.getName());
		}

		if (productDTO.getImage() != null && !productDTO.getImage().isEmpty()) {
			product.setImage(productDTO.getImage());
		}

		if (productDTO.getDescription() != null && !productDTO.getDescription().isEmpty()) {
			product.setDescription(productDTO.getDescription());
		}

		if (productDTO.getStock() != null) {
			product.setStock(productDTO.getStock());
		}

		if (productDTO.getPrice() != null) {
			product.setPrice(productDTO.getPrice());
		}

		if (productDTO.getCategory() != null) {
			Category category = categoryRepository.findById(productDTO.getCategory())
					.orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada"));
			product.setCategory(category);
		}

		return product;
	}

	private void validatePriceAndStock(ProductDTO productDTO) {
		if (productDTO.getPrice() == null || productDTO.getPrice() < 1) {
			throw new IllegalArgumentException("Debe establecer un precio mínimo del, al menos, $1.");
		}
		if (productDTO.getDescription() == null || productDTO.getDescription().isEmpty()) {
			throw new IllegalArgumentException("La descripción del producto es obligatoria.");
		}
	}

	private void validateMandatoryFields(ProductDTO productDTO) {
		if (productDTO.getName() == null || productDTO.getName().isEmpty()) {
			throw new IllegalArgumentException("El nombre del producto es obligatorio.");
		}
		if (productDTO.getDescription() == null || productDTO.getDescription().isEmpty()) {
			throw new IllegalArgumentException("La descripción del producto es obligatoria.");
		}
		validatePriceAndStock(productDTO);
	}

}

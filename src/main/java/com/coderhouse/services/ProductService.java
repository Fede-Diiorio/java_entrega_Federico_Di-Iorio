package com.coderhouse.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.dtos.ProductDTO;
import com.coderhouse.dtos.ProductResponseDTO;
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

	public List<ProductResponseDTO> getAll() {
		List<Product> products = productRepository.findAll();
		return products.stream().map(this::mapToProductResponseDTO).toList();
	}
	
	public Product getProductById(Long id) {
		return productRepository.findById(id)
		.orElseThrow(() -> new IllegalArgumentException("Producto con ID " + id + " no encontrado."));
	}

	public ProductResponseDTO getById(Long id) {
		Product product = getProductById(id);
		return mapToProductResponseDTO(product);
	}

	@Transactional
	public ProductResponseDTO save(ProductDTO productDTO) {
		validateMandatoryFields(productDTO);
		Product product = new Product();
		productRepository.save(convertToProduct(productDTO, product));

		return mapToProductResponseDTO(product);
	}

	@Transactional
	public ProductResponseDTO update(Long id, ProductDTO productDTO) {
		Product product = getProductById(id);

		validatePriceAndStock(productDTO);

		productRepository.save(convertToProduct(productDTO, product));

		return mapToProductResponseDTO(product);
	}

	@Transactional
	public void delete(Long id) {
		if (!productRepository.existsById(id)) {
			throw new IllegalArgumentException("Producto no encontrado.");
		}
		productRepository.deleteById(id);
	}

	@Transactional
	public ProductResponseDTO assignCategoryToProduct(Long productId, Long categoryId) {

		Product product = getProductById(categoryId);

		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada"));

		if (product.getCategory() != null && product.getCategory().getId() == categoryId.longValue()) {
			throw new IllegalArgumentException("La categoría ya está asignada al producto.");
		}

		product.setCategory(category);
		productRepository.save(product);
		return mapToProductResponseDTO(product);
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
			if (category != null) {
				product.setCategory(category);
			}
		}

		return product;
	}

	private void validatePriceAndStock(ProductDTO productDTO) {
		if (productDTO.getPrice() != null && productDTO.getPrice() < 1) {
			throw new IllegalArgumentException("Debe establecer un precio mínimo de al menos $1.");
		}
		if (productDTO.getStock() != null && productDTO.getStock() < 0) {
			throw new IllegalArgumentException("No puede establecer un stock negativo.");
		}
	}

	private void validateMandatoryFields(ProductDTO productDTO) {
		if (productDTO.getName() == null || productDTO.getName().isEmpty()) {
			throw new IllegalArgumentException("El nombre del producto es obligatorio.");
		}
		if (productDTO.getDescription() == null || productDTO.getDescription().isEmpty()) {
			throw new IllegalArgumentException("La descripción del producto es obligatoria.");
		}
		if (productDTO.getPrice() == null || productDTO.getPrice() < 1) {
			throw new IllegalArgumentException("El precio del producto es obligatorio.");
		}
		if (productDTO.getStock() == null || productDTO.getStock() < 0) {
			throw new IllegalArgumentException("El stock del producto es obligatorio.");
		}
	}

	private ProductResponseDTO mapToProductResponseDTO(Product product) {
		ProductResponseDTO dto = new ProductResponseDTO();
		dto.setId(product.getId());
		dto.setName(product.getName());
		dto.setDescription(product.getDescription());
		dto.setPrice(product.getPrice());
		dto.setStock(product.getStock());
		dto.setImage(product.getImage());
		dto.setCode(product.getCode());
		dto.setCategory(product.getCategory() != null ? product.getCategory().getName() : "sin-categoria");
		return dto;
	}

}

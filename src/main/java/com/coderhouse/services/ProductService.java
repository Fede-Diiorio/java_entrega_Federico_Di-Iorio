package com.coderhouse.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.dtos.ProductReqDTO;
import com.coderhouse.dtos.ProductResDTO;
import com.coderhouse.mapper.ProductMapper;
import com.coderhouse.models.Product;
import com.coderhouse.repositories.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductMapper productMapper;

	public List<ProductResDTO> getAll() {
		List<Product> products = productRepository.findAll();
		return products.stream().map(productMapper::toDTO).toList();
	}

	public Product getProductById(Long id) {
		return productRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Producto con ID " + id + " no encontrado."));
	}

	public ProductResDTO getById(Long id) {
		Product product = getProductById(id);
		return productMapper.toDTO(product);
	}

	@Transactional
	public ProductResDTO save(ProductReqDTO productDTO) {
		validateMandatoryFields(productDTO);

		Product product = productMapper.toEntity(productDTO, null);

		productRepository.save(product);

		return productMapper.toDTO(product);

	}

	@Transactional
	public ProductResDTO update(Long id, ProductReqDTO productDTO) {
		Product product = getProductById(id);

		validatePriceAndStock(productDTO);

		productRepository.save(productMapper.toEntity(productDTO, product));

		return productMapper.toDTO(product);
	}

	@Transactional
	public void delete(Long id) {
		if (!productRepository.existsById(id)) {
			throw new IllegalArgumentException("Producto no encontrado.");
		}
		productRepository.deleteById(id);
	}

	private void validatePriceAndStock(ProductReqDTO productDTO) {
		if (productDTO.getPrice() != null && productDTO.getPrice() < 1) {
			throw new IllegalArgumentException("Debe establecer un precio mínimo de al menos $1.");
		}
		if (productDTO.getStock() != null && productDTO.getStock() < 0) {
			throw new IllegalArgumentException("No puede establecer un stock negativo.");
		}
	}

	private void validateMandatoryFields(ProductReqDTO productDTO) {
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
	


}

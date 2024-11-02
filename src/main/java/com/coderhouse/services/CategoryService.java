package com.coderhouse.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.models.Category;
import com.coderhouse.repositories.CategoryRepository;

import jakarta.transaction.Transactional;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

	public Category findById(Long id) {
		return categoryRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada"));
	}

	@Transactional
	public Category saveCategory(Category category) {
		if (category.getSlug() == null) {
	        category.setSlug("sin-categoria");
	    }
		return categoryRepository.save(category);
	}

	@Transactional
	public Category updateCategory(Long id, Category categoryDetails) {
		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada"));
		category.setName(categoryDetails.getName());

		if (categoryDetails.getSlug() != null || !categoryDetails.getSlug().isEmpty()) {
			category.setSlug(categoryDetails.getSlug());
		}

		return categoryRepository.save(category);
	}

	public void deleteCategory(Long id) {
		if (!categoryRepository.existsById(id)) {
			throw new IllegalArgumentException("Categoria no encontrada.");
		}
		categoryRepository.deleteById(id);
	}
}

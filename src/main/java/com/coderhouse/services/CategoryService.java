package com.coderhouse.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.dtos.CategoryDTO;
import com.coderhouse.interfaces.DAOInterface;
import com.coderhouse.models.Category;
import com.coderhouse.repositories.CategoryRepository;

import jakarta.transaction.Transactional;

@Service
public class CategoryService implements DAOInterface<Category, CategoryDTO> {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<CategoryDTO> getAll() {
		return categoryRepository.findAll().stream().map(this::convertToDTO).toList();
	}

	@Override
	public CategoryDTO getById(Long id) {
		Category category = getCategoryById(id);
		return convertToDTO(category);
	}

	@Override
	@Transactional
	public CategoryDTO save(Category object) {
		Category savedCategory = categoryRepository.save(object);
		return convertToDTO(savedCategory);
	}

	@Override
	@Transactional
	public CategoryDTO update(Long id, Category object) throws Exception {
		Category category = getCategoryById(id);

		if (object.getName() != null && !object.getName().isEmpty()) {
			category.setName(object.getName());
		}

		if (object.getSlug() != null && !object.getSlug().isEmpty()) {
			category.setSlug(object.getSlug());
		}

		Category updatedCategory = categoryRepository.save(category);
		return convertToDTO(updatedCategory);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		if (!categoryRepository.existsById(id)) {
			throw new IllegalArgumentException("Categoria no encontrada");
		}
		categoryRepository.deleteById(id);

	}

	public Category getCategoryById(Long id) {
		return categoryRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada"));
	}

	public Category getByName(String categoryName) {
		return categoryRepository.findByName(categoryName);

	}

	private CategoryDTO convertToDTO(Category category) {
		return new CategoryDTO(category.getId(), category.getName(), category.getSlug());
	}

}

package com.coderhouse.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.interfaces.DAOInterface;
import com.coderhouse.models.Category;
import com.coderhouse.repositories.CategoryRepository;

import jakarta.transaction.Transactional;

@Service
public class CategoryService implements DAOInterface<Category> {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public List<Category> getAll() {
		return categoryRepository.findAll();
	}

	@Override
	public Category getById(Long id) {
		return categoryRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada"));
	}

	@Override
	@Transactional
	public Category save(Category object) {
		if (object.getSlug() == null) {
	        object.setSlug("sin-categoria");
	    }
		return categoryRepository.save(object);
	}

	@Override
	@Transactional
	public Category update(Long id, Category object) throws Exception {
		Category category = getById(id);
		category.setName(object.getName());

		if (object.getSlug() != null && !object.getSlug().isEmpty()) {
			category.setSlug(object.getSlug());
		}

		return categoryRepository.save(category);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		if (!categoryRepository.existsById(id)) {
			throw new IllegalArgumentException("Categoria no encontrada");
		}
		categoryRepository.deleteById(id);
		
	}

}

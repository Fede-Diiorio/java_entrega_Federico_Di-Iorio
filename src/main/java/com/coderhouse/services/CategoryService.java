package com.coderhouse.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.dtos.CategoryReqDTO;
import com.coderhouse.dtos.CategoryResDTO;
import com.coderhouse.interfaces.DAOInterface;
import com.coderhouse.mapper.CategoryMapper;
import com.coderhouse.models.Category;
import com.coderhouse.repositories.CategoryRepository;

import jakarta.transaction.Transactional;

@Service
public class CategoryService implements DAOInterface<CategoryReqDTO, CategoryResDTO> {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private CategoryMapper categoryMapper;

	@Override
	public List<CategoryResDTO> getAll() {
		return categoryRepository.findAll().stream().map(categoryMapper::toDTO).toList();
	}

	@Override
	public CategoryResDTO getById(Long id) {
		Category category = getCategoryById(id);
		return categoryMapper.toDTO(category);
	}

	@Override
	@Transactional
	public CategoryResDTO save(CategoryReqDTO object) {
		Category category = categoryMapper.toEntity(object, null);

		categoryRepository.save(category);

		return categoryMapper.toDTO(category);
	}

	@Override
	@Transactional
	public CategoryResDTO update(Long id, CategoryReqDTO object) throws Exception {
		Category category = getCategoryById(id);

		categoryRepository.save(categoryMapper.toEntity(object, category));

		return categoryMapper.toDTO(category);
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

}

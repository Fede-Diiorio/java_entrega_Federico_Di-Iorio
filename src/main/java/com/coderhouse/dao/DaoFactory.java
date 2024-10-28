package com.coderhouse.dao;

import org.springframework.stereotype.Service;

import com.coderhouse.models.Product;
import com.coderhouse.models.ProductCategory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Service
public class DaoFactory {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void createCategory(ProductCategory category) {
		entityManager.persist(category);
	}
	
	@Transactional
	public ProductCategory getCategoryById(long categoryId) throws Exception {
		try {
			TypedQuery<ProductCategory> query = entityManager.createQuery(
					"SELECT ca FROM ProductCategory ca WHERE ca.id = :categoryId", ProductCategory.class);
			query.setParameter("categoryId", categoryId);
			return query.getSingleResult();
		} catch (NoResultException e) {
	        throw new Exception("La marca no existe");
	    }
	}
	
	@Transactional
	public void createProduct(Product product) {
		entityManager.persist(product);
	}
}

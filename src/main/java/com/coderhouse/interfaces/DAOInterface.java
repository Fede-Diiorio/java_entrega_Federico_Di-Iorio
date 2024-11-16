package com.coderhouse.interfaces;

import java.util.List;

public interface DAOInterface<T> {

	List<T> getAll();

	T getById(Long id);

	T save(T object);

	T update(Long id, T object) throws Exception;

	void delete(Long id);

}
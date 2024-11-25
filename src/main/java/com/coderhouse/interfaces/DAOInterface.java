package com.coderhouse.interfaces;

import java.util.List;

public interface DAOInterface<T, DTO> {

    List<DTO> getAll();

    DTO getById(Long id);

    DTO save(T object);

    DTO update(Long id, T object) throws Exception;

    void delete(Long id);
}

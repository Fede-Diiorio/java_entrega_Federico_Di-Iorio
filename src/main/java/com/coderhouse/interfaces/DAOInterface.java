package com.coderhouse.interfaces;

import java.util.List;

public interface DAOInterface<reqDTO, resDTO> {

    List<resDTO> getAll();

    resDTO getById(Long id);

    resDTO save(reqDTO object);

    resDTO update(Long id, reqDTO object) throws Exception;

    void delete(Long id);
}

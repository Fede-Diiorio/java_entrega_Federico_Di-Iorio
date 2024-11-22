package com.coderhouse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderhouse.models.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
	Client findByCartId(Long cartId);
}

package com.coderhouse.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderhouse.models.TicketProduct;

public interface TicketProductRepository extends JpaRepository<TicketProduct, Long>{

	List<TicketProduct> findByTicketId(Long ticketId);
}

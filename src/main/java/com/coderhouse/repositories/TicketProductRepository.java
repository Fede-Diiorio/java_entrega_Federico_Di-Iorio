package com.coderhouse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderhouse.models.TicketProduct;

public interface TicketProductRepository extends JpaRepository<TicketProduct, Long>{

}

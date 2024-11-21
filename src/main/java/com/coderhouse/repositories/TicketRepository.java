package com.coderhouse.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderhouse.models.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long>{
	List<Ticket> findTicketByClientId(Long clientId);
}

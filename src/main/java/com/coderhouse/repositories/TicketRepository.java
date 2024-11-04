package com.coderhouse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderhouse.models.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long>{

}

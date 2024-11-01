package com.coderhouse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderhouse.models.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long>{

}

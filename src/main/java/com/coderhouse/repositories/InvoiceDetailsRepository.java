package com.coderhouse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderhouse.models.InvoiceDetail;

public interface InvoiceDetailsRepository extends JpaRepository<InvoiceDetail, Long>{

}

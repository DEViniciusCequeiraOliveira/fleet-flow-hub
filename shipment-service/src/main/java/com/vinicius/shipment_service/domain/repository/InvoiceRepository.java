package com.vinicius.shipment_service.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vinicius.shipment_service.domain.model.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, UUID> {

}

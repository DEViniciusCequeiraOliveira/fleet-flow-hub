package com.vinicius.transfer_service.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vinicius.transfer_service.domain.model.Transfer;

public interface TransferRepository extends JpaRepository<Transfer, UUID> {
    
}

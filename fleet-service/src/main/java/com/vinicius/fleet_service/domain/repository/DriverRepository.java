package com.vinicius.fleet_service.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vinicius.fleet_service.domain.model.Driver;

public interface DriverRepository extends JpaRepository<Driver, UUID> {

}

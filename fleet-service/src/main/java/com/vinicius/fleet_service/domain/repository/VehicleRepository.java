package com.vinicius.fleet_service.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vinicius.fleet_service.domain.model.Vehicle;

/**
 * VehicleRepository
 */
public interface VehicleRepository extends JpaRepository<Vehicle, UUID> {

    
}
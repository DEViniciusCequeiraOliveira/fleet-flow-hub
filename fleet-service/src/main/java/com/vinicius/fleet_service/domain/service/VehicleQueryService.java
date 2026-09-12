package com.vinicius.fleet_service.domain.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vinicius.fleet_service.api.dto.response.VehicleResponse;
import com.vinicius.fleet_service.domain.model.Vehicle;
import com.vinicius.fleet_service.domain.repository.VehicleRepository;

import lombok.RequiredArgsConstructor;

/**
 * VehicleQueryService
 */
@Service 
@RequiredArgsConstructor 
public class VehicleQueryService {

    private final VehicleRepository vehicleRepository;

    public VehicleResponse getVehicleById(UUID vehicleId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new IllegalArgumentException("Vehicle not found")); 
        return  mapToVehicleResponse(vehicle);                
    }


    public Page<VehicleResponse> getVehicles(Pageable pageable) {
        Page<Vehicle> vehiclePage = vehicleRepository.findAll(pageable);
        return vehiclePage.map(this::mapToVehicleResponse);
    }

    private VehicleResponse mapToVehicleResponse(Vehicle vehicle) {
        VehicleResponse response = VehicleResponse.builder()
                .id(vehicle.getId())
                .licensePlate(vehicle.getLicensePlate())
                .model(vehicle.getModel())
                .type(vehicle.getType())
                .weightCapacity(vehicle.getWeightCapacity())
                .volumeCapacity(vehicle.getVolumeCapacity())
                .status(vehicle.getStatus())
                .build();
        return response;        
    }
    
}
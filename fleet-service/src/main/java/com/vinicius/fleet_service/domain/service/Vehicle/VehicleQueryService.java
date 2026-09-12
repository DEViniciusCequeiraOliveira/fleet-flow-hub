package com.vinicius.fleet_service.domain.service.Vehicle;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vinicius.fleet_service.api.dto.response.VehicleResponse;
import com.vinicius.fleet_service.application.utility.Mapper;
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
    private final Mapper mapper;

    public VehicleResponse getVehicleById(UUID vehicleId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new IllegalArgumentException("Vehicle not found"));
        return mapToVehicleResponse(vehicle);
    }

    public Page<VehicleResponse> getVehicles(Pageable pageable) {
        Page<Vehicle> vehiclePage = vehicleRepository.findAll(pageable);
        return vehiclePage.map(this::mapToVehicleResponse);
    }

    private VehicleResponse mapToVehicleResponse(Vehicle vehicle) {
        return mapper.convert(vehicle, VehicleResponse.class);
    }

}
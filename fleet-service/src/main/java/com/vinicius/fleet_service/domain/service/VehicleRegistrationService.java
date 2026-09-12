package com.vinicius.fleet_service.domain.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vinicius.fleet_service.api.dto.request.VehicleCrudRequest;
import com.vinicius.fleet_service.api.dto.response.VehicleResponse;
import com.vinicius.fleet_service.domain.model.Vehicle;
import com.vinicius.fleet_service.domain.repository.VehicleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VehicleRegistrationService {

    final private VehicleRepository vehicleRepository;

    @Transactional
    public VehicleResponse createVehicle(VehicleCrudRequest vehicleRequest) {
        Vehicle newVehicle = Vehicle.create(
                vehicleRequest.getLicensePlate(),
                vehicleRequest.getModel(),
                vehicleRequest.getType(),
                vehicleRequest.getWeightCapacity(),
                vehicleRequest.getVolumeCapacity());

        return mapToVehicleResponse(newVehicle);
    }

    @Transactional
    public VehicleResponse updateVehicle(UUID id, VehicleCrudRequest vehicleRequest) {
        Vehicle existingVehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Vehicle not found"));

        existingVehicle.setLicensePlate(vehicleRequest.getLicensePlate());
        existingVehicle.setModel(vehicleRequest.getModel());
        existingVehicle.setType(vehicleRequest.getType());
        existingVehicle.setWeightCapacity(vehicleRequest.getWeightCapacity());
        existingVehicle.setVolumeCapacity(vehicleRequest.getVolumeCapacity());

        return mapToVehicleResponse(existingVehicle);
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
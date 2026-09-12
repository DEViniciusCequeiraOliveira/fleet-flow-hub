package com.vinicius.fleet_service.domain.service.Vehicle;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vinicius.fleet_service.api.dto.request.VehicleCrudRequest;
import com.vinicius.fleet_service.api.dto.response.VehicleResponse;
import com.vinicius.fleet_service.application.utility.Mapper;
import com.vinicius.fleet_service.domain.model.Vehicle;
import com.vinicius.fleet_service.domain.repository.VehicleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VehicleRegistrationService {

    final private VehicleRepository vehicleRepository;
    private final Mapper mapper;

    @Transactional
    public VehicleResponse createVehicle(VehicleCrudRequest vehicleRequest) {

        Vehicle newVehicle = Vehicle.create(
                vehicleRequest.getLicensePlate(),
                vehicleRequest.getModel(),
                vehicleRequest.getType(),
                vehicleRequest.getWeightCapacity(),
                vehicleRequest.getVolumeCapacity());

        Vehicle savedVehicle = vehicleRepository.save(newVehicle);

        return mapToVehicleResponse(savedVehicle);
    }

    @Transactional
    public VehicleResponse updateVehicle(UUID vehicleId, VehicleCrudRequest vehicleRequest) {
        Vehicle existingVehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new IllegalArgumentException("Vehicle not found"));

        existingVehicle.setLicensePlate(vehicleRequest.getLicensePlate());
        existingVehicle.setModel(vehicleRequest.getModel());
        existingVehicle.setType(vehicleRequest.getType());
        existingVehicle.setWeightCapacity(vehicleRequest.getWeightCapacity());
        existingVehicle.setVolumeCapacity(vehicleRequest.getVolumeCapacity());

        Vehicle updatedVehicle = vehicleRepository.save(existingVehicle);

        return mapToVehicleResponse(updatedVehicle);
    }

    private VehicleResponse mapToVehicleResponse(Vehicle vehicle) {
        return mapper.convert(vehicle, VehicleResponse.class);
    }

}
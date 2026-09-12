package com.vinicius.fleet_service.api.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinicius.fleet_service.api.dto.request.VehicleCrudRequest;
import com.vinicius.fleet_service.api.dto.response.VehicleResponse;
import com.vinicius.fleet_service.domain.service.VehicleQueryService;
import com.vinicius.fleet_service.domain.service.VehicleRegistrationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/v1/vehicles")
@RequiredArgsConstructor
public class VehicleController {

    final private VehicleRegistrationService vehicleRegistrationService;
    final private VehicleQueryService vehicleQueryService;

    @GetMapping
    public Page<VehicleResponse> getVehicles(@PageableDefault  Pageable pageable) {
        return vehicleQueryService.getVehicles(pageable);
    }

    @GetMapping("/{id}")
    public VehicleResponse getVehicleById(@PathVariable UUID id) {
        return vehicleQueryService.getVehicleById(id);
    }

    @PostMapping
    public VehicleResponse createVehicle(@Valid @RequestBody VehicleCrudRequest vehicleRequest) {
        return vehicleRegistrationService.createVehicle(vehicleRequest);
    }

    @PutMapping("/{id}")
    public VehicleResponse updateVehicle(@PathVariable UUID id, @Valid @RequestBody VehicleCrudRequest vehicleRequest) {
        return vehicleRegistrationService.updateVehicle(id, vehicleRequest);
    }

}

package com.vinicius.fleet_service.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinicius.fleet_service.api.dto.request.DriverCrudRequest;
import com.vinicius.fleet_service.api.dto.response.DriverResponse;
import com.vinicius.fleet_service.domain.service.Driver.DriverQueryService;
import com.vinicius.fleet_service.domain.service.Driver.DriverRegistrationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/drivers")
@RequiredArgsConstructor
public class DriverController {

    final private DriverRegistrationService driverRegistrationService;
    final private DriverQueryService driverQueryService;

    @GetMapping
    public Page<DriverResponse> getVehicles(@PageableDefault Pageable pageable) {
        return driverQueryService.getDrivers(pageable);
    }

    @GetMapping("/{driverId}")
    public DriverResponse getDriverById(@PathVariable UUID driverId) {
        return driverQueryService.getDriverById(driverId);
    }

    @PostMapping
    public DriverResponse createDriver(@Valid @RequestBody DriverCrudRequest driverRequest) {
        return driverRegistrationService.createDriver(driverRequest);
    }

    @PutMapping("/{driverId}")
    public DriverResponse updateDriver(@PathVariable UUID driverId,
            @Valid @RequestBody DriverCrudRequest driverRequest) {
        return driverRegistrationService.updateDriver(driverId, driverRequest);
    }

}

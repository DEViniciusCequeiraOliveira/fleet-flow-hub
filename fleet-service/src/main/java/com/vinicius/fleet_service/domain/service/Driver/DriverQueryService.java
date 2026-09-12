package com.vinicius.fleet_service.domain.service.Driver;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vinicius.fleet_service.api.dto.response.DriverResponse;
import com.vinicius.fleet_service.domain.model.Driver;
import com.vinicius.fleet_service.domain.repository.DriverRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DriverQueryService {

    final private DriverRepository driverRepository;

    public Page<DriverResponse> getDrivers(Pageable pageable) {
        Page<Driver> driverPage = driverRepository.findAll(pageable);
        return driverPage.map(this::mapToDriverResponse);
    }

    public DriverResponse getDriverById(UUID driverId) {
        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new IllegalArgumentException("Driver not found"));
        return mapToDriverResponse(driver);
    }

    private DriverResponse mapToDriverResponse(Driver driver) {
        DriverResponse response = DriverResponse.builder()
                .id(driver.getId())
                .name(driver.getName())
                .document(driver.getDocument())
                .driverLicense(driver.getDriverLicense())
                .licenseCategory(driver.getLicenseCategory())
                .status(driver.getStatus())
                .build();
        return response;
    }
}

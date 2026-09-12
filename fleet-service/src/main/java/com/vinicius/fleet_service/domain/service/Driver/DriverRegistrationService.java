package com.vinicius.fleet_service.domain.service.Driver;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vinicius.fleet_service.api.dto.request.DriverCrudRequest;
import com.vinicius.fleet_service.api.dto.response.DriverResponse;
import com.vinicius.fleet_service.domain.model.Driver;
import com.vinicius.fleet_service.domain.repository.DriverRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DriverRegistrationService {

    private final DriverRepository driverRepository;

    @Transactional()
    public DriverResponse createDriver(DriverCrudRequest driverRequest) {
        Driver driver = Driver.create(
                driverRequest.getName(),
                driverRequest.getDocument(),
                driverRequest.getDriverLicense(),
                driverRequest.getLicenseCategory());

        Driver driverSaved = driverRepository.save(driver);
        return mapToDriverResponse(driverSaved);
    }

    @Transactional 
    public DriverResponse updateDriver(UUID driverId, DriverCrudRequest driverRequest) {
        Driver existingDriver = driverRepository.findById(driverId)
                .orElseThrow(() -> new IllegalArgumentException("Driver not found"));

        existingDriver.setName(driverRequest.getName());
        existingDriver.setDocument(driverRequest.getDocument());
        existingDriver.setDriverLicense(driverRequest.getDriverLicense());
        existingDriver.setLicenseCategory(driverRequest.getLicenseCategory());

        Driver updatedDriver = driverRepository.save(existingDriver);

        return mapToDriverResponse(updatedDriver);
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

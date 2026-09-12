package com.vinicius.fleet_service.api.dto.response;

import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DriverResponse {
    private UUID id;
    private String name;
    private String document;
    private String driverLicense;
    private String licenseCategory;
    private String status;
}

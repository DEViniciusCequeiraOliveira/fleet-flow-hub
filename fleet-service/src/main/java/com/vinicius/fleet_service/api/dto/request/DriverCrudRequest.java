package com.vinicius.fleet_service.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DriverCrudRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String document;
    @NotBlank
    private String driverLicense;
    @NotBlank
    private String licenseCategory;
}

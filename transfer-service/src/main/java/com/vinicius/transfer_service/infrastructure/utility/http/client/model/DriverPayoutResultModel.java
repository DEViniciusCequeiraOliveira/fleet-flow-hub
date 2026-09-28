package com.vinicius.transfer_service.infrastructure.utility.http.client.model;

import java.util.UUID;

import lombok.Data;

@Data
public class DriverPayoutResultModel {
    private UUID id;
    private String name;
    private String document;
    private String driverLicense;
    private String licenseCategory;
    private String status;
}


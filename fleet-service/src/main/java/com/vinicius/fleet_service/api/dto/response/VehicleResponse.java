package com.vinicius.fleet_service.api.dto.response;

import java.util.UUID;

import lombok.Data;

@Data
public class VehicleResponse {
    private UUID id;
    private String licensePlate;
    private String model;
    private String type;
    private Double weightCapacity;
    private Double volumeCapacity;
    private String status;
}

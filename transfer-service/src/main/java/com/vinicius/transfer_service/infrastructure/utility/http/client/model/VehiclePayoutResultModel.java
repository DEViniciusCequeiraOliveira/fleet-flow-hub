package com.vinicius.transfer_service.infrastructure.utility.http.client.model;

import java.util.UUID;

import lombok.Data;

@Data
public class VehiclePayoutResultModel {
    private UUID id;
    private String licensePlate;
    private String model;
    private String type;
    private Double weightCapacity;
    private Double volumeCapacity;
    private String status;
}

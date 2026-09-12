package com.vinicius.fleet_service.api.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class VehicleCrudRequest {
    @NotBlank 
    private String licensePlate;
    @NotBlank 
    private String model;
    @NotBlank 
    private String type;
    @Min(0)
    private Double weightCapacity;
    @Min(0)
    private Double volumeCapacity;    
}

package com.vinicius.tracking_service.api.dto.request;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LocationRequest {
    @NotBlank
    private UUID vehicleId;

    @NotBlank
    private Double latitude;

    @NotBlank
    private Double longitude;

    @NotBlank
    private Double speed;
}

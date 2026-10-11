package com.vinicius.transfer_service.infrastructure.utility.http.client;

import java.util.UUID;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import com.vinicius.transfer_service.infrastructure.utility.http.client.model.VehiclePayoutResultModel;

@HttpExchange("/api/v1/vehicles")
public interface VehicleAPIClient {

    @GetExchange("/{vehicleId}")
    VehiclePayoutResultModel getVehicleById(@PathVariable UUID vehicleId);
}

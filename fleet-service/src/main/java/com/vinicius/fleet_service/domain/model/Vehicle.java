package com.vinicius.fleet_service.domain.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Vehicle
 */
@Entity
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter(AccessLevel.PRIVATE)
public class Vehicle {

    private UUID id;
    private String licensePlate;
    private String model;
    private String type;
    private Double weightCapacity;
    private Double volumeCapacity;
    private String status;

    public static Vehicle create(String licensePlate, String model, String type, Double weightCapacity, Double volumeCapacity) {
        Vehicle vehicle = new Vehicle();
        vehicle.id = UUID.randomUUID();
        vehicle.licensePlate = licensePlate;
        vehicle.model = model;
        vehicle.type = type;
        vehicle.weightCapacity = weightCapacity;
        vehicle.volumeCapacity = volumeCapacity;
        vehicle.status = "A";
        return vehicle;
    }
}
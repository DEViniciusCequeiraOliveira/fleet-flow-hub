package com.vinicius.tracking_service.domain.model;

import java.time.Instant;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter(AccessLevel.PRIVATE)
public class Location {
    @Id
    @EqualsAndHashCode.Include
    private UUID id;
    private UUID vehicleId;
    private Double latitude;
    private Double longitude;
    private Double speed;
    private Instant timestamp;

    public static Location create(UUID vehicleId, Double latitude, Double longitude, Double speed) {
        Location location = new Location();
        location.id = UUID.randomUUID();
        location.vehicleId = vehicleId;
        location.latitude = latitude;
        location.longitude = longitude;
        location.speed = speed;
        location.timestamp = Instant.now();
        return location;
    }
}
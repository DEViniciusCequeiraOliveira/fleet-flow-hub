package com.vinicius.distribution_service.domain.model;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter(AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Route {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;
    private UUID distributionCenterId;
    private UUID vehicleId;
    private UUID driverId;
    @Enumerated(EnumType.STRING)
    private RouteStatus status;
    private OffsetDateTime createdAt;
    private OffsetDateTime plannedStartAt;
    private OffsetDateTime startedAt;
    private OffsetDateTime estimatedCompletionAt;
    private OffsetDateTime completedAt;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "route")
    private List<Stop> stops = new ArrayList<>();

    public static Route plan(UUID distributionCenterId, UUID vehicleId, UUID driverId) {
        Route route = new Route();
        route.id = UUID.randomUUID();
        route.distributionCenterId = distributionCenterId;
        route.vehicleId = vehicleId;
        route.driverId = driverId;
        route.status = RouteStatus.PLANNED;
        route.createdAt = OffsetDateTime.now();
        route.plannedStartAt = OffsetDateTime.now();
        route.estimatedCompletionAt = OffsetDateTime.now().plusHours(8);
        return route;
    }

    public UUID addStop(Long sequence, String recipientName, String address, Double latitude, Double longitude) {
        if (this.status != RouteStatus.PLANNED) {
            throw new IllegalArgumentException("Invalid status");
        }

        Stop stop = Stop.pend(this, sequence, recipientName, address, latitude, longitude);
        this.stops.add(stop);
        return stop.getId();
    }

    public void start() {
        this.startedAt = OffsetDateTime.now();
        changeStatusTo(RouteStatus.IN_PROGRESS);
    }

    public void cancel() {
        this.completedAt = OffsetDateTime.now();
        changeStatusTo(RouteStatus.CANCELLED);
    }

    public void arriveStop(UUID stopId) {
        this.stops.forEach((value) -> {
            if (value.getId().equals(stopId)) {
                value.arrive();
            }
        });
    }

    public void completeStop(UUID stopId) {
        this.stops.forEach((value) -> {
            if (value.getId().equals(stopId)) {
                value.complete();
            }
        });

        boolean allStopsFinished = this.stops.stream()
                .allMatch(stop -> stop.getStatus().isFinal());

        if (allStopsFinished) {
            changeStatusTo(RouteStatus.COMPLETED);
        }
    }

    public void failStop(UUID stopId) {
        this.stops.forEach((value) -> {
            if (value.getId().equals(stopId)) {
                value.fail();
            }
        });

        boolean allStopsFinished = this.stops.stream()
                .allMatch(stop -> stop.getStatus().isFinal());

        if (allStopsFinished) {
            changeStatusTo(RouteStatus.COMPLETED);
        }
    }

    public void skipStop(UUID stopId) {
        this.stops.forEach((value) -> {
            if (value.getId().equals(stopId)) {
                value.skip();
            }
        });

        boolean allStopsFinished = this.stops.stream()
                .allMatch(stop -> stop.getStatus().isFinal());

        if (allStopsFinished) {
            changeStatusTo(RouteStatus.COMPLETED);
        }
    }

    private void changeStatusTo(RouteStatus newStatus) {
        if (newStatus != null && this.getStatus().canNotTransitionFrom(newStatus)) {
            throw new IllegalArgumentException(
                    "Invalid status transition from " + this.getStatus() +
                            " to " + newStatus);
        }
        this.setStatus(newStatus);
    }

    public List<Stop> getStops() {
        return Collections.unmodifiableList(stops);
    }

}

package com.vinicius.distribution_service.domain.model;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter(AccessLevel.PRIVATE)
public class Stop {
    @Id
    @EqualsAndHashCode.Include
    private UUID id;
    @ManyToOne(optional = false)
    private Route route;
    private Long sequence;
    private String recipientName;
    private String address;
    private Double latitude;
    private Double longitude;
    @Enumerated(EnumType.STRING)
    private StopStatus status;
    private OffsetDateTime plannedArrivalAt;
    private OffsetDateTime arrivedAt;
    private OffsetDateTime completedAt;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "stop")
    private List<StopInvoice> stopInvoices = new ArrayList<>();

    public static Stop pend(Route route, Long sequence, String recipientName,
            String address, Double latitude, Double longitude) {
        Stop stop = new Stop();
        stop.id = UUID.randomUUID();
        stop.route = route;
        stop.sequence = sequence;
        stop.recipientName = recipientName;
        stop.address = address;
        stop.latitude = latitude;
        stop.longitude = longitude;
        stop.status = StopStatus.PENDING;
        stop.plannedArrivalAt = OffsetDateTime.now().plusHours(2);

        return stop;
    }

    public void arrive() {
        this.arrivedAt = OffsetDateTime.now();
        changeStatusTo(StopStatus.ARRIVED);
    }

    public void complete() {
        this.completedAt = OffsetDateTime.now();
        changeStatusTo(StopStatus.COMPLETED);
    }

    public void fail() {
        this.completedAt = OffsetDateTime.now();
        changeStatusTo(StopStatus.FAILED);
    }

    public void skip() {
        this.completedAt = OffsetDateTime.now();
        changeStatusTo(StopStatus.SKIPPED);
    }

    private void changeStatusTo(StopStatus newStatus) {
        if (newStatus != null && this.getStatus().canNotTransitionFrom(newStatus)) {
            throw new IllegalArgumentException(
                    "Invalid status transition from " + this.getStatus() +
                            " to " + newStatus);
        }

        if (route.getStatus() != RouteStatus.IN_PROGRESS) {
            throw new IllegalArgumentException(
                    "Invalid status Route transition from " + this.getStatus() +
                            " to " + newStatus);
        }

        this.setStatus(newStatus);
    }
}

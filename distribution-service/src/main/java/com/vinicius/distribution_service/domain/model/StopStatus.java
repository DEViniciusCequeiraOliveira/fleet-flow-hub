package com.vinicius.distribution_service.domain.model;

import java.util.List;

/**
 * StopStatus
 */
public enum StopStatus {
    PENDING,
    ARRIVED(PENDING),
    COMPLETED(ARRIVED),
    FAILED(ARRIVED),
    SKIPPED(ARRIVED);

    private final List<StopStatus> allowedPreviousStatuses;

    StopStatus(StopStatus... allowedPreviousStatuses) {
        this.allowedPreviousStatuses = List.of(allowedPreviousStatuses);
    }

    public boolean canTransitionFrom(StopStatus newStatus) {
        StopStatus currentStatus = this;
        return newStatus.allowedPreviousStatuses.contains(currentStatus);
    }

    public boolean canNotTransitionFrom(StopStatus newStatus) {
        return !canTransitionFrom(newStatus);
    }

    public boolean isFinal() {
        return this == COMPLETED || this == FAILED || this == SKIPPED;
    }
}

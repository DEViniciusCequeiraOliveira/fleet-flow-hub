package com.vinicius.distribution_service.domain.model;

import java.util.List;

/**
 * RouteStatus
 */
public enum RouteStatus {
    PLANNED,
    IN_PROGRESS(PLANNED),
    COMPLETED(IN_PROGRESS),
    CANCELLED(IN_PROGRESS);

    private final List<RouteStatus> allowedPreviousStatuses;

    RouteStatus(RouteStatus... allowedPreviousStatuses) {
        this.allowedPreviousStatuses = List.of(allowedPreviousStatuses);
    }

    public boolean canTransitionFrom(RouteStatus newStatus) {
        RouteStatus currentStatus = this;
        return newStatus.allowedPreviousStatuses.contains(currentStatus);
    }

    public boolean canNotTransitionFrom(RouteStatus newStatus) {
        return !canTransitionFrom(newStatus);
    }
}

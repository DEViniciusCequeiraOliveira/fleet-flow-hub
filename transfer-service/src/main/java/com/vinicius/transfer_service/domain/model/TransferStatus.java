package com.vinicius.transfer_service.domain.model;

import java.util.List;

public enum TransferStatus { 
    PLANNED,    
    IN_TRANSIT(PLANNED),
    ARRIVED(IN_TRANSIT),
    COMPLETED(ARRIVED),
    CANCELLED(PLANNED);
    

    private final List<TransferStatus> allowedPreviousStatuses;

    TransferStatus(TransferStatus... allowedPreviousStatuses) {
        this.allowedPreviousStatuses = List.of(allowedPreviousStatuses);
    }

    public boolean canTransitionFrom(TransferStatus  newStatus) {
        TransferStatus currentStatus = this;
        return !newStatus.allowedPreviousStatuses.contains(currentStatus);
    }

    public boolean canNotTransitionFrom(TransferStatus newStatus) {
        return !canTransitionFrom(newStatus);
    }
}

package com.vinicius.transfer_service.api.dto.request;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import com.vinicius.transfer_service.domain.model.TransferStatus;

import lombok.Data;

/**
 * TransferPreparationRequest
 */
@Data
public class TransferPreparationRequest {

    private String originDistributionCenterId;
    private String destinationDistributionCenterId;
    private UUID vehicleId;
    private UUID driverId;
    private TransferStatus status;
    private OffsetDateTime startedAt;
    private OffsetDateTime estimatedArrivalAt;
    private OffsetDateTime arrivedAt;
    private List<TransferInvoicePreparationRequest> invoices;
}
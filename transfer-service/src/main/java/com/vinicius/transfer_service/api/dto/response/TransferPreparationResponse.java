package com.vinicius.transfer_service.api.dto.response;

import java.time.OffsetDateTime;
import java.util.List;

import org.hibernate.validator.constraints.UUID;

import com.vinicius.transfer_service.domain.model.TransferStatus;

import lombok.Data;

@Data 
public class TransferPreparationResponse {
    private UUID id;
    private String originDistributionCenterId;
    private String destinationDistributionCenterId;
    private UUID vehicleId;
    private UUID driverId;
    private TransferStatus status;
    private OffsetDateTime startedAt;
    private OffsetDateTime estimatedArrivalAt;
    private OffsetDateTime arrivedAt;
    private List<TransferInvoicePreparationResponse> invoices;
}

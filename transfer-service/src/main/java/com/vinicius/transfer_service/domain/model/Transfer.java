package com.vinicius.transfer_service.domain.model;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter(AccessLevel.PRIVATE)
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Transfer {

    @Id
    private UUID id;
    private String originDistributionCenterId;
    private String destinationDistributionCenterId;
    private UUID vehicleId;
    private UUID driverId;
    private TransferStatus status;
    private OffsetDateTime startedAt;
    private OffsetDateTime estimatedArrivalAt;
    private OffsetDateTime arrivedAt;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "transfer")
    private List<TransferInvoice> invoices = new ArrayList<>();

    public static Transfer plan(String originDistributionCenterId, String destinationDistributionCenterId,
            UUID vehicleId, UUID driverId, OffsetDateTime estimatedArrivalAt) {
        Transfer transfer = new Transfer();
        transfer.id = UUID.randomUUID();
        transfer.originDistributionCenterId = originDistributionCenterId;
        transfer.destinationDistributionCenterId = destinationDistributionCenterId;
        transfer.vehicleId = vehicleId;
        transfer.driverId = driverId;
        transfer.status = TransferStatus.PLANNED;
        transfer.estimatedArrivalAt = estimatedArrivalAt;
        return transfer;
    }

    public void start() {
        setStartedAt(OffsetDateTime.now());
        changeStatusTo(TransferStatus.IN_TRANSIT);
    }

    public void arrive() {
        changeStatusTo(TransferStatus.ARRIVED);
        setArrivedAt(OffsetDateTime.now());
    }

    public void complete() {
        changeStatusTo(TransferStatus.COMPLETED);
    }

    public void cancel() {
        changeStatusTo(TransferStatus.CANCELLED);
    }

    public UUID addInvoce(UUID invoiceId) {
        TransferInvoice transferInvoice = TransferInvoice.create(this, invoiceId);
        getInvoices().add(transferInvoice);
        return transferInvoice.getId().getInvoiceId();
    }

    public void removeInvoice(UUID invoiceId) {
        getInvoices().removeIf(invoice -> invoice.getId().getInvoiceId().equals(invoiceId));
    }

    public List<TransferInvoice> getInvoices() {
        return Collections.unmodifiableList(this.invoices);
    }

    private void changeStatusTo(TransferStatus newStatus) {
        if (newStatus != null && this.getStatus().canNotTransitionFrom(newStatus)) {
            throw new IllegalArgumentException(
                    "Invalid status transition from " + this.getStatus() +
                            " to " + newStatus);
        }
        this.setStatus(newStatus);
    }

}

package com.vinicius.transfer_service.domain.model;

import java.util.UUID;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode.Include;

/**
 * TransferInvoiceId
 */
@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TransferInvoiceId {

    @Include
    private UUID transferId;
    @Include
    private UUID invoiceId;

    static TransferInvoiceId of(UUID transferId, UUID invoiceId) {
        TransferInvoiceId transferInvoiceId = new TransferInvoiceId();
        transferInvoiceId.transferId = transferId;
        transferInvoiceId.invoiceId = invoiceId;
        return transferInvoiceId;

    }
}
package com.vinicius.transfer_service.domain.model;

import java.util.UUID;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * TransferInvoiceId
 */
@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TransferInvoiceId {

    @EqualsAndHashCode.Include
    private UUID transferId;
    
    @EqualsAndHashCode.Include
    private String invoiceId;

    static TransferInvoiceId of(UUID transferId, String invoiceId) {
        TransferInvoiceId transferInvoiceId = new TransferInvoiceId();
        transferInvoiceId.transferId = transferId;
        transferInvoiceId.invoiceId = invoiceId;
        return transferInvoiceId;

    }
}
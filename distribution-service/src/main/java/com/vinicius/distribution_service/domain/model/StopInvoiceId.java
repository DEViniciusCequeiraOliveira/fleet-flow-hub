package com.vinicius.distribution_service.domain.model;

import java.util.UUID;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class StopInvoiceId {

    @EqualsAndHashCode.Include
    private UUID stopId;

    @EqualsAndHashCode.Include
    private UUID invoiceId;

    static StopInvoiceId of(UUID stopId, UUID invoiceId) {
        StopInvoiceId stopInvoiceId = new StopInvoiceId();
        stopInvoiceId.stopId = stopId;
        stopInvoiceId.invoiceId = invoiceId;
        return stopInvoiceId;
    }
}

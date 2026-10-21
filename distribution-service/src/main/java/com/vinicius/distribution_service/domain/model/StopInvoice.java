package com.vinicius.distribution_service.domain.model;

import java.util.UUID;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter(AccessLevel.PRIVATE)
public class StopInvoice {
    @EmbeddedId
    @EqualsAndHashCode.Include
    private StopInvoiceId id;

    @MapsId("stopId")
    @ManyToOne(optional = false)
    @Getter(AccessLevel.PRIVATE)
    private Stop stop;

    public static StopInvoice create(Stop stop, UUID invoiceId) {
        StopInvoice stopInvoice = new StopInvoice();
        stopInvoice.id = StopInvoiceId.of(stop.getId(), invoiceId);
        stopInvoice.stop = stop;
        return stopInvoice;
    }
}

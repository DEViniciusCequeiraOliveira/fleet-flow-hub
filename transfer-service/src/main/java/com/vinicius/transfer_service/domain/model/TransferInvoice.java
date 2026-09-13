package com.vinicius.transfer_service.domain.model;

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
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Setter(AccessLevel.PRIVATE)
@Getter
public class TransferInvoice {
    @EmbeddedId
    @EqualsAndHashCode.Include
    private TransferInvoiceId id;
    
    @MapsId("transferId")
    @ManyToOne(optional = false)     
    @Getter(AccessLevel.PRIVATE)
    private Transfer transfer;

    public static TransferInvoice create(Transfer transfer, UUID invoiceId) {
        TransferInvoice transferInvoice = new TransferInvoice();
        transferInvoice.id = TransferInvoiceId.of(transfer.getId(), invoiceId);
        transferInvoice.transfer = transfer;
        return transferInvoice;
    }
}

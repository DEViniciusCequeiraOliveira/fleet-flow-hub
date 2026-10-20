package com.vinicius.shipment_service.domain.model;

import java.math.BigDecimal;
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
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter(AccessLevel.PRIVATE)
public class Invoice {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;
    private String number;
    private String series;
    private String accessKey;
    private String issuerName;
    private String issuerTaxId;
    private String recipientName;
    private String recipientTaxId;
    private String deliveryAddress;
    private BigDecimal totalAmount;
    private OffsetDateTime issuedAt;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "invoice")
    private List<InvoiceItem> items = new ArrayList<>();

    public static Invoice create(String number, String series, String accessKey, String issuerName, String issuerTaxId,
            String recipientName, String recipientTaxId, String deliveryAddress,
            OffsetDateTime issuedAt) {
        Invoice invoice = new Invoice();
        invoice.id = UUID.randomUUID();
        invoice.number = number;
        invoice.series = series;
        invoice.accessKey = accessKey;
        invoice.issuerName = issuerName;
        invoice.issuerTaxId = issuerTaxId;
        invoice.recipientName = recipientName;
        invoice.recipientTaxId = recipientTaxId;
        invoice.deliveryAddress = deliveryAddress;
        invoice.issuedAt = issuedAt;

        return invoice;
    }

    public UUID addItem(String description, Double quantity, BigDecimal unitPrice,
            Double weight, Double height, Double width, Double length) {
        InvoiceItem invoiceItem = InvoiceItem.create(
                this, description, quantity, unitPrice, weight,
                height, width, length);
        this.items.add(invoiceItem);
        calculateTotalAmount();
        return invoiceItem.getId();
    }

    public List<InvoiceItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void removeItems(InvoiceItem item) {
        this.items.removeIf(invoice -> invoice.getId().equals(item.getId()));
        calculateTotalAmount();
    }

    private void calculateTotalAmount() {
        BigDecimal total = items.stream()
                .map(item -> item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, (value, curr) -> value.add(curr));

        setTotalAmount(total);
    }
}

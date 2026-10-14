package com.vinicius.shipment_service.domain.model;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
public class InvoiceItem {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;
    private String description;
    private Double quantity;
    private BigDecimal unitPrice;
    private Double weight;
    private Double height;
    private Double width;
    private Double length;
    @ManyToOne(optional = false)
    private Invoice invoice;

    public static InvoiceItem create(Invoice invoice, String description, Double quantity, BigDecimal unitPrice,
            Double weight, Double height, Double width, Double length) {

        InvoiceItem item = new InvoiceItem();
        item.id = UUID.randomUUID();
        item.invoice = invoice;
        item.description = description;
        item.quantity = quantity;
        item.unitPrice = unitPrice;
        item.weight = weight;
        item.height = height;
        item.width = width;
        item.length = length;

        return item;
    }
}

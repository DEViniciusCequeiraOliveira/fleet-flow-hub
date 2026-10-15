package com.vinicius.shipment_service.api.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Data;

@Data
public class InvoiceItemQueryCompleteResponse {
    private UUID id;
    private String description;
    private Double quantity;
    private BigDecimal unitPrice;
    private Double weight;
    private Double height;
    private Double width;
    private Double length;
}

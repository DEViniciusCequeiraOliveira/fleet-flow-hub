package com.vinicius.shipment_service.api.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * InvoiceItemCrudRequest
 */
@Data
public class InvoiceItemCrudRequest {
    @NotBlank
    private String description;
    @Min(0)
    private Double quantity;
    @Min(0)
    private BigDecimal unitPrice;
    private Double weight;
    private Double height;
    private Double width;
    private Double length;
}
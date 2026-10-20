package com.vinicius.shipment_service.api.dto.request;

import java.time.OffsetDateTime;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class InvoiceCrudRequest {
    @NotBlank
    private String number;
    @NotBlank
    private String series;
    @NotBlank
    private String accessKey;
    @NotBlank
    private String issuerName;
    @NotBlank
    private String issuerTaxId;
    @NotBlank
    private String recipientName;
    @NotBlank
    private String recipientTaxId;
    @NotBlank
    private String deliveryAddress;
    @NotBlank
    private OffsetDateTime issuedAt;
    @NotEmpty
    List<InvoiceItemCrudRequest> items;

}

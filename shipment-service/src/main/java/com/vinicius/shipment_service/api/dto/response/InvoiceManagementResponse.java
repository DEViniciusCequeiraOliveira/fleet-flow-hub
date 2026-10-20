package com.vinicius.shipment_service.api.dto.response;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data 
public class InvoiceManagementResponse {
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
    private List<InvoiceItemManagementResponse> items;
}

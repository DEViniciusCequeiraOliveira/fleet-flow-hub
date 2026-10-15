package com.vinicius.shipment_service.api.controller;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinicius.shipment_service.api.dto.response.InvoiceQueryCompleteResponse;
import com.vinicius.shipment_service.api.dto.response.InvoiceQueryResumeResponse;
import com.vinicius.shipment_service.domain.service.Invoice.InvoiceManagementService;
import com.vinicius.shipment_service.domain.service.Invoice.InvoiceQueryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/invoices")
@RequiredArgsConstructor
public class InvoiceController {

    final private InvoiceQueryService invoiceQueryService;
    final private InvoiceManagementService invoiceManagementService;

    @GetMapping
    public Page<InvoiceQueryResumeResponse> getInvoices(@PageableDefault Pageable pageable) {
        return invoiceQueryService.getInvoices(pageable);
    }

    @GetMapping("/{invoiceId}")
    public InvoiceQueryCompleteResponse getInvoiceById(@PathVariable UUID invoiceId) {
        return invoiceQueryService.getInvoiceById(invoiceId);
    }
}

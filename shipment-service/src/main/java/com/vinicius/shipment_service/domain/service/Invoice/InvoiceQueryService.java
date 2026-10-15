package com.vinicius.shipment_service.domain.service.Invoice;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vinicius.shipment_service.api.dto.response.InvoiceQueryCompleteResponse;
import com.vinicius.shipment_service.api.dto.response.InvoiceQueryResumeResponse;
import com.vinicius.shipment_service.application.utility.Mapper;
import com.vinicius.shipment_service.domain.model.Invoice;
import com.vinicius.shipment_service.domain.repository.InvoiceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InvoiceQueryService {

    private final Mapper mapper;
    private final InvoiceRepository invoiceRepository;

    public Page<InvoiceQueryResumeResponse> getInvoices(Pageable pageable) {
        Page<Invoice> invoicePage = invoiceRepository.findAll(pageable);
        return invoicePage.map(this::mapToInvoiceResponse);
    }

    public InvoiceQueryCompleteResponse getInvoiceById(UUID invoiceId) {
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));
        return mapToInvoiceCompleteResponse(invoice);
    }

    private InvoiceQueryResumeResponse mapToInvoiceResponse(Invoice invoice) {
        return mapper.convert(invoice, InvoiceQueryResumeResponse.class);
    }

    private InvoiceQueryCompleteResponse mapToInvoiceCompleteResponse(Invoice invoice) {
        return mapper.convert(invoice, InvoiceQueryCompleteResponse.class);
    }

}

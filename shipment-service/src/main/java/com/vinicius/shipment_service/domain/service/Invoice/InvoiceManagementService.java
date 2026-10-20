package com.vinicius.shipment_service.domain.service.Invoice;

import org.springframework.stereotype.Service;

import com.vinicius.shipment_service.api.dto.request.InvoiceCrudRequest;
import com.vinicius.shipment_service.api.dto.response.InvoiceManagementResponse;
import com.vinicius.shipment_service.application.utility.Mapper;
import com.vinicius.shipment_service.domain.model.Invoice;
import com.vinicius.shipment_service.domain.repository.InvoiceRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InvoiceManagementService {

    private final Mapper mapper;
    private final InvoiceRepository invoiceRepository;

    @Transactional
    public InvoiceManagementResponse createInvoice(InvoiceCrudRequest invoiceCrudRequest) {
        Invoice invoice = Invoice.create(invoiceCrudRequest.getNumber(),
                invoiceCrudRequest.getSeries(),
                invoiceCrudRequest.getAccessKey(),
                invoiceCrudRequest.getIssuerName(),
                invoiceCrudRequest.getIssuerTaxId(),
                invoiceCrudRequest.getRecipientName(),
                invoiceCrudRequest.getRecipientTaxId(),
                invoiceCrudRequest.getDeliveryAddress(),
                invoiceCrudRequest.getIssuedAt());

        invoiceCrudRequest.getItems()
                .forEach(item -> invoice.addItem(
                        item.getDescription(),
                        item.getQuantity(),
                        item.getUnitPrice(),
                        item.getWeight(),
                        item.getHeight(),
                        item.getWidth(),
                        item.getLength()));

        Invoice invoiceSaved = invoiceRepository.save(invoice);

        return mapper.convert(invoiceSaved, InvoiceManagementResponse.class);
    }

}

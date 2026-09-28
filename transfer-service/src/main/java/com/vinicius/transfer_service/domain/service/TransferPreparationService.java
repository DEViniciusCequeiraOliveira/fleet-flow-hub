package com.vinicius.transfer_service.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vinicius.transfer_service.api.dto.request.TransferPreparationRequest;
import com.vinicius.transfer_service.api.dto.response.TransferPreparationResponse;
import com.vinicius.transfer_service.application.utility.Mapper;
import com.vinicius.transfer_service.domain.model.Transfer;
import com.vinicius.transfer_service.domain.repository.TransferRepository;
import com.vinicius.transfer_service.infrastructure.utility.http.client.DriverAPIClient;
import com.vinicius.transfer_service.infrastructure.utility.http.client.model.DriverPayoutResultModel;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransferPreparationService {

    final private TransferRepository transferRepository;
    final private DriverAPIClient driverAPIClient;
    final private Mapper mapper;

    @Transactional
    public TransferPreparationResponse prepareTransfer(TransferPreparationRequest request) {
        DriverPayoutResultModel driver = driverAPIClient.getDriverById(request.getDriverId());

        Transfer transfer = Transfer.plan(
                request.getOriginDistributionCenterId(),
                request.getDestinationDistributionCenterId(),
                driver.getId(),
                request.getDriverId());

        request.getInvoices().forEach(invoiceRequest -> {
            transfer.addInvoce(invoiceRequest.getInvoice());
        });

        Transfer transferSaved = transferRepository.save(transfer);
        return mapToResponse(transferSaved);
    }

    private TransferPreparationResponse mapToResponse(Transfer transfer) {
        return mapper.convert(transfer, TransferPreparationResponse.class);
    }
}

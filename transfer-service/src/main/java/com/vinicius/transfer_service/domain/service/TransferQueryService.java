package com.vinicius.transfer_service.domain.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vinicius.transfer_service.api.dto.response.TransferPreparationResponse;
import com.vinicius.transfer_service.application.utility.Mapper;
import com.vinicius.transfer_service.domain.model.Transfer;
import com.vinicius.transfer_service.domain.repository.TransferRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransferQueryService {

    private final TransferRepository transferRepository;
    private final Mapper mapper;

    public Page<TransferPreparationResponse> getTransfers(Pageable pageable) {
        Page<Transfer> transferPage = transferRepository.findAll(pageable);
        return transferPage.map(this::mapToTransferPreparationResponse);
    }

    public TransferPreparationResponse getTransfer(UUID transferId) {
        Transfer transfer = transferRepository.findById(transferId)
                .orElseThrow(() -> new IllegalArgumentException("Transfer not found"));
        return mapToTransferPreparationResponse(transfer);
    }

    private TransferPreparationResponse mapToTransferPreparationResponse(Transfer transfer) {
        return mapper.convert(transfer, TransferPreparationResponse.class);
    }

}

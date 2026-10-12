package com.vinicius.transfer_service.domain.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vinicius.transfer_service.api.dto.response.TransferQueryCompleteResponse;
import com.vinicius.transfer_service.api.dto.response.TransferQueryResumeResponse;
import com.vinicius.transfer_service.application.utility.Mapper;
import com.vinicius.transfer_service.domain.model.Transfer;
import com.vinicius.transfer_service.domain.repository.TransferRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransferQueryService {

    private final TransferRepository transferRepository;
    private final Mapper mapper;

    public Page<TransferQueryResumeResponse> getTransfers(Pageable pageable) {
        Page<Transfer> transferPage = transferRepository.findAll(pageable);
        return transferPage.map(this::mapToTransferQueryResumeResponse);
    }

    public TransferQueryCompleteResponse getTransfer(UUID transferId) {
        Transfer transfer = transferRepository.findById(transferId)
                .orElseThrow(() -> new IllegalArgumentException("Transfer not found"));
        return mapToTransferQueryCompleteResponse(transfer);
    }

    private TransferQueryResumeResponse mapToTransferQueryResumeResponse(Transfer transfer) {
        return mapper.convert(transfer, TransferQueryResumeResponse.class);
    }

    private TransferQueryCompleteResponse mapToTransferQueryCompleteResponse(Transfer transfer) {
        return mapper.convert(transfer, TransferQueryCompleteResponse.class);
    }

}

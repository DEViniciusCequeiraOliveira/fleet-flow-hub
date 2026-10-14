package com.vinicius.transfer_service.domain.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vinicius.transfer_service.api.dto.response.TransferCheckpointResponse;
import com.vinicius.transfer_service.application.utility.Mapper;
import com.vinicius.transfer_service.domain.model.Transfer;
import com.vinicius.transfer_service.domain.repository.TransferRepository;

import lombok.RequiredArgsConstructor;

/**
 * TransferCheckpointService
 */
@Service
@RequiredArgsConstructor
public class TransferCheckpointService {

    final private Mapper mapper;
    final private TransferRepository transferRepository;

    @Transactional
    public TransferCheckpointResponse startTransfer(UUID transferId) {
        Transfer transfer = transferRepository.findById(transferId)
                .orElseThrow(() -> new IllegalArgumentException("Transfer not found"));

        transfer.start();

        Transfer transferSaved = transferRepository.save(transfer);
        return mapper.convert(transferSaved, TransferCheckpointResponse.class);
    }

    @Transactional
    public TransferCheckpointResponse arriveTransfer(UUID transferId) {
        Transfer transfer = transferRepository.findById(transferId)
                .orElseThrow(() -> new IllegalArgumentException("Transfer not found"));

        transfer.arrive();

        Transfer transferSaved = transferRepository.save(transfer);
        return mapper.convert(transferSaved, TransferCheckpointResponse.class);
    }

    @Transactional
    public TransferCheckpointResponse completeTransfer(UUID transferId) {
        Transfer transfer = transferRepository.findById(transferId)
                .orElseThrow(() -> new IllegalArgumentException("Transfer not found"));

        transfer.complete();

        Transfer transferSaved = transferRepository.save(transfer);
        return mapper.convert(transferSaved, TransferCheckpointResponse.class);
    }

    @Transactional
    public TransferCheckpointResponse cancelTransfer(UUID transferId) {
        Transfer transfer = transferRepository.findById(transferId)
                .orElseThrow(() -> new IllegalArgumentException("Transfer not found"));

        transfer.cancel();

        Transfer transferSaved = transferRepository.save(transfer);
        return mapper.convert(transferSaved, TransferCheckpointResponse.class);
    }

}

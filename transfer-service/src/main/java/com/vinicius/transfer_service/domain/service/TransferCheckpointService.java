package com.vinicius.transfer_service.domain.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vinicius.transfer_service.domain.model.Transfer;
import com.vinicius.transfer_service.domain.repository.TransferRepository;

import lombok.RequiredArgsConstructor;

/**
 * TransferCheckpointService
 */
@Service
@RequiredArgsConstructor
public class TransferCheckpointService {

    final private TransferRepository transferRepository;

    @Transactional
    public void startTransfer(UUID transferId) {
        Transfer transfer = transferRepository.findById(transferId)
                .orElseThrow(() -> new IllegalArgumentException("Transfer not found"));

        transfer.start();

        transferRepository.save(transfer);
    }

    @Transactional
    public void arriveTransfer(UUID transferId) {
        Transfer transfer = transferRepository.findById(transferId)
                .orElseThrow(() -> new IllegalArgumentException("Transfer not found"));

        transfer.arrive();

        transferRepository.save(transfer);
    }

    @Transactional
    public void completeTransfer(UUID transferId) {
        Transfer transfer = transferRepository.findById(transferId)
                .orElseThrow(() -> new IllegalArgumentException("Transfer not found"));

        transfer.complete();

        transferRepository.save(transfer);
    }

    @Transactional
    public void cancelTransfer(UUID transferId) {
        Transfer transfer = transferRepository.findById(transferId)
                .orElseThrow(() -> new IllegalArgumentException("Transfer not found"));

        transfer.cancel();

        transferRepository.save(transfer);
    }

}
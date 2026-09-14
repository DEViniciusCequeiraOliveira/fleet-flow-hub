package com.vinicius.transfer_service.api.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinicius.transfer_service.api.dto.request.TransferPreparationRequest;
import com.vinicius.transfer_service.api.dto.response.TransferPreparationResponse;
import com.vinicius.transfer_service.domain.service.TransferCheckpointService;
import com.vinicius.transfer_service.domain.service.TransferPreparationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/transfers")
@RequiredArgsConstructor
public class TransferController {

    final private TransferPreparationService transferPreparationService;
    final private TransferCheckpointService transferCheckpointService;

    @PostMapping()
    public TransferPreparationResponse planTransfer(@RequestBody TransferPreparationRequest request) {
        return transferPreparationService.prepareTransfer(request);
    }

    @PostMapping("/start/{transferId}")
    public void startTransfer(@PathVariable UUID transferId) {
        transferCheckpointService.startTransfer(transferId);
    }

    @PostMapping("/arrive/{transferId}")
    public void arriveTransfer(@PathVariable UUID transferId) {
        transferCheckpointService.arriveTransfer(transferId);
    }

    @PostMapping("/complete/{transferId}")
    public void completeTransfer(@PathVariable UUID transferId) {
        transferCheckpointService.completeTransfer(transferId);
    }

    @PostMapping("/cancel/{transferId}")
    public void cancelTransfer(@PathVariable UUID transferId) {
        transferCheckpointService.cancelTransfer(transferId);
    }

}

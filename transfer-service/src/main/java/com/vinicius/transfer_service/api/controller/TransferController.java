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
    public TransferPreparationResponse startTransfer(@PathVariable UUID transferId) {
        return transferCheckpointService.startTransfer(transferId);
    }

    @PostMapping("/arrive/{transferId}")
    public TransferPreparationResponse arriveTransfer(@PathVariable UUID transferId) {
        return transferCheckpointService.arriveTransfer(transferId);
    }

    @PostMapping("/complete/{transferId}")
    public TransferPreparationResponse completeTransfer(@PathVariable UUID transferId) {
        return transferCheckpointService.completeTransfer(transferId);
    }

    @PostMapping("/cancel/{transferId}")
    public TransferPreparationResponse cancelTransfer(@PathVariable UUID transferId) {
        return transferCheckpointService.cancelTransfer(transferId);
    }

}

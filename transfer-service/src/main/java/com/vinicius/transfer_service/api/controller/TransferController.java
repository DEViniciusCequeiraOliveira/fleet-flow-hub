package com.vinicius.transfer_service.api.controller;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinicius.transfer_service.api.dto.request.TransferPreparationRequest;
import com.vinicius.transfer_service.api.dto.response.TransferPreparationResponse;
import com.vinicius.transfer_service.api.dto.response.TransferQueryCompleteResponse;
import com.vinicius.transfer_service.api.dto.response.TransferQueryResumeResponse;
import com.vinicius.transfer_service.domain.service.TransferCheckpointService;
import com.vinicius.transfer_service.domain.service.TransferPreparationService;
import com.vinicius.transfer_service.domain.service.TransferQueryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/transfers")
@RequiredArgsConstructor
public class TransferController {

    final private TransferPreparationService transferPreparationService;
    final private TransferCheckpointService transferCheckpointService;
    final private TransferQueryService transferQueryService;

    @GetMapping
    public Page<TransferQueryResumeResponse> getTransfers(@PageableDefault Pageable pageable) {
        return transferQueryService.getTransfers(pageable);
    }

    @GetMapping("/{transferId}")
    public TransferQueryCompleteResponse getTransfer(@PathVariable UUID transferId) {        
        return transferQueryService.getTransfer(transferId);
    }

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

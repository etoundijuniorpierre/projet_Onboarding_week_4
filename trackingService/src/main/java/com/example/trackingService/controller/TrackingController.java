package com.example.trackingService.controller;


import com.example.trackingService.dto.TrackPackageRequest;
import com.example.trackingService.command.TrackPackageCommand;
import com.example.trackingService.query.TrackingRecord;
import com.example.trackingService.repository.TrackingRecordRepository;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/api/tracking")
public class TrackingController {

    private final CommandGateway commandGateway;
    private final TrackingRecordRepository trackingRecordRepository;

    public TrackingController(CommandGateway commandGateway, TrackingRecordRepository trackingRecordRepository) {
        this.commandGateway = commandGateway;
        this.trackingRecordRepository = trackingRecordRepository;
    }

    @PostMapping
    public ResponseEntity<String> trackPackage(@RequestBody TrackPackageRequest request) {
        String trackingId = UUID.randomUUID().toString();
        TrackPackageCommand command = new TrackPackageCommand(
                trackingId,
                request.getPackageId(),
                request.getCheckpointId(),
                LocalDateTime.now()
        );
        commandGateway.sendAndWait(command);
        return ResponseEntity.ok(trackingId);
    }

    @GetMapping("/{trackingId}")
    public ResponseEntity<TrackingRecord> getTrackingRecord(@PathVariable Long trackingId) {
        return trackingRecordRepository.findById(trackingId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}



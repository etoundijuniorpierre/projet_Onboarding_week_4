package com.example.trackingService.controller;

import com.example.trackingservice.command.TrackPackageCommand;
import com.example.trackingService.query.TrackingRecord;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tracking")
public class TrackingController {

    private final CommandGateway commandGateway;
    private final com.example.trackingservice.repository.TrackingRecordRepository trackingRecordRepository;

    public TrackingController(CommandGateway commandGateway, com.example.trackingservice.repository.TrackingRecordRepository trackingRecordRepository) {
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
    public ResponseEntity<TrackingRecord> getTrackingRecord(@PathVariable String trackingId) {
        return trackingRecordRepository.findById(trackingId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

class TrackPackageRequest {
    private Long packageId;
    private Long checkpointId;

    public Long getPackageId() {
        return packageId;
    }

    public void setPackageId(Long packageId) {
        this.packageId = packageId;
    }

    public Long getCheckpointId() {
        return checkpointId;
    }

    public void setCheckpointId(Long checkpointId) {
        this.checkpointId = checkpointId;
    }
}

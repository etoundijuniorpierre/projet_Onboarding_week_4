package com.example.trackingService.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDateTime;

public class TrackPackageCommand {

    @TargetAggregateIdentifier
    private final String trackingId;
    private final Long packageId;
    private final Long checkpointId;
    private final LocalDateTime timestamp;

    public TrackPackageCommand(String trackingId, Long packageId, Long checkpointId, LocalDateTime timestamp) {
        this.trackingId = trackingId;
        this.packageId = packageId;
        this.checkpointId = checkpointId;
        this.timestamp = timestamp;
    }

    public String getTrackingId() {
        return trackingId;
    }

    public Long getPackageId() {
        return packageId;
    }

    public Long getCheckpointId() {
        return checkpointId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}

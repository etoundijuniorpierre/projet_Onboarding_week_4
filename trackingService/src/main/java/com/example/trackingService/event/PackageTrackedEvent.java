package com.example.trackingService.event;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PackageTrackedEvent {

    private final String trackingId;
    private final Long packageId;
    private final Long checkpointId;
    private final LocalDateTime timestamp;

    public PackageTrackedEvent(String trackingId, Long packageId, Long checkpointId, LocalDateTime timestamp) {
        this.trackingId = trackingId;
        this.packageId = packageId;
        this.checkpointId = checkpointId;
        this.timestamp = timestamp;
    }

}

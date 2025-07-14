package com.example.trackingService.event;

import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;


@Data
public class PackageTrackedEvent {
    private final String trackingId;
    private final Long packageId;
    private final Long checkpointId;
    private final LocalDateTime timestamp;
}

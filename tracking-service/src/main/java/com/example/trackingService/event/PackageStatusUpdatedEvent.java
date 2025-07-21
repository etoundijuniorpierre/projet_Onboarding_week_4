package com.example.trackingService.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PackageStatusUpdatedEvent {
    private String packageId;
    private String oldStatus;
    private String newStatus;
    private Long timestamp;
}

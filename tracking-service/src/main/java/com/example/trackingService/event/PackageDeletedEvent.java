package com.example.trackingService.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PackageDeletedEvent {
    private String packageId;
    private Long timestamp;
}
package com.example.trackingService.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckpointPassedEvent {
    private String logId;
    private String packageId;
    private String locationId;
    private LocalDateTime timestamp;
}

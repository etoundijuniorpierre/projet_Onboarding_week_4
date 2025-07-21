package com.example.trackingService.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckpointDetail {
    private String locationId;
    private String city;
    private String zone;
    private boolean checkpointAvailable;
    private LocalDateTime passDateTime;
}

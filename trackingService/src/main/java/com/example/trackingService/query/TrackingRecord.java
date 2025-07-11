package com.example.trackingService.query;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class TrackingRecord {

    @Id
    private Long trackingId;
    private Long packageId;
    private Long checkpointId;
    private LocalDateTime lastTracked;
}

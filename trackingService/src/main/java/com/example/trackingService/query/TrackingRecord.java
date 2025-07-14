package com.example.trackingService.query;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class TrackingRecord {
    @Id
    private String trackingId;

    @Column
    private Long packageId;

    @Column
    private Long checkpointId;

    @Column
    private LocalDateTime lastTracked;
}

package com.example.trackingService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tracking_records")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrackingRecord {

    @Id
    private String packageId;
    private String description;
    private Integer weight;
    public boolean fragile;
    private String currentStatus;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "checkpoint_history", joinColumns = @JoinColumn(name = "package_id"))
    @OrderColumn(name = "sequence_idx")
    private List<CheckpointDetail> checkpointHistory = new ArrayList<>();

    private LocalDateTime lastUpdated;
}

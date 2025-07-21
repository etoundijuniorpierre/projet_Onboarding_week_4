package com.example.trackingService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrackingResponseDto {
    private String packageId;
    private String description;
    private Integer weight;
    private boolean fragile;
    private String currentStatus;
    private List<CheckpointDetailDto> checkpointHistory;
    private LocalDateTime lastUpdated;
}

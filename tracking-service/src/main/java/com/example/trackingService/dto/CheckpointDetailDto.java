package com.example.trackingService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckpointDetailDto {
    private String locationId;
    private String city;
    private String zone;
    private boolean checkpointAvailable;
    private LocalDateTime passDateTime;
}

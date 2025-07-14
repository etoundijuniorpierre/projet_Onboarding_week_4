package com.example.trackingService.dto;

import lombok.Data;

@Data
public class TrackPackageRequest {
    private Long packageId;
    private Long checkpointId;
}

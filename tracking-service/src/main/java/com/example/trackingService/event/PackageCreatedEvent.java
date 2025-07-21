package com.example.trackingService.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PackageCreatedEvent {
    private String packageId;
    private String description;
    private Integer weight;
    private String status;
    private Long timestamp;

    public boolean isFragile() {
        return false;
    }
}

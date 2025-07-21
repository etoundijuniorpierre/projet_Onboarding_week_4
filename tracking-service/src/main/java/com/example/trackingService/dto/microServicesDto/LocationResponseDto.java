package com.example.trackingService.dto.microServicesDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationResponseDto {
    private String id;
    private String city;
    private String zone;
    private boolean checkpointAvailable;
}

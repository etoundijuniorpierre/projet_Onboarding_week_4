package com.location_service.dto;


import lombok.Data;

@Data

public class LocationRequestDto {
    private String id;
    private String city;
    private String zone;
    private String packageId;
    private boolean checkpointAvailable;
}

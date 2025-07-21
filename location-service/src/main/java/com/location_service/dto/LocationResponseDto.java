package com.location_service.dto;

import com.location_service.dto.microServiceDto.PackageResponseDto;
import lombok.Data;


@Data
public class LocationResponseDto {
    private String id;
    private String city;
    private String zone;
    private PackageResponseDto packages;
    private boolean checkpointAvailable;
}

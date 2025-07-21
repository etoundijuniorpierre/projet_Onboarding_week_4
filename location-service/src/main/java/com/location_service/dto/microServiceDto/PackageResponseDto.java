package com.location_service.dto.microServiceDto;

import lombok.Data;

@Data
public class PackageResponseDto {
    private Long id;
    private String description;
    private Integer weight;
    private boolean fragile;
    private String status;
}

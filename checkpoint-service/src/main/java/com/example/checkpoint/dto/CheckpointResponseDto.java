package com.example.checkpoint.dto;


import com.example.checkpoint.dto.microServicesDto.LocationResponseDto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CheckpointResponseDto {
    private Long id;
    private Long packageId;
    private LocationResponseDto locationDto;
    private LocalDateTime passDateTime;
}

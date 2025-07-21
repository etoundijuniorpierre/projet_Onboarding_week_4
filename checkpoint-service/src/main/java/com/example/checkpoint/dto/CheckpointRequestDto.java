package com.example.checkpoint.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CheckpointRequestDto {
    @NotNull
    private Long packageId;

    @NotNull
    private Long locationId;
}


package com.logisticsCompany.dto;

import com.logisticsCompany.entities.enums.Status;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Data
public class PackageRequestDto {
    @NotBlank
    String description;

    @NotNull
    Integer weight;

    public Boolean fragile;

    public Status status;
}

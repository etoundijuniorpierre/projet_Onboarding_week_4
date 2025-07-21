package com.logisticsCompany.event;

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
    public boolean fragile;
    private String status;
    private Long timestamp;


}


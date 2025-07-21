package com.logisticsCompany.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PackageDeletedEvent {
    private String packageId;
    private Long timestamp;
}


package com.logisticsCompany.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PackageStatusUpdatedEvent {
    private String packageId;
    private String oldStatus;
    private String newStatus;
    private Long timestamp;
}

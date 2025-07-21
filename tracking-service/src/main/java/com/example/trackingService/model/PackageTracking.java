package com.example.trackingService.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "package_tracking")
@Data
@NoArgsConstructor
public class PackageTracking {
    @Id
    private String packageId;
    private String currentStatus;
    private String lastLocationId;

    @Column(columnDefinition = "jsonb")
    private String trackingHistory;
}

package com.example.trackingService.command;

import lombok.Data;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDateTime;

@Data
public class TrackPackageCommand {

    @TargetAggregateIdentifier
    private final String trackingId;
    private final Long packageId;
    private final Long checkpointId;
    private final LocalDateTime timestamp;

}

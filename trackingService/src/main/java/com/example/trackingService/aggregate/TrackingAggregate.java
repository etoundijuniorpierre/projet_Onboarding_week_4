package com.example.trackingService.aggregate;

import com.example.trackingservice.command.TrackPackageCommand;
import com.example.trackingservice.event.PackageTrackedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class TrackingAggregate {

    @AggregateIdentifier
    private String trackingId;

    private Long packageId;
    private Long checkpointId;
    private LocalDateTime lastTracked;

    public TrackingAggregate() {
        // Required by Axon
    }

    @CommandHandler
    public TrackingAggregate(TrackPackageCommand command) {
        apply(new PackageTrackedEvent(
                command.getTrackingId(),
                command.getPackageId(),
                command.getCheckpointId(),
                command.getTimestamp()));
    }

    @EventSourcingHandler
    public void on(PackageTrackedEvent event) {
        this.trackingId = event.getTrackingId();
        this.packageId = event.getPackageId();
        this.checkpointId = event.getCheckpointId();
        this.lastTracked = event.getTimestamp();
    }
}

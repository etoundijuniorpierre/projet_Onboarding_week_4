package com.example.trackingService.handler;

import com.example.trackingService.event.PackageTrackedEvent;
import com.example.trackingService.query.TrackingRecord;
import com.example.trackingService.repository.TrackingRecordRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public class TrackingEventHandler {

    private final TrackingRecordRepository repository;

    public TrackingEventHandler(TrackingRecordRepository repository) {
        this.repository = repository;
    }

    @EventHandler
    public void on(PackageTrackedEvent event) {
        TrackingRecord record = new TrackingRecord();
        record.setTrackingId(event.getTrackingId());
        record.setPackageId(event.getPackageId());
        record.setCheckpointId(event.getCheckpointId());
        record.setLastTracked(event.getTimestamp());
        repository.save(record);
    }
}

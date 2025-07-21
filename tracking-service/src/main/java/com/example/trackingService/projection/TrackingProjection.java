package com.example.trackingService.projection;

import com.example.trackingService.dto.microServicesDto.LocationResponseDto;
import com.example.trackingService.entity.CheckpointDetail;
import com.example.trackingService.entity.TrackingRecord;
import com.example.trackingService.event.CheckpointPassedEvent;
import com.example.trackingService.event.PackageCreatedEvent;
import com.example.trackingService.event.PackageDeletedEvent;
import com.example.trackingService.event.PackageStatusUpdatedEvent;
import com.example.trackingService.feign.LocationClient;
import com.example.trackingService.repository.TrackingRecordRepository;
import feign.FeignException;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

@Component
public class TrackingProjection {

    private final TrackingRecordRepository trackingRecordRepository;
    private final LocationClient locationClient; // Optional, only if fetching location name

    public TrackingProjection(TrackingRecordRepository trackingRecordRepository, LocationClient locationClient) {
        this.trackingRecordRepository = trackingRecordRepository;
        this.locationClient = locationClient;
    }

    @EventHandler
    public void on(PackageCreatedEvent event) {
        TrackingRecord trackingRecord = new TrackingRecord();
        trackingRecord.setPackageId(event.getPackageId());
        trackingRecord.setDescription(event.getDescription());
        trackingRecord.setWeight(event.getWeight());
        trackingRecord.setFragile(event.isFragile());
        trackingRecord.setCurrentStatus(event.getStatus());
        trackingRecord.setLastUpdated(LocalDateTime.ofInstant(Instant.ofEpochMilli(event.getTimestamp()), ZoneOffset.UTC));

        trackingRecordRepository.save(trackingRecord);
        System.out.println("TrackingProjection: Created tracking record for package ID: " + event.getPackageId());
    }

    @EventHandler
    public void on(PackageStatusUpdatedEvent event) {
        Optional<TrackingRecord> optionalRecord = trackingRecordRepository.findById(event.getPackageId());
        if (optionalRecord.isPresent()) {
            TrackingRecord trackingRecord = optionalRecord.get();
            trackingRecord.setCurrentStatus(event.getNewStatus());
            trackingRecord.setLastUpdated(LocalDateTime.ofInstant(Instant.ofEpochMilli(event.getTimestamp()), ZoneOffset.UTC));
            trackingRecordRepository.save(trackingRecord);
            System.out.println("TrackingProjection: Updated status for package ID: " + event.getPackageId() + " to " + event.getNewStatus());
        } else {
            System.err.println("TrackingProjection: Tracking record not found for package ID: " + event.getPackageId() + " during status update.");
        }
    }

    @EventHandler
    public void on(CheckpointPassedEvent event) {
        Optional<TrackingRecord> optionalRecord = trackingRecordRepository.findById(event.getPackageId());
        if (optionalRecord.isPresent()) {
            TrackingRecord trackingRecord = optionalRecord.get();

            String city = "Unknown city";
            String zone = "Unknown Zone";
            boolean checkpointAvailable = false;
            try {
                LocationResponseDto locationDto = locationClient.getLocationById(event.getLocationId()).getBody();
                if (locationDto != null) {
                    city = locationDto.getCity();
                    zone = locationDto.getZone();
                    checkpointAvailable = locationDto.isCheckpointAvailable();
                }
            } catch (FeignException e) {
                System.err.println("TrackingProjection: Could not fetch location name for ID: " + event.getLocationId() + " - " + e.getMessage());
            } catch (Exception e) {
                System.err.println("TrackingProjection: Unexpected error fetching location name for ID: " + event.getLocationId() + " - " + e.getMessage());
            }

            CheckpointDetail checkpointDetail = new CheckpointDetail(
                    event.getLocationId(),
                    city,
                    zone,
                    checkpointAvailable,
                    event.getTimestamp()
            );
            trackingRecord.getCheckpointHistory().add(checkpointDetail);
            trackingRecord.setLastUpdated(event.getTimestamp()); // Use the checkpoint event's timestamp

            trackingRecordRepository.save(trackingRecord);
            System.out.println("TrackingProjection: Added checkpoint for package ID: " + event.getPackageId() + " at location " + event.getLocationId());
        } else {
            System.err.println("TrackingProjection: Tracking record not found for package ID: " + event.getPackageId() + " during checkpoint event.");
        }
    }

    @EventHandler
    public void on(PackageDeletedEvent event) {
        trackingRecordRepository.deleteById(event.getPackageId());
        System.out.println("TrackingProjection: Deleted tracking record for package ID: " + event.getPackageId());
    }
}
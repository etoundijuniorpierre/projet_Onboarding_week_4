package com.example.trackingService.controller;

import com.example.trackingService.dto.TrackingResponseDto;
import com.example.trackingService.entity.TrackingRecord;
import com.example.trackingService.mapper.TrackingMapper;
import com.example.trackingService.repository.TrackingRecordRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/tracking")
public class TrackingController {

    private final TrackingRecordRepository trackingRecordRepository;
    private final TrackingMapper trackingMapper;

    public TrackingController(TrackingRecordRepository trackingRecordRepository, TrackingMapper trackingMapper) {
        this.trackingRecordRepository = trackingRecordRepository;
        this.trackingMapper = trackingMapper;
    }


    @GetMapping("/{packageId}")
    public ResponseEntity<TrackingResponseDto> getTrackingRecordByPackageId(@PathVariable String packageId) {
        TrackingRecord trackingRecord = trackingRecordRepository.findById(packageId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tracking record for package ID " + packageId + " not found."));
        TrackingResponseDto responseDto = trackingMapper.toDto(trackingRecord);

        return ResponseEntity.ok(responseDto);
    }


    @GetMapping("/all")
    public ResponseEntity<List<TrackingResponseDto>> getAllTrackingRecords() {
        // Retrieve all tracking records from the Read Model database
        List<TrackingRecord> trackingRecords = trackingRecordRepository.findAll();
        List<TrackingResponseDto> responseDtos = trackingMapper.toDtoList(trackingRecords);
        return ResponseEntity.ok(responseDtos);
    }
}
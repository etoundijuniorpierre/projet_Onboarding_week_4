package com.location_service.controller;

import com.location_service.dto.LocationResponseDto;
import com.location_service.dto.LocationRequestDto;
import com.location_service.entity.LocationEntity;
import com.location_service.mapper.LocationMapper;
import com.location_service.service.LocationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/location")
public class LocationController {

    private final LocationService locationService;
    private final LocationMapper locationMapper;

    public LocationController(LocationService locationService, LocationMapper locationMapper) {
        this.locationService = locationService;
        this.locationMapper = locationMapper;
    }

    @PostMapping
    public ResponseEntity<LocationResponseDto> addLocation(@RequestBody LocationRequestDto locationRequestDto) {
        LocationEntity createdLocation = locationService.addLocation(locationMapper.toEntity(locationRequestDto));
        LocationResponseDto responseDto = locationMapper.toDto(createdLocation);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<LocationResponseDto>> getAllLocations() {
        List<LocationEntity> allLocations = locationService.getAllLocations();
        return ResponseEntity.ok(locationMapper.toDtoList(allLocations));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocationResponseDto> getLocationById(@PathVariable String id) {
        LocationEntity searchLocation = locationService.getLocationById(id);
        return ResponseEntity.ok(locationMapper.toDto(searchLocation));
    }
}

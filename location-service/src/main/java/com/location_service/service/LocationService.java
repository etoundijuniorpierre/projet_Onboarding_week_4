package com.location_service.service;

import com.location_service.entity.LocationEntity;
import com.location_service.repository.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LocationService {

    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public LocationEntity addLocation(LocationEntity locationEntity) {
        if (locationEntity.getId() != null && locationRepository.existsById(locationEntity.getId())) {
            throw new IllegalArgumentException("location with ID '" + locationEntity.getId() + "' exist.");
        }
        return locationRepository.save(locationEntity);
    }

    public List<LocationEntity> getAllLocations() {
        return locationRepository.findAll();
    }

    public LocationEntity getLocationById(String id) {
        return locationRepository.findById(id).orElseThrow(() -> new RuntimeException("No location found with id: " + id));
    }
}

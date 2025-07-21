package com.location_service.service;

import com.location_service.entity.LocationEntity;
import com.location_service.repository.LocationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class LocationServiceTest {

    @Mock
    LocationRepository locationRepository;

    @InjectMocks
    LocationService locationService;


    @Test
    void addLocation() {
        LocationEntity locationEntity = new LocationEntity();

        locationEntity.setId("1");
        locationEntity.setCity("Yaounde");
        locationEntity.setZone("mimboman");
        locationEntity.setCheckpointAvailable(true);

        when(locationRepository.save(locationEntity)).thenReturn(locationEntity);
        LocationEntity result = locationService.addLocation(locationEntity);

        assertEquals(locationEntity, result);
        verify(locationRepository).save(locationEntity);

    }

    @Test
    void getAllLocations() {
        LocationEntity locationEntity1 = new LocationEntity();

        locationEntity1.setId("1");
        locationEntity1.setCity("Yaounde");
        locationEntity1.setZone("mimboman");
        locationEntity1.setCheckpointAvailable(true);

        LocationEntity locationEntity2 = new LocationEntity();

        locationEntity2.setId("1");
        locationEntity2.setCity("Yaounde");
        locationEntity2.setZone("mimboman");
        locationEntity2.setCheckpointAvailable(true);

        List<LocationEntity> locationList = List.of(locationEntity1, locationEntity2);

        when(locationRepository.findAll()).thenReturn(locationList);
        List<LocationEntity> result = locationService.getAllLocations();

        assertEquals(locationList, result);
        verify(locationRepository).findAll();

    }

    @Test
    void getLocationById() {
        String id = "1";
        
        LocationEntity locationEntity = new LocationEntity();
        locationEntity.setId("1");


        when(locationRepository.findById(id)).thenReturn(Optional.of(locationEntity));
        LocationEntity result = locationService.getLocationById(id);

        assertEquals(locationEntity, result);
        verify(locationRepository).findById(id);
    }
}
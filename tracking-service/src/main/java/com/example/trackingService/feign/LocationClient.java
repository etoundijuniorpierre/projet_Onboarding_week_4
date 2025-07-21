package com.example.trackingService.feign;

import com.example.trackingService.dto.microServicesDto.LocationResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "location-service", url = "${location-service.url}")
public interface LocationClient {

    @GetMapping("/{id}")
    ResponseEntity<LocationResponseDto> getLocationById(@PathVariable String id);
}

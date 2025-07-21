package com.example.checkpoint.feign;

import com.example.checkpoint.dto.microServicesDto.LocationResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "location-service", url = "http://localhost:8081/api/location")
public interface LocationClient {
    @GetMapping("/{id}")
    ResponseEntity<LocationResponseDto> getLocationById(@PathVariable String id);
}

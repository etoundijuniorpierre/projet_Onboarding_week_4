package com.location_service.feign;


import com.location_service.dto.microServiceDto.PackageResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "package-service", url = "http://localhost:8080/api/packages")
public interface PackageClient {
    @GetMapping("/{id}")
    ResponseEntity<PackageResponseDto> getById(@PathVariable Long id);
}

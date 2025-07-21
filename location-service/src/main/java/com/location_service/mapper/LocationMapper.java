package com.location_service.mapper;

import com.location_service.dto.LocationRequestDto;
import com.location_service.dto.LocationResponseDto;
import com.location_service.dto.microServiceDto.PackageResponseDto;
import com.location_service.entity.LocationEntity;
import com.location_service.feign.PackageClient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {}) // Add any other helper classes if needed
public abstract class LocationMapper {

     @Autowired
     protected PackageClient packageClient;

     @Mapping(target = "packageId", source = "packageId")
     public abstract LocationEntity toEntity(LocationRequestDto locationRequestDto);

     @Mapping(target = "packages", source = "packageId", qualifiedByName = "packageLocation")
     public abstract LocationResponseDto toDto(LocationEntity locationEntity);

     public abstract List<LocationResponseDto> toDtoList(List<LocationEntity> locationEntities);

     @Named("packageLocation")
     protected PackageResponseDto packageLocation(Long id) {
          if (id == null) {
               return null;
          }
          return packageClient.getById(id).getBody();
     }
}
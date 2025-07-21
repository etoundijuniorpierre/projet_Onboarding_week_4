package com.example.checkpoint.mapper;

import com.example.checkpoint.dto.CheckpointRequestDto;
import com.example.checkpoint.dto.CheckpointResponseDto;
import com.example.checkpoint.dto.microServicesDto.LocationResponseDto;
import com.example.checkpoint.entity.CheckpointEntity;
import com.example.checkpoint.feign.LocationClient;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class  CheckpointMapper {

    @Autowired
    private LocationClient locationClient;

    @Mapping(target = "locationId", source = "locationId")
    @Mapping(target = "packageId", source = "packageId")
    public abstract CheckpointEntity toEntity(CheckpointRequestDto checkpointRequestDto);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "locationDto", source = "locationId", qualifiedByName = "checkpointLocation")
    public abstract CheckpointResponseDto toDto(CheckpointEntity checkpointEntity);

    public abstract List<CheckpointResponseDto> toDtoList(List<CheckpointEntity> checkpointEntities);

    @Named("checkpointLocation")
    public LocationResponseDto checkpointLocation(Long id) {
        if (id == null) {
            return null;
        }
        LocationResponseDto dto = locationClient.getLocationById(String.valueOf(id)).getBody();
        if (dto == null) {
            return null;
        }
        return dto;
    }

}


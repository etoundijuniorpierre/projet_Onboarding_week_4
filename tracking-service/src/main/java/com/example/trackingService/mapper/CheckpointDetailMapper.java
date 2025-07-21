package com.example.trackingService.mapper;

import com.example.trackingService.dto.CheckpointDetailDto;
import com.example.trackingService.entity.CheckpointDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CheckpointDetailMapper {


    @Mapping(source = "locationId", target = "locationId")
    @Mapping(source = "city", target = "city")
    @Mapping(source = "zone", target = "zone")
    @Mapping(source = "checkpointAvailable", target = "checkpointAvailable")
    @Mapping(source = "passDateTime", target = "passDateTime")
    CheckpointDetailDto toDto(CheckpointDetail entity);

    List<CheckpointDetailDto> toDtoList(List<CheckpointDetail> checkpointDetails);
}
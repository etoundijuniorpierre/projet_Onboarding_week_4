package com.example.trackingService.mapper;

import com.example.trackingService.dto.TrackingResponseDto;
import com.example.trackingService.entity.TrackingRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring", uses = {CheckpointDetailMapper.class}) // Indique que ce mapper utilise CheckpointDetailMapper
public interface TrackingMapper {


    @Mapping(target = "packageId", source = "packageId")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "weight", source = "weight")
    @Mapping(target = "fragile", source = "fragile")
    @Mapping(target = "currentStatus", source = "currentStatus")
    @Mapping(target = "lastUpdated", source = "lastUpdated")
    TrackingResponseDto toDto(TrackingRecord entity);

    List<TrackingResponseDto> toDtoList(List<TrackingRecord> trackingRecords);

}

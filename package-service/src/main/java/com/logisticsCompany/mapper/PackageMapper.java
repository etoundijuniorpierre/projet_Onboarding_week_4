package com.logisticsCompany.mapper;

import com.logisticsCompany.dto.PackageResponseDto;
import com.logisticsCompany.dto.PackageRequestDto;
import com.logisticsCompany.entities.PackageEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",

        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PackageMapper {

    PackageEntity toEntity(PackageRequestDto packageRequestDto);

    PackageResponseDto toDto(PackageEntity packageEntity);

    List<PackageResponseDto> toDtoList(List<PackageEntity> packageEntities);

    void updateEntityFromDto(PackageRequestDto packageRequestDto, @MappingTarget PackageEntity packageEntity);
}

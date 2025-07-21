package com.logisticsCompany.repository;


import com.logisticsCompany.entities.PackageEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PackageRepository extends JpaRepository<PackageEntity, Long> {

}

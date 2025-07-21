package com.logisticsCompany.service;


import com.logisticsCompany.entities.PackageEntity;
import com.logisticsCompany.entities.enums.Status;
import com.logisticsCompany.repository.PackageRepository;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class PackageService {

    private final PackageRepository packageRepository;

    public PackageService(PackageRepository packageRepository) {
        this.packageRepository = packageRepository;
    }


    //create packageEntity
    public PackageEntity createPackage(PackageEntity packageEntity) {
        if (packageEntity.getStatus() == null){
            packageEntity.setStatus(Status.NOT_DELIVERED);
        }
        return packageRepository.save(packageEntity);
    }

    //getAll
    public List<PackageEntity> getAllPackages() {
        return packageRepository.findAll();
    }


    //getByID
    public PackageEntity getPackageById(Long id) {
        return packageRepository.findById(id).orElseThrow(() -> new RuntimeException("Package not found"));
    }


    //update
    public PackageEntity updatePackage(Long id, PackageEntity packageEntity) {
        PackageEntity packageEntityExist = packageRepository.findById(id).orElseThrow(() -> new RuntimeException("Package not found"));
        return packageRepository.save(packageEntityExist);
    }


    //delete
    public void deletePackage(Long id) {
        PackageEntity packageEntityExist = packageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Package not found with ID: " + id));
        packageRepository.delete(packageEntityExist);
    }

}

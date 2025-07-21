package com.logisticsCompany.projection;

import com.logisticsCompany.entities.PackageEntity;
import com.logisticsCompany.entities.enums.Status;
import com.logisticsCompany.event.PackageCreatedEvent;
import com.logisticsCompany.event.PackageDeletedEvent;
import com.logisticsCompany.event.PackageStatusUpdatedEvent;
import com.logisticsCompany.repository.PackageRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public class PackageProjection {

    private final PackageRepository packageRepository;

    public PackageProjection(PackageRepository packageRepository) {
        this.packageRepository = packageRepository;
    }

    //event create
    @EventHandler
    public void on(PackageCreatedEvent event) {
        PackageEntity packageEntity = new PackageEntity();
        packageRepository.save(packageEntity);
        System.out.println("PackageProjection: Saved new package to DB - ID: " + event.getPackageId());
    }

    // event update
    @EventHandler
    public void on(PackageStatusUpdatedEvent event) {
        packageRepository.findById(Long.valueOf(event.getPackageId())).ifPresent(packageEntity -> {
            packageEntity.setStatus(Status.valueOf(event.getNewStatus()));
            packageRepository.save(packageEntity);
            System.out.println("PackageProjection: Updated package status in DB - ID: " + event.getPackageId() + ", New Status: " + event.getNewStatus());
        });
    }


    // event delete
    @EventHandler // Écoute l'événement de suppression de colis
    public void on(PackageDeletedEvent event) {
        packageRepository.deleteById(Long.valueOf(event.getPackageId()));
        System.out.println("PackageProjection: Deleted package from DB - ID: " + event.getPackageId());
    }
}

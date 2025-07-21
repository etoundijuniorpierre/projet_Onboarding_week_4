package com.logisticsCompany.service;

import com.logisticsCompany.entities.PackageEntity;
import com.logisticsCompany.repository.PackageRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.List;
import java.util.Optional;

import static com.logisticsCompany.entities.enums.Status.DELIVERED;
import static com.logisticsCompany.entities.enums.Status.NOT_DELIVERED;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // call mockito in JUnit 5
class PackageServiceTest {

    @Mock //mocks the repository
    private PackageRepository packageRepository;

    @InjectMocks //Inject the mocked repository and mapper in service
    private PackageService packageService;


    //TEST CREATE
    @Test
    void shouldCreateNewPackageEntity() {
        // 1- given

        PackageEntity packageEntity = new PackageEntity();
        packageEntity.setPackageId(1L);
        packageEntity.setDescription("une description");
        packageEntity.setWeight(10);
        packageEntity.setFragile(true);
        packageEntity.setStatus(DELIVERED);


        // 2- when

        // condition + mapping
        when(packageRepository.save(packageEntity)).thenReturn(packageEntity);
        PackageEntity savedEntity = packageService.createPackage(packageEntity);
        // 3- then

        // vérification appel des méthodes
        assertEquals(packageEntity, savedEntity);
        verify(packageRepository).save(packageEntity);

    }



    //test GETall
    @Test
    void shouldGetListOfAllPackages() {
            // 1- given

        // initialise l'entity 1
            PackageEntity package1 = new PackageEntity();
            package1.setPackageId(1L);
            package1.setDescription("Une description 1");
            package1.setWeight(5);
            package1.setFragile(true);
            package1.setStatus(DELIVERED);

        // initialise l'entity 2
            PackageEntity package2 = new PackageEntity();
            package2.setPackageId(2L);
            package2.setDescription("Une description 2");
            package2.setWeight(8);
            package2.setFragile(false);
            package2.setStatus(NOT_DELIVERED);

        // mettre dans une entityList
            List<PackageEntity> entities = List.of(package1, package2);


            //2- when
            when(packageRepository.findAll()).thenReturn(entities);


            List<PackageEntity> result = packageService.getAllPackages();

            //3- then

        // condition à respecter
            assertNotNull(result);
            assertEquals(2, result.size());
            assertEquals("Une description 1", result.get(0).getDescription());
            assertEquals("Une description 2", result.get(1).getDescription());
    }



//TEST GETBYid
    @Test
    void shouldGetPackageById() {
        // 1- given

        //param à chercher
        Long id = 1L;

        // initialise l'entity
        PackageEntity packageEntity = new PackageEntity();
        packageEntity.setPackageId(id);


        //2- when
        when(packageRepository.findById(id)).thenReturn(Optional.of(packageEntity));
        PackageEntity result = packageService.getPackageById(id);

        //3- then
        // condition à respecter
        assertEquals(id, result.getPackageId());
    }



    @Test
    void shouldUpdatePackageEntity() {
        // 1- given
        //param à chercher
        Long id = 1L;


        // initialise actual entity
        PackageEntity packageEntity = new PackageEntity();
        packageEntity.setPackageId(1L);
        packageEntity.setDescription("une description");
        packageEntity.setWeight(10);
        packageEntity.setFragile(true);
        packageEntity.setStatus(DELIVERED);

        // initialise new update entity
        PackageEntity UpdatePackageEntity = new PackageEntity();
        packageEntity.setPackageId(1L);
        packageEntity.setDescription("new description");
        packageEntity.setWeight(11);
        packageEntity.setFragile(true);
        packageEntity.setStatus(NOT_DELIVERED);

        //2- when
        when(packageRepository.findById(id)).thenReturn(Optional.of(packageEntity));
        when(packageRepository.save(packageEntity)).thenReturn(UpdatePackageEntity);

        PackageEntity result = packageService.updatePackage(id, packageEntity);

        //3- then
        assertEquals(UpdatePackageEntity, result);

    }

    @Test
    void shouldDeletePackageEntity() {
        // 1- given
        Long id = 1L;

        PackageEntity packageEntity = new PackageEntity();
        packageEntity.setPackageId(1L);

        // 2- when

        // Configuration du mock pour FINDById
        when(packageRepository.findById(id)).thenReturn(Optional.of(packageEntity));

        packageService.deletePackage(id);

        // 3- then

        // vérification appel du FINDBiId
        verify(packageRepository).findById(id);
        // vérification appel du delete
        verify(packageRepository).delete(packageEntity);
    }
}
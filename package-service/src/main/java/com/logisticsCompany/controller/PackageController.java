package com.logisticsCompany.controller;
import com.logisticsCompany.command.DeletePackageCommand;
import com.logisticsCompany.command.UpdatePackageStatusCommand;
import com.logisticsCompany.dto.PackageResponseDto;
import com.logisticsCompany.dto.PackageRequestDto;
import com.logisticsCompany.entities.PackageEntity;
import com.logisticsCompany.entities.enums.Status;
import com.logisticsCompany.mapper.PackageMapper;
import com.logisticsCompany.service.PackageService;
import jakarta.validation.Valid;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.logisticsCompany.command.CreatePackageCommand;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/packages")
public class PackageController {

    private final PackageService packageService;
    private final PackageMapper packageMapper;
    private final CommandGateway commandGateway;

    public PackageController(PackageService packageService, PackageMapper packageMapper, CommandGateway commandGateway) {
        this.packageService = packageService;
        this.packageMapper = packageMapper;
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public CompletableFuture<ResponseEntity<PackageResponseDto>> createPackage(@Valid @RequestBody PackageRequestDto packageRequestDto) {
        String packageId = UUID.randomUUID().toString();
        CreatePackageCommand command = new CreatePackageCommand(
                packageId,
                packageRequestDto.getDescription(),
                packageRequestDto.getWeight(),
                packageRequestDto.getFragile(),
                packageRequestDto.getStatus().toString()
        );
        // sent commande and wait confirmation
        return commandGateway.send(command)
                .thenApply(result -> {
                    PackageResponseDto responseDto = new PackageResponseDto();
                    responseDto.setPackageId(Long.valueOf(packageId));
                    responseDto.setDescription(packageRequestDto.getDescription());
                    responseDto.setWeight(packageRequestDto.getWeight());
                    responseDto.setFragile(packageRequestDto.getFragile());
                    responseDto.setStatus(packageRequestDto.getStatus() != null ? packageRequestDto.getStatus() : Status.valueOf("CREATED"));
                    return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
                });
    }


    @GetMapping("/all")
    public ResponseEntity<List<PackageResponseDto>> getAll() {
        List<PackageEntity> packageEntities = packageService.getAllPackages();
        return ResponseEntity.ok(packageMapper.toDtoList(packageEntities));
    }


    @GetMapping("/{id}")
    public ResponseEntity<PackageResponseDto> getById(@PathVariable Long id) {
        PackageEntity packageEntity = packageService.getPackageById(id);
        PackageResponseDto packageResponseDto = packageMapper.toDto(packageEntity);
        return ResponseEntity.ok(packageResponseDto);
    }

    @PutMapping("/{id}")
    public CompletableFuture<ResponseEntity<PackageResponseDto>> updatePackageStatus(@PathVariable String id, @RequestParam String newStatus) {
        UpdatePackageStatusCommand command = new UpdatePackageStatusCommand(id, newStatus);
        return commandGateway.send(command)
                .thenApply(result -> {
                    PackageEntity updatedPackage = packageService.getPackageById(Long.valueOf(id));
                    return ResponseEntity.ok(packageMapper.toDto(updatedPackage));
                });
    }

    @DeleteMapping("/{id}")
    public CompletableFuture<ResponseEntity<Void>> deletePackage(@PathVariable String id) {
        DeletePackageCommand command = new DeletePackageCommand(id);

        return commandGateway.send(command)
                .thenApply(result -> ResponseEntity.noContent().<Void>build())
                .exceptionally(ex -> {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error deleting package: " + ex.getMessage(), ex);
                });
    }

}

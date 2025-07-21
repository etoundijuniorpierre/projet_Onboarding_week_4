package com.example.checkpoint.controller;

import com.example.checkpoint.dto.CheckpointRequestDto;
import com.example.checkpoint.dto.CheckpointResponseDto;
import com.example.checkpoint.dto.microServicesDto.LocationResponseDto;
import com.example.checkpoint.entity.CheckpointEntity;
import com.example.checkpoint.event.CheckpointPassedEvent;
import com.example.checkpoint.mapper.CheckpointMapper;
import com.example.checkpoint.service.CheckpointService;
import jakarta.validation.Valid;
import org.axonframework.eventhandling.gateway.EventGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/checkpoint")
public class CheckpointController {

    private final CheckpointService checkpointService;

    private final CheckpointMapper checkpointMapper;
    private final EventGateway eventGateway;

    public CheckpointController(CheckpointService checkpointService, CheckpointMapper checkpointMapper, EventGateway eventGateway) {
        this.checkpointService = checkpointService;
        this.checkpointMapper = checkpointMapper;
        this.eventGateway = eventGateway;
    }


    @PostMapping
    public ResponseEntity<CheckpointResponseDto> createCheckpoint(@Valid @RequestBody CheckpointRequestDto checkpointRequestDto) {

        CheckpointEntity checkpointEntity = checkpointMapper.toEntity(checkpointRequestDto);
        CheckpointEntity createdCheckpoint = checkpointService.createCheckpoint(checkpointEntity);

        CheckpointPassedEvent event = new CheckpointPassedEvent(
                createdCheckpoint.getId().toString(),
                createdCheckpoint.getPackageId().toString(),
                createdCheckpoint.getLocationId().toString(),
                createdCheckpoint.getPassDateTime()
        );
        eventGateway.publish(event); // Publier l'événement
        System.out.println("Published CheckpointPassedEvent for package " + event.getPackageId() + " at location " + event.getLocationId());

        CheckpointResponseDto responseDto = checkpointMapper.toDto(createdCheckpoint);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }


    @GetMapping("/all")
    public ResponseEntity<List<CheckpointResponseDto>> getAllCheckpoints() {
        List<CheckpointEntity> checkpointEntities = checkpointService.getAllCheckpoints();
        return ResponseEntity.ok(checkpointMapper.toDtoList(checkpointEntities));
    }


    @GetMapping("/{id}")
    public ResponseEntity<CheckpointResponseDto> getCheckpointById(@PathVariable Long id) {
        CheckpointEntity checkpointEntity = checkpointService.getCheckpointById(id);
        return ResponseEntity.ok(checkpointMapper.toDto(checkpointEntity));
    }
}

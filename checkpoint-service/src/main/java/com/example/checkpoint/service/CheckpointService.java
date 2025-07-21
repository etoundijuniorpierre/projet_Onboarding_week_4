package com.example.checkpoint.service;

import com.example.checkpoint.entity.CheckpointEntity;
import com.example.checkpoint.repository.CheckpointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckpointService {
    @Autowired
    private CheckpointRepository checkpointRepository;

    public CheckpointEntity createCheckpoint(CheckpointEntity checkpointEntity) {
        if(checkpointEntity.getLocationId() == null || checkpointEntity.getPackageId() == null) {
            throw new IllegalArgumentException("checkpoint entity id and package entity id are required");
        }

        return checkpointRepository.save(checkpointEntity);
    }

    public List<CheckpointEntity> getAllCheckpoints() {
        return checkpointRepository.findAll();
    }

    public CheckpointEntity getCheckpointById(Long id) {
        return checkpointRepository.findById(id).orElseThrow(() -> new RuntimeException("checkpoint not found with id: " + id));
    }
}

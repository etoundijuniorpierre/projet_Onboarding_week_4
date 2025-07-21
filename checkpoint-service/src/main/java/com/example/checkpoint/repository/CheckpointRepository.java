package com.example.checkpoint.repository;

import com.example.checkpoint.entity.CheckpointEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckpointRepository extends JpaRepository<CheckpointEntity, Long> {

}

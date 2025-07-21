package com.example.checkpoint.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class CheckpointEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Long packageId;

    @Column
    private Long locationId;

    @Column
    private LocalDateTime passDateTime = LocalDateTime.now();
}

package com.example.trackingService.repository;


import com.example.trackingService.entity.TrackingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackingRecordRepository extends JpaRepository<TrackingRecord, String> {
}

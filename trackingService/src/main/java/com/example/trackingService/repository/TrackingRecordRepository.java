package com.example.trackingService.repository;

import com.example.trackingservice.query.TrackingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackingRecordRepository extends JpaRepository<TrackingRecord, Long> {
}

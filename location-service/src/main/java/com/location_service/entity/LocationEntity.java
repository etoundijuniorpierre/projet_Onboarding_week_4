package com.location_service.entity;



import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Data

@Document(collection = "tests")
public class LocationEntity {
    @Id
    private String id ;

    private String city;

    private String zone;

    private Long packageId;

    private boolean checkpointAvailable;

}

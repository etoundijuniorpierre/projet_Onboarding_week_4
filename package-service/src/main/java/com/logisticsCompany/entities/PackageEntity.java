package com.logisticsCompany.entities;
import com.logisticsCompany.entities.enums.Status;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
public class PackageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long packageId;

    @Column(nullable = false)
    public String description;

    @Column(nullable = false)
    public Integer weight;

    public boolean fragile;

    @Column()
    @Enumerated(EnumType.STRING)
    public Status status;

}

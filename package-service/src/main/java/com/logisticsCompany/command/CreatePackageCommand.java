package com.logisticsCompany.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePackageCommand {
    @TargetAggregateIdentifier
    private String packageId;
    private String description;
    private Integer weight;
    public boolean fragile;
    private String status;
}


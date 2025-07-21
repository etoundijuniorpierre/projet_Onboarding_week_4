package com.logisticsCompany.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePackageStatusCommand {
    @TargetAggregateIdentifier
    private String packageId;
    private String newStatus;

}


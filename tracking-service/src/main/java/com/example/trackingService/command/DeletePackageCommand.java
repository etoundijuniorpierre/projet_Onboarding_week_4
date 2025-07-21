package com.example.trackingService.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeletePackageCommand {
    @TargetAggregateIdentifier
    private String packageId;
}

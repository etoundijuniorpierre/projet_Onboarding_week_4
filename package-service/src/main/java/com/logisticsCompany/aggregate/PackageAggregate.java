package com.logisticsCompany.aggregate;


import com.logisticsCompany.command.CreatePackageCommand;
import com.logisticsCompany.command.DeletePackageCommand;
import com.logisticsCompany.command.UpdatePackageStatusCommand;
import com.logisticsCompany.event.PackageCreatedEvent;
import com.logisticsCompany.event.PackageDeletedEvent;
import com.logisticsCompany.event.PackageStatusUpdatedEvent;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.time.Instant;


@Aggregate
@NoArgsConstructor
public class PackageAggregate {

    @AggregateIdentifier // this is ID for agrégat
    private String packageId;
    private String description;
    private Integer weight;
    public boolean fragile;
    private String status;

    //realization of the command

    //create
    @CommandHandler
    public PackageAggregate(CreatePackageCommand command) {

        AggregateLifecycle.apply(new PackageCreatedEvent(
                command.getPackageId(),
                command.getDescription(),
                command.getWeight(),
                command.isFragile(),
                command.getStatus() != null ? command.getStatus() : "CREATED",
                Instant.now().toEpochMilli()
        ));
    }

    //update
    @CommandHandler
    public void handle(UpdatePackageStatusCommand command) {

        AggregateLifecycle.apply(new PackageStatusUpdatedEvent(
                command.getPackageId(),
                this.status,
                command.getNewStatus(),
                Instant.now().toEpochMilli()
        ));
    }

    //delete
    @CommandHandler
    public void handle(DeletePackageCommand command) {
        AggregateLifecycle.apply(new PackageDeletedEvent(
                command.getPackageId(),
                Instant.now().toEpochMilli()
        ));
        AggregateLifecycle.markDeleted();
    }


    //implement event
    @EventSourcingHandler
    public void on(PackageCreatedEvent event) {
        this.packageId = event.getPackageId();
        this.description = event.getDescription();
        this.weight = event.getWeight();
        this.fragile = event.isFragile();
        this.status = event.getStatus();
        System.out.println("PackageAggregate: Package created l'id: " + packageId + ", Status: " + status);
    }

    @EventSourcingHandler
    public void on(PackageStatusUpdatedEvent event) {
        this.status = event.getNewStatus();
        System.out.println("PackageAggregate: Package status updated l'id : " + packageId + ", New Status: " + status);
    }

    @EventSourcingHandler
    public void on(PackageDeletedEvent event) {
        System.out.println("PackageAggregate: Package deleted l'id: " + packageId);
    }
}

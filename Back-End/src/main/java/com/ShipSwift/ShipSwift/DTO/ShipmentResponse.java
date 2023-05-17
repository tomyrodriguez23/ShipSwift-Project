package com.ShipSwift.ShipSwift.DTO;
import com.ShipSwift.ShipSwift.model.enums.PayStatus;
import com.ShipSwift.ShipSwift.model.enums.ShipmentStatus;
import com.ShipSwift.ShipSwift.model.enums.ShipmentType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@Builder
public class ShipmentResponse {

    @NotNull(message = "ID cannot be null")
    private Long id;

    @NotNull(message = "SSC cannot be null")
    private String ssc;

    @NotNull(message = "Origin cannot be null")
    private BranchDto origin;

    @NotNull(message = "Destination cannot be null")
    private DestinationDto destination;

    @NotNull(message = "Type cannot be null")
    private ShipmentType shipmentType;

    @NotNull(message = "shipmentStatus cannot be null")
    private ShipmentStatus shipmentStatus;

    @NotNull(message = "shipmentStatus cannot be null")
    private PayStatus payStatus;

    @NotNull(message = "Cost cannot be null")
    private Double cost;

    @NotNull(message = "creationDate cannot be null")
    private LocalDate creationDate;

    @NotNull(message = "Package Shipment cannot be null")
    private PackageShipmentDto packageShipment;

    @NotNull(message = "Customer Id cannot be null")
    private Long customerId;

}

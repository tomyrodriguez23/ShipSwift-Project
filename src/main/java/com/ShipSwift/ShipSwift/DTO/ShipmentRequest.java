package com.ShipSwift.ShipSwift.DTO;
import com.ShipSwift.ShipSwift.model.enums.ShipmentType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
@Getter
public class ShipmentRequest {

    @NotNull(message = "Origin cannot be null")
    private BranchDto origin;

    @NotNull(message = "Destination cannot be null")
    private DestinationDto destination;

    @NotNull(message = "Type cannot be null")
    private ShipmentType shipmentType;

    @NotNull(message = "Package Shipment cannot be null")
    private PackageShipmentDto packageShipment;

    @NotNull(message = "Customer cannot be null")
    private Long customerId;
}

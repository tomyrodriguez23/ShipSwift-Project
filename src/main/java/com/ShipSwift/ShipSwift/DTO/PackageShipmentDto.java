package com.ShipSwift.ShipSwift.DTO;
import com.ShipSwift.ShipSwift.model.enums.PackageType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class PackageShipmentDto {

    private Long id;

    @NotNull(message = "PackageType cannot be null")
    private PackageType packageType;

    @DecimalMin(value = "0.1", message = "Weight should be greater than 0")
    @NotNull(message = "weight cannot be null")
    private Double weight;

    @DecimalMin(value = "0.1", message = "Length should be greater than 0")
    @NotNull(message = "length cannot be null")
    private Double length;

    @DecimalMin(value = "0.1", message = "Width should be greater than 0")
    @NotNull(message = "width cannot be null")
    private Double width;

    @DecimalMin(value = "0.1", message = "Height should be greater than 0")
    @NotNull(message = "height cannot be null")
    private Double height;

    @DecimalMin(value = "0.1", message = "Value should be greater than 0")
    @NotNull(message = "insuranceValue cannot be null")
    private Double insuranceValue;

}

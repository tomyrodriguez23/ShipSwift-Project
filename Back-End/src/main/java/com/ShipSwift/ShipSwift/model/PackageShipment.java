package com.ShipSwift.ShipSwift.model;
import com.ShipSwift.ShipSwift.model.enums.PackageType;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "packages")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PackageShipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PackageType packageType;

    @Column(nullable = false)
    @DecimalMin(value = "0.1", message = "Weight should be greater than 0")
    private Double weight;

    @Column(nullable = false)
    @DecimalMin(value = "0.1", message = "Length should be greater than 0")
    private Double length;

    @Column(nullable = false)
    @DecimalMin(value = "0.1", message = "Width should be greater than 0")
    private Double width;

    @Column(nullable = false)
    @DecimalMin(value = "0.1", message = "Height should be greater than 0")
    private Double height;

    @Column(nullable = false)
    @DecimalMin(value = "0.1", message = "Value should be greater than 0")
    private Double insuranceValue;
}

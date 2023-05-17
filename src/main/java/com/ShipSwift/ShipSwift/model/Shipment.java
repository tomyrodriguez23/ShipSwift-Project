package com.ShipSwift.ShipSwift.model;
import com.ShipSwift.ShipSwift.model.enums.PayStatus;
import com.ShipSwift.ShipSwift.model.enums.ShipmentType;
import com.ShipSwift.ShipSwift.model.enums.ShipmentStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
@Table(name = "shipments")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Size(min = 1, max = 2000, message = "SSC should have max 2000 characters")
    private String ssc;

    @ManyToOne
    @JoinColumn(name = "branch_id", referencedColumnName = "id", nullable = false)
    private Branch origin;

    @OneToOne
    @JoinColumn(name = "destination_id", referencedColumnName = "id", nullable = false)
    private Destination destination;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ShipmentStatus shipmentStatus;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PayStatus payStatus;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ShipmentType shipmentType;

    @Column(nullable = false)
    @DecimalMin(value = "0.0", message = "Cost should be positive")
    private Double cost;

    @Column(nullable = false)
    @PastOrPresent(message = "Creation date should be in the past or present")
    private LocalDate creationDate;

    @OneToOne
    @JoinColumn(name = "package_id", referencedColumnName = "id", nullable = false)
    private PackageShipment packageShipment;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
    private Customer customer;

}

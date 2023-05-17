package com.ShipSwift.ShipSwift.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Table(name = "residences")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Residence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Size(min = 10, max= 1000, message = "Latitude should have between 10 and 1000 characters")
    private String latitude;

    @Column(nullable = false)
    @Size(min = 10, max = 1000, message = "Longitude should have between 10 and 1000 characters")
    private String longitude;

    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "id", nullable = false)
    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
    private Country country;

    @Column(nullable = false)
    @Size(min = 1, max = 250, message = "Name should have between 1 and 250 characters")
    private String name;

    @Column(nullable = false)
    @Size(min = 1, max = 250, message = "Address line should have between 1 and 250 characters")
    private String line;

    @Column(nullable = false)
    @Size(min = 2, max = 25, message = "Zip Code should have between 2 and 25 characters")
    private String zipCode;

}
package com.ShipSwift.ShipSwift.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.util.Set;

@Table(name = "countries")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Size(min = 1, max = 250, message = "Name should have between 1 and 250 characters")
    private String name;

    @OneToMany(mappedBy = "country")
    @JsonIgnore
    private Set<Branch> branches;

    @OneToMany(mappedBy = "country")
    @JsonIgnore
    private Set<Customer> customers;

}

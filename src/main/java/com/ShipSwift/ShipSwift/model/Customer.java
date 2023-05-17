package com.ShipSwift.ShipSwift.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@Table(name = "customers")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Size(min = 1, max = 250, message = "Name should have between 1 and 250 characters")
    private String name;

    @Column(nullable = false)
    @Size(min = 1, max = 250, message = "Last name should have between 1 and 250 characters")
    private String lastName;

    @Column(nullable = false)
    @Email(message = "Email should be valid")
    private String email;

    @Column(nullable = false)
    @Size(min = 6, max = 250, message = "Last name should have between 6 and 250 characters")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[A-Z])[A-Za-z\\d]{6,}$", message = "Password must start with a capital letter, contain at least one number, and have a minimum length of 6 characters.")
    private String password;

    @Column(nullable = false)
    @Size(min = 10, max = 25, message = "Phone Number should have between 10 and 25 characters")
    private String phoneNumber;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "balance_id", referencedColumnName = "id", nullable = false)
    private Balance balance;

    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "id", nullable = false)
    private Country country;

    @ManyToOne
    @JoinColumn(name = "preference_branch_id", referencedColumnName = "id", nullable = false)
    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
    private Branch preferenceBranch;

    @OneToMany(mappedBy = "customer")
    private Set<Shipment> shipments;

    @OneToMany(mappedBy = "customer")
    private Set<Transaction> transactions;
}

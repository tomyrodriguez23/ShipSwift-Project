package com.ShipSwift.ShipSwift.model;

import com.ShipSwift.ShipSwift.model.enums.TransactionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Table(name = "transactions")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @PastOrPresent(message = "Creation date should be in the past or present")
    private LocalDateTime createdAd;

    @Column(nullable = false)
    private TransactionType type;

    @Column(nullable = false)
    @DecimalMin(value = "0.01", message = "Amount must be greater than or equal to 0.01.")
    private Double amount;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
    private Customer customer;

}

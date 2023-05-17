package com.ShipSwift.ShipSwift.DTO;

import com.ShipSwift.ShipSwift.model.Customer;
import com.ShipSwift.ShipSwift.model.enums.TransactionType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TransactionDto {

    private Long id;

    @PastOrPresent(message = "Creation date should be in the past or present")
    private LocalDateTime createdAd;

    @NotNull(message = "TransactionType is required")
    private TransactionType type;

    @DecimalMin(value = "0.01", message = "Amount must be greater than or equal to 0.01.")
    private Double amount;

    @NotNull(message = "Customer is required")
    private Customer customer;

}

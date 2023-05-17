package com.ShipSwift.ShipSwift.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
@Getter
public class CountryDto {

    private Long id;

    @Size(min = 1, max = 250, message = "Name should have between 1 and 250 characters")
    @NotBlank(message = "Name cannot be blank")
    private String name;

}

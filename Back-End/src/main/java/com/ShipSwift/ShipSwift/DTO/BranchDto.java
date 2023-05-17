package com.ShipSwift.ShipSwift.DTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class BranchDto {

    private Long id;

    @NotNull(message = "Country is required")
    private CountryDto country;

    @Size(min = 10, max = 1000, message = "Latitude should have between 10 and 1000 characters")
    @NotBlank(message = "Latitude cannot be blank")
    private String latitude;

    @Size(min = 10, max = 1000, message = "Longitude should have between 10 and 1000 characters")
    @NotBlank(message = "Longitude cannot be blank")
    private String longitude;

    @Size(min = 1, max = 250, message = "Name should have between 1 and 250 characters")
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Size(min = 10, max = 250, message = "Address line should have between 10 and 250 characters")
    @NotBlank(message = "Line cannot be blank")
    private String line;

    @Size(min = 10, max = 25, message = "Phone Number should have between 10 and 25 characters")
    @NotBlank(message = "Phone Number cannot be blank")
    private String phoneNumber;

    @Size(min = 4, max = 25, message = "Zip Code should have between 4 and 25 characters")
    @NotBlank(message = "Zip Code cannot be blank")
    private String zipCode;

}

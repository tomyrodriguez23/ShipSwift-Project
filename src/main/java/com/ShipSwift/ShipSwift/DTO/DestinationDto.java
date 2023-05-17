package com.ShipSwift.ShipSwift.DTO;
import com.ShipSwift.ShipSwift.model.enums.ShipmentType;
import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public class DestinationDto {

    private Long id;

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 1, max = 250, message = "Name should have between {min} and {max} characters")
    private String nameReceiver;

    @NotBlank(message = "Last name cannot be blank")
    @Size(min = 1, max = 250, message = "Last name should have between {min} and {max} characters")
    private String lastNameReceiver;

    @NotBlank(message = "Phone number cannot be blank")
    @Size(min = 10, max = 25, message = "Phone number should have between {min} and {max} characters")
    private String phoneNumberReceiver;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    private String emailReceiver;

    @NotNull(message = "Type cannot be null")
    private ShipmentType type;

    private BranchDto branch;

    private ResidenceDto residence;

}

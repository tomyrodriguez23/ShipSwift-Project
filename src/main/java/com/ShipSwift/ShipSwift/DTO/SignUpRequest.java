package com.ShipSwift.ShipSwift.DTO;
import com.ShipSwift.ShipSwift.model.Branch;
import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public class SignUpRequest {

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 1, max = 250, message = "Name should have between {min} and {max} characters")
    private String name;

    @NotBlank(message = "Last name cannot be blank")
    @Size(min = 1, max = 250, message = "Last name should have between {min} and {max} characters")
    private String lastName;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 6, max = 250, message = "Password should have between {min} and {max} characters")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[A-Z])[A-Za-z\\d]{6,}$", message = "Password must start with a capital letter, contain at least one number, and have a minimum length of 6 characters.")
    private String password;

    @NotBlank(message = "Phone number cannot be blank")
    @Size(min = 10, max = 25, message = "Phone number should have between {min} and {max} characters")
    private String phoneNumber;

    @NotNull(message = "Country number cannot be null")
    private CountryDto country;

    @NotNull(message = "Preference Branch  cannot be null")
    private Branch preferenceBranch;

}

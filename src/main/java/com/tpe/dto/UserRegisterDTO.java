package com.tpe.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserRegisterDTO {

    @NotBlank(message = "Please enter a valid firstName!")
    @Size(min = 3, max = 15, message = "The firstName must be between {min}-{max} characters")
    private String firstName;

    @NotBlank(message = "Please enter a valid lastName!")
    @Size(min = 3, max = 15, message = "The lastName must be between {min}-{max} characters")
    private String lastName;

    @NotBlank(message = "Please enter a valid userName!")
    @Size(min = 3, max = 32, message = "The userName must be between {min}-{max} characters")
    private String userName;

    @NotBlank(message = "Please enter a valid password!")
    @Size(min = 8, max = 64, message = "The password must be between {min}-{max} characters")
    private String password;
}

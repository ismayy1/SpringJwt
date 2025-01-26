package com.tpe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @NotBlank(message = "The firstName can't be null!")
    private String firstName;

    @NotBlank(message = "The lastName can't be null!")
    private String lastName;

    @NotBlank(message = "The userName can't be null!")
    private String userName;
}

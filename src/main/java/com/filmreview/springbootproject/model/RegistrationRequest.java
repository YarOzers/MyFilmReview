package com.filmreview.springbootproject.model;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {

    @NotBlank(message = "Please, provide your first name!")
    @Size(min = 6, max = 16, message = "Available size from 6 to 16 inclusively!")
    private String firstName;

    @NotBlank(message = "Please, provide your last name!")
    @Size(min = 6, max = 16, message = "Available size from 6 to 16 inclusively!")
    private String lastName;

    @NotBlank(message = "Please, provide your email!")
    @Email(message = "Not correct email.\n Please, try again!")
    private String email;

    @NotBlank(message = "Please, provide your password!")
    @Size(min = 6, max = 16, message = "Available size from 6 to 16 inclusively!")
    private String password;
}

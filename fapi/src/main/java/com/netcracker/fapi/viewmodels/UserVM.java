package com.netcracker.fapi.viewmodels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVM {
    @Digits(integer = 13, fraction = 0)
    private Long id;

    private String role = "USER";

    @Email
    @Size(max=100, message = "Email must contain less than 100 symbols")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(max=100, message = "Password must contain less than 100 symbols")
    private String password;

    @NotBlank(message = "Username is required")
    @Size(max=100, message = "Username name must contain less than 100 symbols")
    private String userName;

    @Size(max=100, message = "First name must contain less than 100 symbols")
    private String firstName;
    @Size(max=100, message = "Middle name must contain less than 100 symbols")
    private String middleName;
    @Size(max=100, message = "Last name must contain less than 100 symbols")
    private String lastName;

    private String gender;

    @Past(message = "Bad format of birth date")
    private LocalDate birthDate;

    private Boolean isBan = false;

    private Boolean isDeleted = false;
}

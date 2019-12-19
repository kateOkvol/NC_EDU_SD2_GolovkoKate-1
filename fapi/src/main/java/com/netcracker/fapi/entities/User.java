package com.netcracker.fapi.entities;

import com.netcracker.fapi.entities.enums.GenderType;
import com.netcracker.fapi.entities.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private UserRole role;
    private String email;
    private String password;
    private String userName;
    private String firstName;
    private String middleName;
    private String lastName;
    private GenderType gender;
    private LocalDate birthDate;
    private Boolean isBan;
    private Boolean isDeleted;
}

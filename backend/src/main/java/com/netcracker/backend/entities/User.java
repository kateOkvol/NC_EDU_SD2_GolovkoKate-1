package com.netcracker.backend.entities;

import com.netcracker.backend.common.PostgreSQLEnumType;
import com.netcracker.backend.entities.enums.GenderType;
import com.netcracker.backend.entities.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@TypeDef(name = "pgsql_enum",
        typeClass = PostgreSQLEnumType.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role",
            columnDefinition = "user_role")
    @Type(type = "pgsql_enum")
    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.USER;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "user_name", unique = true)
    private String userName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "gender",
            columnDefinition = "gender_type")
    @Type(type = "pgsql_enum")
    @Enumerated(EnumType.STRING)
    private GenderType gender;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "is_ban")
    private Boolean isBan = false;

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;
}

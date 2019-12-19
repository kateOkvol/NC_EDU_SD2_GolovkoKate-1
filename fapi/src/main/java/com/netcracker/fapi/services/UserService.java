package com.netcracker.fapi.services;

import com.netcracker.fapi.viewmodels.UserVM;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
public interface UserService {
    ResponseEntity findById(@NotNull Long id) throws ChangeSetPersister.NotFoundException;

    ResponseEntity create(@Valid UserVM userVM);

    ResponseEntity delete(@NotNull Long id) throws ChangeSetPersister.NotFoundException;

    ResponseEntity update(@NotNull Long id, @Valid UserVM userVM) throws ChangeSetPersister.NotFoundException;

    UserVM findByLogin(String login);

}

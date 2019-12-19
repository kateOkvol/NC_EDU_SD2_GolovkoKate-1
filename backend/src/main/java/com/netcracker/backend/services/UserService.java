package com.netcracker.backend.services;

import com.netcracker.backend.entities.User;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.constraints.NotNull;

@Validated
public interface UserService {
    User findById(@NotNull Long id) throws ChangeSetPersister.NotFoundException;

    ResponseEntity<User> create(User user) throws HttpClientErrorException.BadRequest;

    User delete(@NotNull Long id) throws ChangeSetPersister.NotFoundException;

    User update(@NotNull Long id, User user) throws ChangeSetPersister.NotFoundException;
}

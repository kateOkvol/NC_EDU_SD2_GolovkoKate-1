package com.netcracker.fapi.services;

import com.netcracker.fapi.viewmodels.PollVM;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public interface PollService {

    ResponseEntity findById(@NotNull Long id) throws ChangeSetPersister.NotFoundException;

    ResponseEntity findByLink(@NotNull String link) throws ChangeSetPersister.NotFoundException;

    ResponseEntity create(@Valid PollVM pollVM);
}

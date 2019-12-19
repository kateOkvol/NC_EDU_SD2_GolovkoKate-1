package com.netcracker.backend.services;

import com.netcracker.backend.entities.CompletedPoll;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Validated
public interface CompletedPollService {
    CompletedPoll findById(@NotNull Long id) throws ChangeSetPersister.NotFoundException;

    CompletedPoll create(CompletedPoll completedPoll);

}

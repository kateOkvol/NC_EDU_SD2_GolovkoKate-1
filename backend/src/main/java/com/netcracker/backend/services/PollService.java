package com.netcracker.backend.services;

import com.netcracker.backend.entities.Poll;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Validated
public interface PollService {
    Poll findById(@NotNull Long id) throws ChangeSetPersister.NotFoundException;

    Poll create(Poll poll);

    Poll delete(@NotNull Long id) throws ChangeSetPersister.NotFoundException;

    Poll update(@NotNull Long id, Poll poll) throws ChangeSetPersister.NotFoundException;
}

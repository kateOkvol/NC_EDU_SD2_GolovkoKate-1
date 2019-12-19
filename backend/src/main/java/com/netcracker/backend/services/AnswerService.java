package com.netcracker.backend.services;

import com.netcracker.backend.entities.Answer;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Validated
public interface AnswerService {
    Answer findById(@NotNull Long id) throws ChangeSetPersister.NotFoundException;

    Answer create(Answer answer);

    Answer delete(@NotNull Long id) throws ChangeSetPersister.NotFoundException;

    Answer update(@NotNull Long id, Answer answer) throws ChangeSetPersister.NotFoundException;
}

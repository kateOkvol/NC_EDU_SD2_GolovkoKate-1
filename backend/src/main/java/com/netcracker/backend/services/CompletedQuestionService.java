package com.netcracker.backend.services;

import com.netcracker.backend.entities.CompletedQuestion;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Validated
public interface CompletedQuestionService {
    CompletedQuestion findById(@NotNull Long id) throws ChangeSetPersister.NotFoundException;

    CompletedQuestion create(CompletedQuestion completedQuestion);
}

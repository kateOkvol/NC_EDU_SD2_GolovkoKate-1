package com.netcracker.backend.services;

import com.netcracker.backend.entities.Question;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
public interface QuestionService {
    Question findById(@NotNull Long id) throws ChangeSetPersister.NotFoundException;

    Question create(Question question);

    Question delete(@NotNull Long id) throws ChangeSetPersister.NotFoundException;

    Question update(@NotNull Long id, Question question) throws ChangeSetPersister.NotFoundException;
}

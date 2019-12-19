package com.netcracker.backend.services;

import com.netcracker.backend.entities.Topic;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Validated
public interface TopicService {
    Topic findById(@NotNull Long id) throws ChangeSetPersister.NotFoundException;

    Topic create(Topic topic);

    Topic delete(@NotNull Long id) throws ChangeSetPersister.NotFoundException;

    Topic update(@NotNull Long id, Topic topic) throws ChangeSetPersister.NotFoundException;
}

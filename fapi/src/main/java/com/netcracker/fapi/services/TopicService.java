package com.netcracker.fapi.services;

import com.netcracker.fapi.entities.Topic;
import com.netcracker.fapi.viewmodels.TopicVM;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public interface TopicService {

    ResponseEntity findById(@NotNull Long id) throws ChangeSetPersister.NotFoundException;

    ResponseEntity create(@Valid TopicVM topicVM);
}

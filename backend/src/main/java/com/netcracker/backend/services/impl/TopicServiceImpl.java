package com.netcracker.backend.services.impl;

import com.netcracker.backend.entities.Topic;
import com.netcracker.backend.services.TopicService;
import com.netcracker.backend.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service
public class TopicServiceImpl implements TopicService {
    private TopicRepository repository;

    @Autowired
    public TopicServiceImpl(TopicRepository repository) {
        this.repository = repository;
    }

    public List<Topic> findByUserId(@NotNull Long id) {
        return repository.getAllByUserIdAndIsDeletedFalse(id);
    }

    public List<Topic> findByAdmin() {
        return repository.findAllByIsSharedTrueAndIsDeletedFalse();
    }

    @Override
    public Topic findById(@NotNull Long id) throws ChangeSetPersister.NotFoundException {
        Optional<Topic> optionalTopic = repository.findById(id);
        if (!optionalTopic.isPresent()) {
            throw new ChangeSetPersister.NotFoundException();
        }
        return optionalTopic.get();
    }

    @Override
    public Topic create(Topic topic) {
        return repository.save(topic);
    }

    @Override
    public Topic delete(@NotNull Long id) throws ChangeSetPersister.NotFoundException {
        Optional<Topic> optionalTopic = repository.findById(id);
        if (!optionalTopic.isPresent()) {
            throw new ChangeSetPersister.NotFoundException();
        }
        optionalTopic.get().setIsDeleted(true);
        return optionalTopic.get();
    }

    @Override
    @Transactional
    public Topic update(@NotNull Long id, Topic topicDTO) throws ChangeSetPersister.NotFoundException {
        Optional<Topic> optionalTopic = repository.findById(id);
        if (!optionalTopic.isPresent()) {
            throw new ChangeSetPersister.NotFoundException();
        }
        Topic topic = optionalTopic.get();
        topic = topicDTO;
        return repository.save(topic);
    }
}

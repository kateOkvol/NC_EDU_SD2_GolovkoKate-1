package com.netcracker.backend.services.impl;

import com.netcracker.backend.entities.CompletedPoll;
import com.netcracker.backend.repositories.CompletedPollRepository;
import com.netcracker.backend.services.CompletedPollService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class CompletedPollServiceImpl implements CompletedPollService {
    private CompletedPollRepository repository;

    @Autowired
    public CompletedPollServiceImpl(CompletedPollRepository repository) {
        this.repository = repository;
    }

    public List<CompletedPoll> getByPollId(@NotNull Long id) {
        return repository.findAllByPollId(id);
    }

    @Override
    public CompletedPoll findById(@NotNull Long id) throws ChangeSetPersister.NotFoundException {
        Optional<CompletedPoll> optionalPoll = repository.getById(id);
        if (!optionalPoll.isPresent()) {
            throw new ChangeSetPersister.NotFoundException();
        }
        return optionalPoll.get();
    }

    @Override
    public CompletedPoll create(CompletedPoll completedPoll) {
        return repository.save(completedPoll);
    }
}

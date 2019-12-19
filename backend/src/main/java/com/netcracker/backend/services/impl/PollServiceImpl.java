package com.netcracker.backend.services.impl;

import com.netcracker.backend.entities.Poll;
import com.netcracker.backend.repositories.PollRepository;
import com.netcracker.backend.services.PollService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class PollServiceImpl implements PollService {
    private PollRepository pollRepository;

    @Autowired
    public PollServiceImpl(PollRepository pollRepository) {
        this.pollRepository = pollRepository;
    }

    public List<Poll> findByUserId(@NotNull Long id, @NotNull String isDraft) {
        return pollRepository.getAllByUserIdAndIsDeletedFalseAndIsDraft(id, Boolean.parseBoolean(isDraft));
    }

    public List<Poll> findAll(int page, int size) {
        Pageable pg = PageRequest.of(page, size);
        return pollRepository.getAllByIsDeletedFalseAndIsDraftFalse(pg);
    }

    public Poll findByLink(@NotNull String link) throws ChangeSetPersister.NotFoundException {
        Optional<Poll> optionalPoll = pollRepository.findOneByLinkAndIsDeletedFalse(link);
        if (!optionalPoll.isPresent()) {
            throw new ChangeSetPersister.NotFoundException();
        }
        return optionalPoll.get();
    }

    @Override
    public Poll findById(@NotNull Long id) throws ChangeSetPersister.NotFoundException {
        Optional<Poll> optionalPoll = pollRepository.getByIdAndIsDeletedFalse(id);
        if (!optionalPoll.isPresent()) {
            throw new ChangeSetPersister.NotFoundException();
        }
        return optionalPoll.get();
    }

    @Override
    @Transactional
    public Poll create(Poll poll) {
        return pollRepository.save(poll);
    }

    @Override
    @Transactional
    public Poll delete(@NotNull Long id) throws ChangeSetPersister.NotFoundException {
        Optional<Poll> optionalPoll = pollRepository.getByIdAndIsDeletedFalse(id);
        if (!optionalPoll.isPresent()) {
            throw new ChangeSetPersister.NotFoundException();
            //log("not found);
        }
        Poll poll = optionalPoll.get();
        poll.setIsDeleted(true);
        return poll;
    }

    @Override
    @Transactional
    public Poll update(@NotNull Long id, Poll pollDTO) throws ChangeSetPersister.NotFoundException {
        Optional<Poll> optionalPoll = pollRepository.getByIdAndIsDeletedFalse(id);
        if (!optionalPoll.isPresent()) {
            throw new ChangeSetPersister.NotFoundException();
            //log("not found);
        }
        Poll poll = optionalPoll.get();
        poll = pollDTO;
        return pollRepository.save(poll);
    }
}

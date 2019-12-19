package com.netcracker.backend.services.impl;

import com.netcracker.backend.entities.CompletedQuestion;
import com.netcracker.backend.repositories.CompletedQuestionRepository;
import com.netcracker.backend.services.CompletedQuestionService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Service
@NoArgsConstructor
public class CompletedQuestionServiceImpl implements CompletedQuestionService {
    private CompletedQuestionRepository repository;

    @Autowired
    public CompletedQuestionServiceImpl(CompletedQuestionRepository repository) {
        this.repository = repository;
    }

    @Override
    public CompletedQuestion findById(@NotNull Long id) throws ChangeSetPersister.NotFoundException {
        Optional<CompletedQuestion> optionalQuestion = repository.findById(id);
        if (!optionalQuestion.isPresent()) {
            throw new ChangeSetPersister.NotFoundException();
        }
        return optionalQuestion.get();
    }

    @Override
    public CompletedQuestion create(CompletedQuestion completedQuestion) {
        return repository.save(completedQuestion);
    }
}

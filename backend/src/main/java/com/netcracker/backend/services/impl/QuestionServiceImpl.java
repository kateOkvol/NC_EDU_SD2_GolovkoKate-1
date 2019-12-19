package com.netcracker.backend.services.impl;

import com.netcracker.backend.entities.Question;
import com.netcracker.backend.services.QuestionService;
import com.netcracker.backend.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {
    private QuestionRepository repository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Question findById(@NotNull Long id) throws ChangeSetPersister.NotFoundException {
        Optional<Question> question = repository.findByIdAndIsDeletedFalse(id);
        if (!question.isPresent()) {
            throw new ChangeSetPersister.NotFoundException();
            //log("Cannot find user with id " + id);
        }
        return question.get();
    }

    @Override
    @Transactional
    public Question create(Question question) {
        return repository.save(question);
    }

    @Override
    @Transactional
    public Question delete(@NotNull Long id) throws ChangeSetPersister.NotFoundException {
        Optional<Question> optionalQuestion = repository.findByIdAndIsDeletedFalse(id);
        if (!optionalQuestion.isPresent()) {
            throw new ChangeSetPersister.NotFoundException();
            //log("not found);
        }
        Question question = optionalQuestion.get();
        question.setIsDeleted(true);
        return question;
    }

    @Override
    @Transactional
    public Question update(@NotNull Long id, Question questionDTO) throws ChangeSetPersister.NotFoundException {
        Optional<Question> optionalQuestion = repository.findByIdAndIsDeletedFalse(id);
        if (!optionalQuestion.isPresent()) {
            throw new ChangeSetPersister.NotFoundException();
            //log("not found);
        }
        Question question = optionalQuestion.get();
        question = questionDTO;
        return repository.save(question);
    }
}

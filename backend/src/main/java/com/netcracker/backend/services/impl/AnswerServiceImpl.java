package com.netcracker.backend.services.impl;

import com.netcracker.backend.entities.Answer;
import com.netcracker.backend.repositories.AnswerRepository;
import com.netcracker.backend.services.AnswerService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service
@Data
@NoArgsConstructor
public class AnswerServiceImpl implements AnswerService {
    private AnswerRepository repository;

    @Autowired
    public AnswerServiceImpl(AnswerRepository repository) {
        this.repository = repository;
    }

    public List<Answer> findByQuestionId(@NotNull Long questionId) throws ChangeSetPersister.NotFoundException {
        List<Answer> answers = repository.getAllByQuestionIdAndIsDeletedFalse(questionId);
        if (answers.isEmpty()) {
            throw new ChangeSetPersister.NotFoundException();
            //log("Cannot find user with id " + id);
        }
        return answers;
    }

    @Override
    public Answer findById(@NotNull Long id) throws ChangeSetPersister.NotFoundException {
        Optional<Answer> optionalAnswer = repository.getByIdAndIsDeletedFalse(id);
        if (!optionalAnswer.isPresent()) {
            throw new ChangeSetPersister.NotFoundException();
            //log("Cannot find user with id " + id);
        }
        return optionalAnswer.get();
    }

    @Override
    @Transactional
    public Answer create(Answer answer) {
        return repository.save(answer);
    }

    @Override
    @Transactional
    public Answer delete(@NotNull Long id) throws ChangeSetPersister.NotFoundException {
        Optional<Answer> optionalAnswer = repository.getByIdAndIsDeletedFalse(id);
        if (!optionalAnswer.isPresent()) {
            throw new ChangeSetPersister.NotFoundException();
            //log("not found);
        }
        Answer answer = optionalAnswer.get();
        answer.setIsDeleted(true);
        return answer;
    }

    @Override
    @Transactional
    public Answer update(@NotNull Long id, Answer answer) throws ChangeSetPersister.NotFoundException {
        Optional<Answer> optionalAnswer = repository.getByIdAndIsDeletedFalse(id);
        if (!optionalAnswer.isPresent()) {
            throw new ChangeSetPersister.NotFoundException();
            //log("not found);
        }
        Answer updatedAnswer = optionalAnswer.get();
        updatedAnswer = answer;
        return repository.save(updatedAnswer);

    }

//    private AnswerDTO getBySmthId(Optional optionalAnswer) throws ChangeSetPersister.NotFoundException {
//        if (!optionalAnswer.isPresent()) {
//            throw new ChangeSetPersister.NotFoundException();
//            //log("Cannot find user with id " + id);
//        }
//        return mapping.getMapper().map(optionalAnswer.get(), AnswerDTO.class);
//    }
}

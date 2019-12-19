package com.netcracker.backend.repositories;

import com.netcracker.backend.entities.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    Optional<Answer> getByIdAndIsDeletedFalse(Long id);
    List<Answer> getAllByQuestionIdAndIsDeletedFalse(Long questionId);
}

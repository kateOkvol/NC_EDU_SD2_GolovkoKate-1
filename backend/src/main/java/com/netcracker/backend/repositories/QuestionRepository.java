package com.netcracker.backend.repositories;

import com.netcracker.backend.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long>/*, QuerydslPredicateExecutor<Question> */{
    Optional<Question> findByIdAndIsDeletedFalse(Long id);
}

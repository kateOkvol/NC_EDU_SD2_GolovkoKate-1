package com.netcracker.backend.repositories;

import com.netcracker.backend.entities.CompletedQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompletedQuestionRepository extends JpaRepository<CompletedQuestion, Long> {
//    @Query(value = "SELECT * FROM completed_questoins cq JOIN completed_polls ON cq.completed_poll_id = completed_polls.id WHERE completed_polls.poll_id = :pollId",
//            nativeQuery = true)
//    List<CompletedQuestion> getAllByPollId(@Param("pollId") Long pollId);

    List<CompletedQuestion> findAllByQuestionId(Long id);
}

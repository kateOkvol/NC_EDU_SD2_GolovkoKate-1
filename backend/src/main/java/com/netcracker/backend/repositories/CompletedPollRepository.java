package com.netcracker.backend.repositories;

import com.netcracker.backend.entities.CompletedPoll;
import com.netcracker.backend.entities.CompletedQuestion;
import com.netcracker.backend.entities.Statistic;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CompletedPollRepository extends JpaRepository<CompletedPoll, Long> {
    Optional<CompletedPoll> getById(Long id);

    List<CompletedPoll> findAllByPollId(Long id);

//    @Query(value =
//            "SELECT count(cq.id) as value, question_id, answer " +
//            "FROM completed_questions cq " +
//            "JOIN completed_polls cp ON cp.id = cq.completed_poll_id " +
//            "GROUP BY answer, question_id, cp.poll_id " +
//            "HAVING  cp.poll_id = :pollId " +
//            "ORDER BY question_id",
//            nativeQuery = true)
//    List<Statistic> countAnswersByPollId(@Param("pollId") Long pollId);
}

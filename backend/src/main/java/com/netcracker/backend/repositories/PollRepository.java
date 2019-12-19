package com.netcracker.backend.repositories;

import com.netcracker.backend.entities.Poll;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PollRepository extends JpaRepository<Poll, Long> {
    Optional<Poll> getByIdAndIsDeletedFalse(Long id);
    Optional<Poll> findOneByLinkAndIsDeletedFalse(String link);
    List<Poll> getAllByUserIdAndIsDeletedFalseAndIsDraft(Long userId, boolean isDraft);
    List<Poll> getAllByIsDeletedFalseAndIsDraftFalse(Pageable pageable);
}

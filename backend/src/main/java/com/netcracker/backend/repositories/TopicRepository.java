package com.netcracker.backend.repositories;

import com.netcracker.backend.entities.Topic;
import com.netcracker.backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
    List<Topic> getAllByUserIdAndIsDeletedFalse(Long userId);

    List<Topic> findAllByIsSharedTrueAndIsDeletedFalse();
}

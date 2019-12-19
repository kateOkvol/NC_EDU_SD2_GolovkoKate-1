package com.netcracker.backend.repositories;

import com.netcracker.backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> getByIdAndIsDeletedFalse(Long id);

    Optional<User> getByUserName(String username);

    Optional<User> getByUserNameAndPasswordAndIsDeletedFalse(String username, String password);

    Optional<User> getByIdAndIsDeletedFalseAndIsBanFalse(Long id);

    List<User> getAllByIsBanFalseAndIsDeletedFalse();

    Boolean existsByUserNameAndIsDeletedFalse(String email);
}

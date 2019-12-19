package com.netcracker.backend.services.impl;

import com.netcracker.backend.entities.User;
import com.netcracker.backend.services.UserService;
import com.netcracker.backend.repositories.UserRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.Optional;

//@Slf4j
@Service
@NoArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Long id) throws ChangeSetPersister.NotFoundException {
        Optional<User> optionalUser = userRepository.getByIdAndIsDeletedFalse(id);
        if (!optionalUser.isPresent()) {
            throw new ChangeSetPersister.NotFoundException();
            //log("Cannot find user with id " + id);
        }
        return optionalUser.get();
    }

    @Transactional
    public User findByUsername(String username) throws ChangeSetPersister.NotFoundException {
        Optional<User> optionalUser = userRepository.getByUserName(username);
        if (!optionalUser.isPresent()) {
            throw new ChangeSetPersister.NotFoundException();
            //log("Cannot find user with id " + id);
        }
        return optionalUser.get();
    }

    @Transactional
    public User login(User user) throws ChangeSetPersister.NotFoundException {
        Optional<User> optionalUser = userRepository.getByUserNameAndPasswordAndIsDeletedFalse(
                user.getUserName(), user.getPassword());
        if (!optionalUser.isPresent()) {
            throw new ChangeSetPersister.NotFoundException();
            //log("Cannot find user with id " + id);
        }
        return optionalUser.get();
    }

    @Transactional
    @Override
    public ResponseEntity<User> create(User user) {
        if (userRepository.existsByUserNameAndIsDeletedFalse(user.getUserName())) {
            return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
        }
        User created = userRepository.save(user);
        return new ResponseEntity<>(created, HttpStatus.OK);
    }

    @Transactional
    @Override
    public User delete(@NotNull Long id) throws ChangeSetPersister.NotFoundException {
        Optional<User> optionalUser = userRepository.getByIdAndIsDeletedFalse(id);
        if (!optionalUser.isPresent()) {
            throw new ChangeSetPersister.NotFoundException();
            //log("not found);
        }
        User user = optionalUser.get();
        user.setIsDeleted(true);
        return user;
    }

    @Transactional
    @Override
    public User update(@NotNull Long id, User userDTO) throws ChangeSetPersister.NotFoundException {
        Optional<User> optionalUser = userRepository.getByIdAndIsDeletedFalse(id);
        if (!optionalUser.isPresent()) {
            throw new ChangeSetPersister.NotFoundException();
            //log("not found);
        }
        User user = optionalUser.get();
        user = userDTO;
        return userRepository.save(user);
        //log
    }
}

package com.netcracker.backend.controllers;

import com.netcracker.backend.entities.User;
import com.netcracker.backend.services.impl.UserServiceImpl;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/users")
@NoArgsConstructor
public class UserController {
    private UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("{id}")
    public User findById(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        return userService.findById(id);
    }
    @GetMapping("/login/{username}")
    public User findByUsername(@PathVariable String username) throws ChangeSetPersister.NotFoundException {
        return userService.findByUsername(username);
    }

    @PostMapping("/login")
    public User login(@Valid @RequestBody User user) throws ChangeSetPersister.NotFoundException {
        return userService.login(user);
    }

    @PostMapping
    public ResponseEntity<User> create(@Valid @RequestBody User user) {
        return userService.create(user);
    }

    @DeleteMapping("{id}")
    public User delete(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        return userService.delete(id);
    }

    @PutMapping("{id}")
    public User update(@PathVariable Long id,
                       @Valid @RequestBody User user)
            throws ChangeSetPersister.NotFoundException {
        return userService.update(id, user);
    }
}

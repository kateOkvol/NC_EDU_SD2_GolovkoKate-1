package com.netcracker.fapi.controllers;

import lombok.NoArgsConstructor;
import com.netcracker.fapi.viewmodels.UserVM;
import com.netcracker.fapi.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/users")
@NoArgsConstructor
public class UserController {
    private UserServiceImpl service;

    @Autowired
    public UserController(UserServiceImpl service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping("/current")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity current() {
        return service.current();
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        return service.delete(id);
    }

    @PostMapping()
    public ResponseEntity create(@RequestBody @Validated UserVM userVM) {
        return service.create(userVM);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Validated UserVM userVM) {
        return service.login(userVM);
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody @Validated UserVM userVM) {
        return service.update(id, userVM);
    }
}

package com.netcracker.fapi.controllers;

import com.netcracker.fapi.services.impl.PollServiceImpl;
import com.netcracker.fapi.viewmodels.PollVM;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("api/polls")
@NoArgsConstructor
public class PollController {
    private PollServiceImpl service;

    @Autowired
    public PollController(PollServiceImpl service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public ResponseEntity findById(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        return service.findById(id);
    }

    @GetMapping()
    public ResponseEntity findByLink(@RequestParam @NotNull String link) throws ChangeSetPersister.NotFoundException {
        return service.findByLink(link);
    }

    @GetMapping("/admin")
    public ResponseEntity findAll(@RequestParam int page, @RequestParam int size) {
        return service.findAll(page, size);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity findByUserId(@PathVariable @NotNull Long id, @RequestParam @NotNull String isDraft) {
        return service.findByUserId(id, isDraft);
    }

    @PostMapping()
    public ResponseEntity create(@RequestBody @Validated PollVM pollVM) {
        return service.create(pollVM);
    }
}

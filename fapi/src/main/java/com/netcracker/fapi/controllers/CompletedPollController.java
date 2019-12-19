package com.netcracker.fapi.controllers;

import com.netcracker.fapi.services.impl.CompletedPollServiceImpl;
import com.netcracker.fapi.viewmodels.CompletedPollVM;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("api/surveys")
@NoArgsConstructor
public class CompletedPollController {
    private CompletedPollServiceImpl service;

    @Autowired
    public CompletedPollController(CompletedPollServiceImpl service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public ResponseEntity findById(@PathVariable @NotNull Long id) {
        return service.findById(id);
    }

    @GetMapping("/poll/{id}")
    public ResponseEntity getByPollId(@PathVariable @NotNull Long id) {
        return service.getByPollId(id);
    }

    @PostMapping()
    public ResponseEntity create(@RequestBody @Validated CompletedPollVM completedPollVM) {
        return service.create(completedPollVM);
    }

}

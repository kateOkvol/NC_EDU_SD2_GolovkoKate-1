package com.netcracker.backend.controllers;

import com.netcracker.backend.entities.CompletedPoll;
import com.netcracker.backend.services.impl.CompletedPollServiceImpl;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("api/surveys")
@NoArgsConstructor
public class CompletedPollController {
    private CompletedPollServiceImpl pollService;

    @Autowired
    public CompletedPollController(CompletedPollServiceImpl pollService) {
        this.pollService = pollService;
    }

    @GetMapping("{id}")
    public CompletedPoll getById(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        return pollService.findById(id);
    }

    @GetMapping("/poll/{id}")
    public List<CompletedPoll> getByPollId(@PathVariable @NotNull Long id) {
        return pollService.getByPollId(id);
    }

    @PostMapping
    public CompletedPoll create(@RequestBody @Validated CompletedPoll completedPoll) {
        return pollService.create(completedPoll);
    }
}

package com.netcracker.backend.controllers;

import com.netcracker.backend.entities.Poll;
import com.netcracker.backend.services.impl.PollServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/polls")
public class PollController {
    private PollServiceImpl pollService;

    @Autowired
    public PollController(PollServiceImpl pollService) {
        this.pollService = pollService;
    }

    @GetMapping("{id}")
    public Poll findById(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        return pollService.findById(id);
    }

    @GetMapping()
    public Poll getByLink(@RequestParam String link) throws ChangeSetPersister.NotFoundException {
        return pollService.findByLink(link);
    }

    @GetMapping("/user/{id}")
    public List<Poll> getUserPolls(@PathVariable Long id, @RequestParam String isDraft) {
        return pollService.findByUserId(id, isDraft);
    }

    @GetMapping("/admin")
    public List<Poll> getAll(@RequestParam int page, @RequestParam int size) {
        return pollService.findAll(page, size);
    }

    @PostMapping
    public Poll create(@Valid @RequestBody Poll poll) {
        return pollService.create(poll);
    }

    @DeleteMapping("{id}")
    public Poll delete(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        return pollService.delete(id);
    }

    @PutMapping("{id}")
    public Poll update(@PathVariable Long id,
                       @Valid @RequestBody Poll poll)
            throws ChangeSetPersister.NotFoundException {
        return pollService.update(id, poll);
    }

}

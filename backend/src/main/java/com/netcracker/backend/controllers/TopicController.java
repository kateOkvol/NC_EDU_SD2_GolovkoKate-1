package com.netcracker.backend.controllers;

import com.netcracker.backend.entities.Topic;
import com.netcracker.backend.services.impl.TopicServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/topics")
public class TopicController {
    private TopicServiceImpl service;

    @Autowired
    public TopicController(TopicServiceImpl service) {
        this.service = service;
    }

    @GetMapping("/user/{id}")
    public List<Topic> getByUserId(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        return service.findByUserId(id);
    }

    @GetMapping("/admin")
    public List<Topic> getByAdmin() throws ChangeSetPersister.NotFoundException {
        return service.findByAdmin();
    }

    @GetMapping("{id}")
    public Topic getById(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        return service.findById(id);
    }

    @PostMapping
    public Topic create(@RequestBody @Validated Topic topic) {
        return service.create(topic);
    }

    @PutMapping("{id}")
    public Topic update(@PathVariable Long id, @RequestBody @Validated Topic topic) throws ChangeSetPersister.NotFoundException {
        return service.update(id, topic);
    }

    @DeleteMapping("{id}")
    public Topic delete(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        return service.delete(id);
    }
}

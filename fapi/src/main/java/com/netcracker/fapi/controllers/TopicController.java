package com.netcracker.fapi.controllers;

import com.netcracker.fapi.services.impl.TopicServiceImpl;
import com.netcracker.fapi.viewmodels.TopicVM;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("api/topics")
@NoArgsConstructor
public class TopicController {
    private TopicServiceImpl service;

    @Autowired
    public TopicController(TopicServiceImpl service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public ResponseEntity findById(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        return service.findById(id);
    }

    @GetMapping("/admin")
    public ResponseEntity findByAdmin() throws ChangeSetPersister.NotFoundException {
        return service.findByAdmin();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity findByUserId(@PathVariable @NotNull Long id) throws ChangeSetPersister.NotFoundException {
        return service.findByUserId(id);
    }

    @PostMapping()
    public ResponseEntity create(@RequestBody @Validated TopicVM topicVM) {
        return service.create(topicVM);
    }

}

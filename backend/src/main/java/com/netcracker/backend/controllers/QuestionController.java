package com.netcracker.backend.controllers;

import com.netcracker.backend.entities.Question;
import com.netcracker.backend.services.impl.QuestionServiceImpl;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/questions")
@NoArgsConstructor
public class QuestionController {
    private QuestionServiceImpl questionServiceImpl;

    @Autowired
    public QuestionController(QuestionServiceImpl questionServiceImpl) {
        this.questionServiceImpl = questionServiceImpl;
    }

    @GetMapping("{id}")
    public Question findById(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        return questionServiceImpl.findById(id);
    }


    @PostMapping
    public Question create(@Valid @RequestBody Question question) {
        return questionServiceImpl.create(question);
    }

    @DeleteMapping("{id}")
    public Question delete(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        return questionServiceImpl.delete(id);
    }

    @PutMapping("{id}")
    public Question update(@PathVariable Long id,
                           @Valid @RequestBody Question question)
            throws ChangeSetPersister.NotFoundException {
        return questionServiceImpl.update(id, question);
    }
}

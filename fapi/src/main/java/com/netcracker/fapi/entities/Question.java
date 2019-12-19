package com.netcracker.fapi.entities;

import com.netcracker.fapi.entities.enums.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    private Long id;
    private QuestionType type;
    private String name;
    private List<Answer> answers;
    private Boolean isRequired;
    private Boolean isDeleted = false;
}

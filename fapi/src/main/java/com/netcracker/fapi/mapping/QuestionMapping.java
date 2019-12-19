package com.netcracker.fapi.mapping;

import com.netcracker.fapi.entities.Question;
import com.netcracker.fapi.viewmodels.QuestionVM;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class QuestionMapping extends AppMapping {
    public QuestionMapping() {
        modelMapper.createTypeMap(Question.class, QuestionVM.class);
        modelMapper.createTypeMap(QuestionVM.class, Question.class);
    }

    public ModelMapper getMapper() {
        return modelMapper;
    }
}

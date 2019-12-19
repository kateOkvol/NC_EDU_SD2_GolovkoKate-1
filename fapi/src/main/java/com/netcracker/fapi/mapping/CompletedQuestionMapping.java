package com.netcracker.fapi.mapping;

import com.netcracker.fapi.entities.CompletedQuestion;
import com.netcracker.fapi.viewmodels.CompletedQuestionVM;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CompletedQuestionMapping extends AppMapping {

    public CompletedQuestionMapping() {
        modelMapper.createTypeMap(CompletedQuestion.class, CompletedQuestionVM.class);
        modelMapper.createTypeMap(CompletedQuestionVM.class, CompletedQuestion.class);
    }

    public ModelMapper getMapper() {
        return modelMapper;
    }
}

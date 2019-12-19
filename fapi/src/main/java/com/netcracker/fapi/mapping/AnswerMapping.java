package com.netcracker.fapi.mapping;

import com.netcracker.fapi.entities.Answer;
import com.netcracker.fapi.viewmodels.AnswerVM;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AnswerMapping extends AppMapping {

    public AnswerMapping() {
        modelMapper.createTypeMap(Answer.class, AnswerVM.class);
        modelMapper.createTypeMap(AnswerVM.class, Answer.class);
    }

    public ModelMapper getMapper() {
        return modelMapper;
    }
}

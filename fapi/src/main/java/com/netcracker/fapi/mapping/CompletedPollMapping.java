package com.netcracker.fapi.mapping;

import com.netcracker.fapi.entities.CompletedPoll;
import com.netcracker.fapi.entities.Poll;
import com.netcracker.fapi.viewmodels.CompletedPollVM;
import com.netcracker.fapi.viewmodels.PollVM;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CompletedPollMapping extends AppMapping {

    public CompletedPollMapping() {
        modelMapper.createTypeMap(CompletedPoll.class, CompletedPollVM.class);
        modelMapper.createTypeMap(CompletedPollVM.class, CompletedPoll.class)
        ;
    }

    public ModelMapper getMapper() {
        return modelMapper;
    }
}

package com.netcracker.fapi.mapping;


import com.netcracker.fapi.entities.Poll;
import com.netcracker.fapi.viewmodels.PollVM;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PollMapping extends AppMapping {

    public PollMapping() {
        modelMapper.createTypeMap(Poll.class, PollVM.class);
        modelMapper.createTypeMap(PollVM.class, Poll.class);
    }

    public ModelMapper getMapper() {
        return modelMapper;
    }
}

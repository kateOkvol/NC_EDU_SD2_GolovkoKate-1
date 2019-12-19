package com.netcracker.fapi.mapping;

import com.netcracker.fapi.entities.Topic;
import com.netcracker.fapi.viewmodels.TopicVM;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TopicMapping extends AppMapping {
    public TopicMapping() {
        modelMapper.createTypeMap(TopicVM.class, Topic.class);
        modelMapper.createTypeMap(Topic.class, TopicVM.class);
    }

    public ModelMapper getMapper() {
        return modelMapper;
    }
}

package com.netcracker.fapi.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;

@Component
public class AppMapping {
    protected ModelMapper modelMapper = new ModelMapper();

    public AppMapping(){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT).setFieldMatchingEnabled(true).setFieldAccessLevel(PRIVATE).setAmbiguityIgnored(true);
    }
}

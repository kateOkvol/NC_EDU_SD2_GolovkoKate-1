package com.netcracker.fapi.mapping;

import com.netcracker.fapi.entities.User;
import com.netcracker.fapi.viewmodels.UserVM;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapping extends AppMapping {
    public UserMapping() {
        modelMapper.createTypeMap(User.class, UserVM.class);
        modelMapper.createTypeMap(UserVM.class, User.class);
    }

    public ModelMapper getMapper() {
        return modelMapper;
    }
}

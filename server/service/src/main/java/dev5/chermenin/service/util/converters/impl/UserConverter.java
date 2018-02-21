package dev5.chermenin.service.util.converters.impl;

import dev5.chermenin.model.entity.impl.User;
import dev5.chermenin.service.dto.impl.user.UserDto;
import dev5.chermenin.service.util.converters.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConverter
        extends AbstractConverter<UserDto, User> {
    private ModelMapper modelMapper;

    @Autowired
    public UserConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserDto convertToDto(User user) {
        return this.modelMapper.map(user, UserDto.class);
    }

    public User convertToEntity(UserDto userDto) {
        return this.modelMapper.map(userDto, User.class);
    }
}


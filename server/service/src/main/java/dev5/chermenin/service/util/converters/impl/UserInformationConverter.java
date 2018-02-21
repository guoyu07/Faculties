package dev5.chermenin.service.util.converters.impl;

import dev5.chermenin.model.entity.impl.UserInformation;
import dev5.chermenin.service.dto.impl.user.UserInformationDto;
import dev5.chermenin.service.util.converters.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserInformationConverter
        extends AbstractConverter<UserInformationDto, UserInformation> {
    private ModelMapper modelMapper;

    @Autowired
    public UserInformationConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserInformationDto convertToDto(UserInformation userInformation) {
        return this.modelMapper.map(userInformation, UserInformationDto.class);
    }

    public UserInformation convertToEntity(UserInformationDto dto) {
        return this.modelMapper.map(dto, UserInformation.class);
    }
}


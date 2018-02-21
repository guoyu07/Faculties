package dev5.chermenin.service.util.converters.impl;

import dev5.chermenin.model.entity.impl.Group;
import dev5.chermenin.service.dto.impl.GroupDto;
import dev5.chermenin.service.util.converters.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value = "groupConverter")
public class GroupConverter
        extends AbstractConverter<GroupDto, Group> {
    private ModelMapper modelMapper;

    @Autowired
    public GroupConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public GroupDto convertToDto(Group group) {
        return this.modelMapper.map(group, GroupDto.class);
    }

    public Group convertToEntity(GroupDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException("groupDto can't be null");
        }
        return this.modelMapper.map(dto, Group.class);
    }
}


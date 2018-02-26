package dev5.chermenin.service.util.converters.impl;

import dev5.chermenin.model.entity.impl.Subject;
import dev5.chermenin.service.dto.subject.SubjectDto;
import dev5.chermenin.service.util.converters.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubjectConverter
        extends AbstractConverter<SubjectDto, Subject> {
    private ModelMapper modelMapper;

    @Autowired
    public SubjectConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public SubjectDto convertToDto(Subject subject) {
        return this.modelMapper.map(subject, SubjectDto.class);
    }

    public Subject convertToEntity(SubjectDto dto) {
        return this.modelMapper.map(dto, Subject.class);
    }
}


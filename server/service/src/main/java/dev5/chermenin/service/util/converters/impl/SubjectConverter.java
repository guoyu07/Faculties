package dev5.chermenin.service.util.converters.impl;

import dev5.chermenin.model.entity.impl.Subject;
import dev5.chermenin.service.dto.subject.SubjectDto;
import dev5.chermenin.service.util.converters.AbstractConverter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SubjectConverter extends AbstractConverter<SubjectDto, Subject> {
    private final ModelMapper modelMapper;

    public SubjectDto convertToDto(Subject subject) {
        return this.modelMapper.map(subject, SubjectDto.class);
    }

    public Subject convertToEntity(SubjectDto dto) {
        return this.modelMapper.map(dto, Subject.class);
    }
}


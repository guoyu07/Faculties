package dev5.chermenin.service.util.converters.impl;

import dev5.chermenin.model.entity.impl.University;
import dev5.chermenin.service.dto.impl.UniversityDto;
import dev5.chermenin.service.util.converters.AbstractConverter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UniversityConverter extends AbstractConverter<UniversityDto, University> {
    private final ModelMapper modelMapper;

    public UniversityDto convertToDto(University university) {
        return this.modelMapper.map(university, UniversityDto.class);
    }

    public University convertToEntity(UniversityDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException("groupDto can't be null");
        }
        return this.modelMapper.map(dto, University.class);
    }
}


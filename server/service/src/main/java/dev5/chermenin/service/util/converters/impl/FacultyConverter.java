package dev5.chermenin.service.util.converters.impl;

import dev5.chermenin.model.entity.impl.Faculty;
import dev5.chermenin.service.dto.impl.FacultyDto;
import dev5.chermenin.service.util.converters.AbstractConverter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FacultyConverter extends AbstractConverter<FacultyDto, Faculty> {
    private final ModelMapper modelMapper;

    public FacultyDto convertToDto(Faculty faculty) {
        FacultyDto facultyDto = this.modelMapper.map(faculty, FacultyDto.class);
        return facultyDto;
    }

    public Faculty convertToEntity(FacultyDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException("groupDto can't be null");
        }
        return this.modelMapper.map(dto, Faculty.class);
    }
}


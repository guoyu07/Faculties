package dev5.chermenin.service.util.converters.modelMapperConverter;


import com.github.jmnarloch.spring.boot.modelmapper.ConverterConfigurerSupport;
import dev5.chermenin.model.entity.impl.Faculty;
import dev5.chermenin.model.entity.impl.Group;
import dev5.chermenin.model.entity.impl.Subject;
import dev5.chermenin.model.entity.impl.User;
import dev5.chermenin.service.dto.impl.FacultyDto;
import dev5.chermenin.service.dto.impl.GroupDto;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.util.stream.Collectors.toSet;

/**
 * Created by Ancarian on 11.02.2018.
 */
@Component
public class FacultyDtoConverter extends ConverterConfigurerSupport<Faculty, FacultyDto> {
    private ModelMapper modelMapper;

    public FacultyDtoConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Converter<Faculty, FacultyDto> converter() {

        return new AbstractConverter<Faculty, FacultyDto>() {
            @Override
            protected FacultyDto convert(Faculty source) {
                FacultyDto facultyDto = new FacultyDto();

                facultyDto.setInformation(source.getInformation());
                facultyDto.setName(source.getName());
                facultyDto.setUniversityId(source.getUniversity().getId());
                facultyDto.setId(source.getId());

                if (source.getGroups() != null) {
                    List<GroupDto> userDtoSet = new LinkedList<>();
                    for (Group group : source.getGroups()) {
                        userDtoSet.add(modelMapper.map(group, GroupDto.class));
                    }
                    facultyDto.setGroups(userDtoSet);
                }

                return facultyDto;
            }
        };
    }
}
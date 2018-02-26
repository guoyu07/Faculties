package dev5.chermenin.service.util.converters.modelMapperConverter;

import com.github.jmnarloch.spring.boot.modelmapper.ConverterConfigurerSupport;
import dev5.chermenin.model.entity.impl.Subject;
import dev5.chermenin.model.entity.impl.User;
import dev5.chermenin.service.dto.impl.user.UserDto;
import dev5.chermenin.service.dto.impl.user.UserInformationDto;
import dev5.chermenin.service.dto.subject.SubjectScoreDto;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Ancarian on 11.02.2018.
 */
@Component
public class UserDtoConverter extends ConverterConfigurerSupport<User, UserDto> {
    private ModelMapper modelMapper;

    public UserDtoConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Converter<User, UserDto> converter() {
        return new AbstractConverter<User, UserDto>() {
            @Override
            protected UserDto convert(User source) {
                UserDto dto = new UserDto();
                Set<SubjectScoreDto> subjectScoreDtoSet = new HashSet<>();
                for (Map.Entry<Subject, Integer> marks:source.getSubjects().entrySet()) {
                    SubjectScoreDto subjectScoreDto = new SubjectScoreDto();
                    subjectScoreDto.setScore(marks.getValue());
                    subjectScoreDto.setSubject(marks.getKey().getSubject());
                    subjectScoreDto.setId(marks.getKey().getId());

                    subjectScoreDtoSet.add(subjectScoreDto);
                }

                if (source.getGroup() != null) {
                    dto.setGroupId(source.getGroup().getId());
                }

                dto.setName(source.getName());
                dto.setLastname(source.getLastname());
                dto.setPatronymic(source.getPatronymic());
                dto.setId(source.getId());
                //dto.setInfo(modelMapper.map(source.getInfo(), UserInformationDto.class));
                return dto;
            }
        };
    }
}
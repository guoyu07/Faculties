package dev5.chermenin.service.util.converters.modelMapperConverter;

import com.github.jmnarloch.spring.boot.modelmapper.ConverterConfigurerSupport;
import dev5.chermenin.model.entity.impl.Group;
import dev5.chermenin.model.entity.impl.Subject;
import dev5.chermenin.model.entity.impl.User;
import dev5.chermenin.service.dto.impl.GroupDto;
import dev5.chermenin.service.dto.impl.user.UserScoreDto;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toSet;

/**
 * Created by Ancarian on 11.02.2018.
 */
@Component
public class GroupDtoConverter extends ConverterConfigurerSupport<Group, GroupDto> {
    private ModelMapper modelMapper;

    public GroupDtoConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Converter<Group, GroupDto> converter() {

        return new AbstractConverter<Group, GroupDto>() {
            @Override
            protected GroupDto convert(Group source) {
                GroupDto groupDto = new GroupDto();

                if (source.getUsers() != null) {
                    List<UserScoreDto> userDtoSet = new ArrayList<>();
                    for (User user : source.getUsers()) {
                        userDtoSet.add(modelMapper.map(user, UserScoreDto.class));
                    }
                    groupDto.setUsers(userDtoSet);
                }

                if (source.getSubjects() != null) {
                    groupDto.setSubjectNames(source.getSubjects().stream().map(Subject::getSubject).collect(toSet()));
                }
                groupDto.setValidTill(source.getValidTill());
                groupDto.setInformation(source.getInformation());
                groupDto.setIssueDate(source.getIssueDate());
                groupDto.setCountOfAllUsers(source.getCountOfAllUsers());
                groupDto.setCountOfUsers(source.getCountOfUsers());
                groupDto.setEnrollMark(source.getEnrollMark());
                groupDto.setLimit(source.getLimit());
                groupDto.setQualify(source.getQualify());
                groupDto.setFacultyId(source.getFaculty().getId());
                groupDto.setId(source.getId());

                return groupDto;
            }
        };
    }
}
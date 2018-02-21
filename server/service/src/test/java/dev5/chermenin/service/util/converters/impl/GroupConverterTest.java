package dev5.chermenin.service.util.converters.impl;

import dev5.chermenin.dao.repository.SubjectRepository;
import dev5.chermenin.model.entity.impl.Subject;
import dev5.chermenin.model.entity.impl.User;
import dev5.chermenin.service.dto.impl.GroupDto;
import dev5.chermenin.model.entity.impl.Group;
import dev5.chermenin.service.dto.impl.user.UserDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static dev5.chermenin.service.util.Generator.generateGroup;
import static dev5.chermenin.service.util.Generator.generateSubject;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.when;

/**
 * Created by Ancarian on 11.12.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class GroupConverterTest {
    @Mock
    private SubjectRepository subjectRepository;
    @Mock
    private UserConverter userConverter;
    private ModelMapper modelMapper = new ModelMapper();
    private GroupConverter groupConverter;

    private Group group;

    @Before
    public void setUp() throws Exception {
        group = generateGroup();

        when(userConverter.convertToDto(anyListOf(User.class))).thenReturn(new ArrayList<>());
        when(userConverter.convertToEntity(anyListOf(UserDto.class))).thenReturn(new ArrayList<>());
        when(subjectRepository.findByName("math")).thenReturn(generateSubject());

        groupConverter = new GroupConverter(modelMapper);
    }

    @Test
    public void convertToDto() throws Exception {
        GroupDto dto = groupConverter.convertToDto(group);
        assertEquals((Long) group.getId(), (Long) dto.getId());
        assertEquals(group.getInformation(), dto.getInformation());
        assertEquals(group.getIssueDate(), dto.getIssueDate());
        assertEquals(group.getValidTill(), dto.getValidTill());
        assertEquals(group.getLimit(), dto.getLimit());
        assertEquals(group.getCountOfAllUsers(), dto.getCountOfAllUsers());
        assertEquals(group.getCountOfUsers(), dto.getCountOfUsers());

        for (String name : dto.getSubjectNames()) {
            assertEquals(name, "math");
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void convertToDtoWhenNull() throws Exception {
        Group group = null;
        groupConverter.convertToDto(group);
    }

    @Test
    public void convertToEntity() throws Exception {
        GroupDto dto = groupConverter.convertToDto(group);
        Group group1 = groupConverter.convertToEntity(dto);
        assertEquals(group1, group);
    }

    @Test(expected = IllegalArgumentException.class)
    public void convertToEntityWhenNull() throws Exception {
        GroupDto dto = null;
        groupConverter.convertToEntity(dto);
    }



}
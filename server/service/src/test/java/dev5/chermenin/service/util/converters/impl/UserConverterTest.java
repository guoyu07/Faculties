package dev5.chermenin.service.util.converters.impl;

import dev5.chermenin.dao.repository.UserRepository;
import dev5.chermenin.model.entity.impl.User;
import dev5.chermenin.model.entity.impl.enums.Roles;
import dev5.chermenin.service.dto.impl.user.UserDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.HashSet;
import java.util.Set;

import static dev5.chermenin.service.util.Generator.generateRoles;
import static dev5.chermenin.service.util.Generator.generateUser;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by Ancarian on 01.12.2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class UserConverterTest {
    @Mock
    private UserRepository userRepository;
    private ModelMapper modelMapper = new ModelMapper();
    private UserConverter userConverter;
    private User user;

    @Before
    public void setUp() throws Exception {
        Set<Roles> roles = new HashSet<>();
        roles.add(generateRoles());
        user = generateUser();
        user.setId(1L);

        when(userRepository.findOne(1L)).thenReturn(user);
        userConverter = new UserConverter(modelMapper);

    }

    @Test
    public void convertToDto() {
        UserDto userDto = userConverter.convertToDto(user);

        assertEquals(user.getId(), userDto.getId());
        assertEquals(user.getName(), userDto.getName());
        assertEquals(user.getLastname(), userDto.getLastname());
        assertEquals(user.getPatronymic(), userDto.getPatronymic());
//     for (Map.Entry<SubjectDto, Integer> entry : userDto.getMarks().entrySet()) {
//            assertEquals(entry.getKey(), subjectConverter.convertToDto(generateSubject()));
//            assertEquals(entry.getValue(), (Integer) 50);
//        }
    }

    @Test
    public void convertToEntity() {
        UserDto userDto = userConverter.convertToDto(user);
        User userr = userConverter.convertToEntity(userDto);

        assertEquals(user, userr);
    }

}

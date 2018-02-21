package dev5.chermenin.service.util.converters.impl;


import dev5.chermenin.model.entity.impl.UserInformation;
import dev5.chermenin.model.entity.impl.enums.Roles;
import dev5.chermenin.service.dto.impl.user.UserInformationDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.HashSet;
import java.util.Set;

import static dev5.chermenin.service.util.Generator.generateRoles;
import static dev5.chermenin.service.util.Generator.generateUserInfo;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by Ancarian on 11.12.2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class UserInformationConverterTest {
    private ModelMapper modelMapper = new ModelMapper();
    private UserInformationConverter userInformationConverter;
    private UserInformation userInformation;



    @Before
    public void setUp() throws Exception {
        Set<Roles> roles = new HashSet<>();
        roles.add(generateRoles());

        userInformation = generateUserInfo();
        userInformationConverter = new UserInformationConverter(modelMapper);
    }


    @Test
    public void convertToDto() throws Exception {
        UserInformationDto dto = userInformationConverter.convertToDto(userInformation);
        assertEquals(dto.getNickname(), userInformation.getNickname());
        assertEquals(dto.getEmail(), userInformation.getEmail());
        assertEquals(dto.getPassword(), userInformation.getPassword());

    }

    @Test
    public void convertToEntity() throws Exception {
        UserInformationDto dto = userInformationConverter.convertToDto(userInformation);
        UserInformation userInfo = userInformationConverter.convertToEntity(dto);

        assertEquals(userInformation, userInfo);
    }

}
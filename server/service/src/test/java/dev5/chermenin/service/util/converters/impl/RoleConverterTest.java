//package dev5.chermenin.service.util.converters.impl;
//
//
//import dev5.chermenin.model.entity.impl.enums.Roles;
//import org.junit.Before;
//import org.junit.Test;
//import org.modelmapper.ModelMapper;
//
//import static dev5.chermenin.service.util.Generator.generateRoles;
//import static org.junit.Assert.assertEquals;
//
///**
// * Created by Ancarian on 11.12.2017.
// */
//public class RoleConverterTest {
//    private ModelMapper modelMapper = new ModelMapper();
//
//    @Before
//    public void setUp() throws Exception {
//        Roles role = generateRoles();
//
//        roleConverter = new RoleConverter(modelMapper);
//    }
//
//    @Test
//    public void convertToDto() throws Exception {
//        RoleDto dto = roleConverter.convertToDto(role);
//        assertEquals(dto.getRole(), role.getRole());
//        assertEquals((Long) dto.getId(), role.getId());
//    }
//
//    @Test
//    public void convertToEntity() throws Exception {
//        RoleDto dto = roleConverter.convertToDto(role);
//        Role secondRole = roleConverter.convertToEntity(dto);
//        assertEquals(role, secondRole);
//    }
//
//}
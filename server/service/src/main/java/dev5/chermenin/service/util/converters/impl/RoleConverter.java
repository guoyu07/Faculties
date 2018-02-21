//package dev5.chermenin.service.util.converters.impl;
//
//
//import dev5.chermenin.service.dto.impl.RoleDto;
//import dev5.chermenin.model.entity.impl.Role;
//import dev5.chermenin.service.util.converters.AbstractConverter;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
///**
// * Created by Ancarian on 02.12.2017.
// */
//@Component
//public class RoleConverter extends AbstractConverter<RoleDto, Role> {
//
//    private ModelMapper modelMapper;
//
//    @Autowired
//    public RoleConverter(ModelMapper modelMapper) {
//        this.modelMapper = modelMapper;
//    }
//
//    @Override
//    public RoleDto convertToDto(Role role) {
//        return modelMapper.map(role, RoleDto.class);
//    }
//
//    @Override
//    public Role convertToEntity(RoleDto dto) {
//        return modelMapper.map(dto, Role.class);
//    }
//}

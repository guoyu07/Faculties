package dev5.chermenin.service;

/**
 * Created by Ancarian on 15.11.2017.
 */

import dev5.chermenin.model.entity.impl.*;
import dev5.chermenin.service.dto.impl.FacultyDto;
import dev5.chermenin.service.dto.impl.GroupDto;

import dev5.chermenin.service.dto.impl.user.UserDto;
import dev5.chermenin.service.dto.impl.user.UserScoreDto;
import dev5.chermenin.service.util.converters.modelMapperConverter.FacultyDtoConverter;
import dev5.chermenin.service.util.converters.modelMapperConverter.GroupDtoConverter;
import dev5.chermenin.service.util.converters.modelMapperConverter.UserDtoConverter;
import dev5.chermenin.service.util.converters.modelMapperConverter.UserScoreDtoConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "dev5.chermenin")
@EntityScan(basePackages = "dev5.chermenin.model.entity.impl")
@EnableJpaRepositories(basePackages = "dev5.chermenin.dao.repository")
@EnableCaching
public class TestDataBaseConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.createTypeMap(User.class, UserDto.class)
                .setConverter(new UserDtoConverter(modelMapper).converter());

        modelMapper.createTypeMap(Group.class, GroupDto.class)
                .setConverter(new GroupDtoConverter(modelMapper).converter());

        modelMapper.createTypeMap(User.class, UserScoreDto.class).setConverter(new UserScoreDtoConverter().converter());
        modelMapper.createTypeMap(Faculty.class, FacultyDto.class).setConverter(new FacultyDtoConverter(modelMapper).converter());


        return modelMapper;
    }
}


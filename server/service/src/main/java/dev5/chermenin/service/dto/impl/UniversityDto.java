package dev5.chermenin.service.dto.impl;

import dev5.chermenin.service.dto.Dto;
import dev5.chermenin.service.dto.impl.user.UserDto;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
public class UniversityDto extends Dto {

    private Set<UserDto> moderators;
    @NotNull
    private String name;

    private Set<FacultyDto> faculties;
}


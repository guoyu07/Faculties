package dev5.chermenin.service.dto.impl;

import dev5.chermenin.service.dto.Dto;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class FacultyDto extends Dto {
    private String name;

    private String information;

    private Set<GroupDto> groups;

    private long universityId;

}


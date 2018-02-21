package dev5.chermenin.service.dto.impl;

import dev5.chermenin.service.dto.Dto;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SubjectDto extends Dto {
    @NotNull
    private String subject;
}


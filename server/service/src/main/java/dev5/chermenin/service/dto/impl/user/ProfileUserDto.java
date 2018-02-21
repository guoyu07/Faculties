package dev5.chermenin.service.dto.impl.user;

import dev5.chermenin.service.dto.Dto;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Map;

@Getter
@Setter
public class ProfileUserDto extends Dto {
    private Long groupId;

    @NotNull
    private String name;

    @NotNull
    private String lastname;

    @NotNull
    private String patronymic;

    @Valid
    private UserInformationDto info;

    private Map<String, Integer> marks;

}


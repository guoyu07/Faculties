package dev5.chermenin.service.dto.impl.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev5.chermenin.service.dto.Dto;
import dev5.chermenin.service.dto.subject.SubjectScoreDto;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
public class UserDto extends Dto {

    private Long groupId;

    @NotNull
    private String name;

    @NotNull
    private String lastname;

    @NotNull
    private String patronymic;

    private Set<SubjectScoreDto> marks;

    @JsonIgnore
    private UserInformationDto userInformationDto;
}


package dev5.chermenin.service.dto.impl.user;

import dev5.chermenin.service.dto.Dto;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RegisterDto extends Dto {

    @NotNull
    private String name;

    @NotNull
    private String lastname;

    @NotNull
    private String patronymic;

    @NotNull
    private String nickname;

    @NotNull
    private String password;

    @NotNull
    private String email;

}

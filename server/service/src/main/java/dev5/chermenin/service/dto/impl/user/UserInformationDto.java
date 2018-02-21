package dev5.chermenin.service.dto.impl.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev5.chermenin.service.dto.Dto;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserInformationDto extends Dto {

    @JsonIgnore
    private String password;

    @JsonIgnore
    private String email;

    @NotNull
    @NotEmpty
    @Size(min = 6, max = 20)
    private String nickname;

}


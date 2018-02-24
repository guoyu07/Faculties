package dev5.chermenin.service.dto.impl;

import com.fasterxml.jackson.annotation.JsonView;
import dev5.chermenin.service.dto.Dto;
import dev5.chermenin.service.dto.View;
import dev5.chermenin.service.dto.impl.user.UserScoreDto;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class GroupDto extends Dto {

    @JsonView(value = {View.Enable.class})
    @Min(value = 0)
    private int countOfUsers;

    @JsonView(value = {View.Enable.class})
    @Min(value = 0)
    private int countOfAllUsers;

    @NotEmpty
    @NotNull
    @JsonView(value = {View.Enable.class})
    private String information;

    @JsonView(value = {View.Enable.class})
    @NotNull
    private Date issueDate;

    @JsonView(value = {View.Enable.class})
    @NotNull
    private Date validTill;

    @JsonView(value = {View.Enable.class})
    @NotNull
    private Set<String> subjectNames;

    @JsonView(value = {View.Enable.class})
    @Min(value = 1)
    private int limit;

    @JsonView(value = {View.Enable.class})
    @Min(value = 0)
    private int enrollMark;

    @JsonView(value = {View.Enable.class})
    @NotNull
    private String qualify;

    @JsonView(value = {View.Enable.class})
    private long facultyId;

    @JsonView(value = {View.Disable.class})
    private List<UserScoreDto> users;


    @Override
    public String toString() {
        return "GroupDto{" +
                "countOfUsers=" + countOfUsers +
                ", countOfAllUsers=" + countOfAllUsers +
                ", information='" + information + '\'' +
                ", issueDate=" + issueDate +
                ", validTill=" + validTill +
                ", subjectNames=" + subjectNames +
                ", limit=" + limit +
                ", enrollMark=" + enrollMark +
                ", qualify='" + qualify + '\'' +
                ", facultyId=" + facultyId +
                ", users=" + users +
                '}';
    }
}


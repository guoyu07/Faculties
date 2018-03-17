package dev5.chermenin.service.dto.impl;

import com.fasterxml.jackson.annotation.JsonView;
import dev5.chermenin.service.dto.Dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.sql.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class GroupDto extends Dto {

    @Min(value = 0)
    private int countOfUsers;

    @Min(value = 0)
    private int countOfAllUsers;

    @NotEmpty
    @NotNull
    private String information;

    @NotNull
    private Date issueDate;

    @NotNull
    private Date validTill;

    @NotNull
    private Set<String> subjectNames;

    @Min(value = 1)
    private int limit;

    @Min(value = 0)
    private int enrollMark;

    @NotNull
    private String qualify;

    @NotNull
    private long facultyId;

    private List<BigInteger> users;

}


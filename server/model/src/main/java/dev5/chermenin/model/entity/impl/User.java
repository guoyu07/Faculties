package dev5.chermenin.model.entity.impl;

import dev5.chermenin.model.entity.BaseObj;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name = "users")
@Getter
@Setter
@EqualsAndHashCode
public class User extends BaseObj {

    @Column(name = "name")
    private String name;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "patronymic")
    private String patronymic;

    @ManyToOne
    @JoinColumn(name = "groups_id")
    private Group group;

    @OneToOne(cascade = {CascadeType.ALL})
    @PrimaryKeyJoinColumn
    private UserInformation info;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "users_subjects", joinColumns = {@JoinColumn(name = "users_id")})
    @MapKeyJoinColumn(name = "subjects_id")
    @Column(name = "mark")
    private Map<Subject, Integer> subjects;

}


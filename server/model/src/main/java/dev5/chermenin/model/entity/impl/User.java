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

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Column(name = "patronymic", nullable = false)
    private String patronymic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "groups_id")
    private Group group;

    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private UserInformation info;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "users_subjects", joinColumns = {@JoinColumn(name = "users_id")})
    @MapKeyJoinColumn(name = "subjects_id")
    @Column(name = "mark")
    private Map<Subject, Integer> subjects;

}

package dev5.chermenin.model.entity.impl;

import dev5.chermenin.model.entity.BaseObj;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "groups")
@Getter
@Setter
@EqualsAndHashCode
public class Group extends BaseObj {

    @Column(name = "count_of_users")
    private int countOfUsers;

    @Column(name = "count_of_all_users")
    private int countOfAllUsers;

    @Column(name = "information", unique = true)
    private String information;

    @Column(name = "issue_date")
    private Date issueDate;

    @Column(name = "valid_till")
    private Date validTill;

    @Column(name = "count")
    private int limit;

    @Column(name = "enroll_mark")
    private int enrollMark;

    @Column(name = "qualify")
    private String qualify;

    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
    private List<User> users;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "faculties_id", nullable = false)
    private Faculty faculty;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "groups_subjects", joinColumns = {@JoinColumn(name = "groups_id")},
            inverseJoinColumns = {@JoinColumn(name = "subjects_id")})
    private Set<Subject> subjects;


}


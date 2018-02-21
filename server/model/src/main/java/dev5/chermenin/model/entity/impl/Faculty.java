package dev5.chermenin.model.entity.impl;

import dev5.chermenin.model.entity.BaseObj;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "faculties")
@Getter
@Setter
@EqualsAndHashCode
public class Faculty extends BaseObj {

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "information", unique = true, nullable = false)
    private String information;

    @OneToMany(mappedBy = "faculty", fetch = FetchType.LAZY)
    private Set<Group> groups;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "universities_id", nullable = false)
    private University university;

}


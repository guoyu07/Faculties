package dev5.chermenin.model.entity.impl;

import dev5.chermenin.model.entity.BaseObj;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "universities")
@Getter
@Setter
@EqualsAndHashCode
public class University extends BaseObj {

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "moderators", joinColumns = {@JoinColumn(name = "faculties_id")},
            inverseJoinColumns = {@JoinColumn(name = "users_id")})
    private Set<User> moderators;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "university", fetch = FetchType.LAZY)
    private Set<Faculty> faculties;


}


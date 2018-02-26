package dev5.chermenin.model.entity.impl;

import dev5.chermenin.model.entity.BaseObject;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "universities")
public class University extends BaseObject {

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(name = "moderators", joinColumns = {@JoinColumn(name = "faculties_id")},
            inverseJoinColumns = {@JoinColumn(name = "users_id")})
    private Set<User> moderators;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "university", fetch = FetchType.LAZY)
    private Set<Faculty> faculties;


}

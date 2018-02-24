package dev5.chermenin.model.entity.impl;

import dev5.chermenin.model.entity.BaseObj;
import dev5.chermenin.model.entity.impl.enums.Roles;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "user_information")
@Getter
@Setter
@EqualsAndHashCode
public class UserInformation extends BaseObj {

    @Column(name = "date_of_registration")
    private Date dateOfRegistration;

    @Column(name = "nickname", nullable = false, unique = true)
    private String nickname;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @ElementCollection(targetClass = Roles.class, fetch = FetchType.LAZY)
    @Enumerated(value = EnumType.ORDINAL)
    @CollectionTable(name = "user_information_roles", joinColumns = {@JoinColumn(name = "user_information_id")})
    @Column(name = "roles")
    private Set<Roles> roles;


}
